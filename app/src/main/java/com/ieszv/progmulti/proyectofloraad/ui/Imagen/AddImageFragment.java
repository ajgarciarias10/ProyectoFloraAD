package com.ieszv.progmulti.proyectofloraad.ui.Imagen;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ieszv.progmulti.proyectofloraad.R;
import com.ieszv.progmulti.proyectofloraad.databinding.FragmentAddFloraBinding;
import com.ieszv.progmulti.proyectofloraad.databinding.FragmentAddImageBinding;
import com.ieszv.progmulti.proyectofloraad.model.Album.AlbumStorageDirFactory;
import com.ieszv.progmulti.proyectofloraad.model.entity.Imagen;
import com.ieszv.progmulti.proyectofloraad.viewmodel.AddImagenViewModel;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddImageFragment extends Fragment {
    //ACCION EN EL CASO DE QUE LA FOTO SEA PEQUEÑA
    private static final int ACTION_TAKE_PHOTO_B = 1;
    private static final int ACTION_TAKE_PHOTO_S = 2;
    //Ruta actual de la foto
    private String mCurrentPhotoPath;
    //Bits de imagenes para setearlo
    private Bitmap mImageBitmap;
    //Lo utilizamos para meternos en el album de fotos del telefono
    private AlbumStorageDirFactory mAlbumStorageDirFactory = null;
    //region Tipo de prefijo de fotos
    private static final String JPEG_FILE_PREFIX = "IMG_";
    private static final String JPEG_FILE_SUFFIX = ".jpg";
    //endregion
    private ActivityResultLauncher<Intent> launcher;
    private Intent resultadoImagen = null;
    private AddImagenViewModel aivm;
    //endregion
    FragmentAddImageBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddImageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadDataImage();

            }
        });
        binding.fabCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        binding.ivImageFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(ACTION_TAKE_PHOTO_S);
            }
        });
        return root;
    }


    private void uploadDataImage() {
        String nombre = binding.Nombre.getText().toString();
        String idFlora = binding.idFLORA.getText().toString();
        String descripcion = binding.etDescripcion.getText().toString();
            Imagen imagen = new Imagen();
            imagen.nombre = nombre;
            imagen.descripcion = descripcion;
            imagen.idflora = Long.parseLong(idFlora);
            aivm.saveImagen(resultadoImagen, imagen);
    }


    //region evento que se mete en la camara
    private void dispatchTakePictureIntent(int actionCode) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//la accion para sacar la imagen
        switch (actionCode) {
            //En el caso de que la imagen sea pequeña
            case ACTION_TAKE_PHOTO_B:
                File f = null;

                try {
                    f = setUpPhotoFile();
                    mCurrentPhotoPath = f.getAbsolutePath();
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                } catch (IOException e) {
                    e.printStackTrace();
                    f = null;
                    mCurrentPhotoPath = null;
                }
                break;

            default:
                break;
        } // switch

        startActivityForResult(takePictureIntent, actionCode);
    }
    //endregion

    private File setUpPhotoFile() throws IOException {

        File f = createImageFile();
        mCurrentPhotoPath = f.getAbsolutePath();

        return f;
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_";
        File albumF = getAlbumDir();
        File imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX, albumF);
        return imageF;
    }

    private File getAlbumDir() {
        File storageDir = null;

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

            storageDir = mAlbumStorageDirFactory.getAlbumStorageDir(getAlbumName());

            if (storageDir != null) {
                if (!storageDir.mkdirs()) {
                    if (!storageDir.exists()) {
                        Log.d("CameraSample", "failed to create directory");
                        return null;
                    }
                }
            }

        } else {
            Log.v(getString(R.string.app_name), "External storage is not mounted READ/WRITE.");
        }

        return storageDir;
    }

    private String getAlbumName() {
        return getString(R.string.album_name);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case ACTION_TAKE_PHOTO_S: {
                if (resultCode == RESULT_OK) {
                    handleSmallCameraPhoto(data);
                }
                break;
            } // ACTION_TAKE_PHOTO_S

        } // switch
    }

    private void handleSmallCameraPhoto(Intent intent) {
        Bundle extras = intent.getExtras();


        mImageBitmap = (Bitmap) extras.get("data");
        binding.ivDisplayimage.setImageBitmap(mImageBitmap);
        binding.ivDisplayimage.setVisibility(View.VISIBLE);

    }
}