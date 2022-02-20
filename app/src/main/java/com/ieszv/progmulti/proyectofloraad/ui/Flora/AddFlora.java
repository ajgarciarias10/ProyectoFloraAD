  package com.ieszv.progmulti.proyectofloraad.ui.Flora;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ieszv.progmulti.proyectofloraad.R;
import com.ieszv.progmulti.proyectofloraad.databinding.FragmentAddFloraBinding;
import com.ieszv.progmulti.proyectofloraad.databinding.FragmentSlideshowBinding;
import com.ieszv.progmulti.proyectofloraad.model.Album.AlbumStorageDirFactory;
import com.ieszv.progmulti.proyectofloraad.model.entity.Flora;
import com.ieszv.progmulti.proyectofloraad.viewmodel.AddFloraViewModel;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

  public class AddFlora extends Fragment {


    FragmentAddFloraBinding  binding;
      public View onCreateView(@NonNull LayoutInflater inflater,
                               ViewGroup container, Bundle savedInstanceState) {


          binding = FragmentAddFloraBinding.inflate(inflater, container, false);
          View root = binding.getRoot();


          return root;
      }

      @Override
      public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
          super.onViewCreated(view, savedInstanceState);
            initialize();
      }

      private void initialize() {
          AddFloraViewModel avm = new ViewModelProvider(this).get(AddFloraViewModel.class);
          avm.getAddFloraLiveData().observe(this, new Observer<Long>() {
              @Override
              public void onChanged(Long aLong) {
                  if(aLong > 0) {
                      System.exit(0);
                  } else {
                      Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                  }
              }
          });
          binding.fabCancel.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  System.exit(0);
              }
          });

          binding.fabAdd.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Flora flora = new Flora();
                  flora.setNombre(binding.idFlora.getText().toString());
                  flora.setAltitud(binding.attitude.getText().toString());
                  flora.setAmenazas(binding.threats.getText().toString());
                  flora.setBiologia(binding.biologia.getText().toString());
                  flora.setBiotipo(binding.biotipo.getText().toString());
                  flora.setFloracion(binding.floracion.getText().toString());
                  flora.setBiologia_reproductiva(binding.reproduccion.getText().toString());
                  flora.setDemografia(binding.demografA.getText().toString());
                  flora.setDispersion(binding.dipersion.getText().toString());
                  flora.setDistribucion(binding.distribucion.getText().toString());
                  flora.setExpresion_sexual(binding.sexualExpresion.getText().toString());
                  flora.setFamilia(binding.familia.getText().toString());
                  flora.setPolinizacion(binding.polinizacion.toString());
                  flora.setFitosociologia(binding.fitosociologia.getText().toString());
                  flora.setFructificacion(binding.fructificacion.getText().toString());
                  flora.setNumero_cromosomatico(binding.numeroCromosmatico.getText().toString());

                  avm.createFlora(flora);
              }
          });
      }



}