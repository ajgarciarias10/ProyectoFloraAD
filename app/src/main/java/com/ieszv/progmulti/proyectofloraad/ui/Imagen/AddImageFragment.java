package com.ieszv.progmulti.proyectofloraad.ui.Imagen;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ieszv.progmulti.proyectofloraad.R;
import com.ieszv.progmulti.proyectofloraad.databinding.FragmentAddFloraBinding;
import com.ieszv.progmulti.proyectofloraad.databinding.FragmentAddImageBinding;
import com.ieszv.progmulti.proyectofloraad.model.Repository;
import com.ieszv.progmulti.proyectofloraad.model.api.FloraClient;
import com.ieszv.progmulti.proyectofloraad.model.entity.Flora;
import com.ieszv.progmulti.proyectofloraad.model.entity.Imagen;
import com.ieszv.progmulti.proyectofloraad.ui.Flora.AddFlora;
import com.ieszv.progmulti.proyectofloraad.ui.Flora.SlideshowViewModel;
import com.ieszv.progmulti.proyectofloraad.ui.MainActivity.MainActivity;
import com.ieszv.progmulti.proyectofloraad.viewmodel.AddFloraViewModel;
import com.ieszv.progmulti.proyectofloraad.viewmodel.AddImagenViewModel;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddImageFragment extends Fragment {
    private ActivityResultLauncher<Intent> launcher;
    FragmentAddImageBinding binding;
    private Bitmap mImageBitmap;
    private Intent resultadoImagen = null;
    public AddImagenViewModel avm;
    public Flora flora;
    private  Flora florita;
    Long id;
    SlideshowViewModel mavm ;
    Bundle bundle = new Bundle();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddImageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bundle = getArguments();



        mavm = new ViewModelProvider(this).get(SlideshowViewModel.class); //obtengo viewmodel de la actividad
        MutableLiveData<ArrayList<Flora>> floraList = mavm.getFloraLiveData();
        mavm.getFlora();
        floraList.observe(getViewLifecycleOwner(),  floras -> {
         if(AddFlora.mode == 1) {
              id =  floras.get(mavm.getFloraLiveData().getValue().size() - 1).getId();
             binding.etIDImagen.setText(floras.get(mavm.getFloraLiveData().getValue().size() - 1).getNombre() + "");
         }else if(AddFlora.mode == 2){
             florita = bundle.getParcelable("flora");
                id = florita.getId();
             binding.etIDImagen.setText(String.valueOf(florita.getNombre()));

         }
            AddFlora.mode=0;
        });

        launcher = getLauncher();
        binding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadDataImage();

            }
        });
        avm = new ViewModelProvider(this).get(AddImagenViewModel.class);

        binding.fabCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        binding.ivGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }


    ActivityResultLauncher<Intent> getLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    //respuesta al resultado de haber seleccionado una imagen
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        resultadoImagen = result.getData();
                        binding.ivGaleria.setImageURI(resultadoImagen.getData());
                    }
                }
        );
    }
    Intent getContentIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");


        return intent;
    }


    private void selectImage() {
        Intent intent = getContentIntent();
        launcher.launch(intent);

    }


    private void uploadDataImage() {

        Imagen imagen = new Imagen();
        String nombre = binding.Nombre.getText().toString();
        String descripcion = binding.etDescripcion.getText().toString();

        if (!(nombre.trim().isEmpty() || descripcion.trim().isEmpty() || resultadoImagen == null)) {


                imagen.idflora =id;

            imagen.nombre = nombre;
            imagen.descripcion = descripcion;
            avm.saveImagen(resultadoImagen, imagen);
            NavHostFragment.findNavController(AddImageFragment.this)
                    .navigate(R.id.action_nav_add_Image_to_nav_home);


        }
    }
}