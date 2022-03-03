package com.ieszv.progmulti.proyectofloraad.ui.Flora;
import static com.ieszv.progmulti.proyectofloraad.ui.Flora.EditFloraFragment.checkingEMPTYFIELDs;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.ieszv.progmulti.proyectofloraad.R;
import com.ieszv.progmulti.proyectofloraad.databinding.FragmentAddFloraBinding;
import com.ieszv.progmulti.proyectofloraad.model.entity.Flora;
import com.ieszv.progmulti.proyectofloraad.ui.MainActivity.MainActivity;
import com.ieszv.progmulti.proyectofloraad.viewmodel.AddFloraViewModel;
  public class AddFlora extends Fragment {
      public static int mode;
    Flora flora;
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
          mode = 0;
          AddFloraViewModel avm = new ViewModelProvider(this).get(AddFloraViewModel.class);
          avm.getAddFloraLiveData().observe(this, new Observer<Long>() {
              @Override
              public void onChanged(Long aLong) {
                  if(aLong > 0) {
                      mode=1;
                      NavHostFragment.findNavController(AddFlora.this)
                              .navigate(R.id.action_nav_add_flora_to_nav_add_Image);
                  } else {
                      Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                  }
              }
          });
          binding.fabCancel.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  NavHostFragment.findNavController(AddFlora.this)
                          .navigate(R.id.action_nav_add_flora_to_nav_add_Image);;
              }
          });

          binding.fabAdd.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(checkingEMPTYFIELDs(binding.idFlora)
                          || checkingEMPTYFIELDs(binding.familia)
                          ||  checkingEMPTYFIELDs(binding.ide)
                          || checkingEMPTYFIELDs(binding.attitude)
                          || checkingEMPTYFIELDs(binding.habitat)
                          || checkingEMPTYFIELDs(binding.fitosociologia)
                          || checkingEMPTYFIELDs(binding.biotipo)
                          || checkingEMPTYFIELDs(binding.biologia)
                          || checkingEMPTYFIELDs(binding.reproduccion)
                          ||checkingEMPTYFIELDs(binding.floracion)
                          || checkingEMPTYFIELDs( binding.fructificacion)
                          ||checkingEMPTYFIELDs(binding.sexualExpresion)
                          || checkingEMPTYFIELDs(binding.polinizacion)
                          || checkingEMPTYFIELDs(binding.dipersion)
                          ||checkingEMPTYFIELDs(binding.numeroCromosmatico)
                          || checkingEMPTYFIELDs(binding.reproduccionSexual)
                          || checkingEMPTYFIELDs(binding.distribucion)
                          || checkingEMPTYFIELDs(binding.demografA)
                          ||  checkingEMPTYFIELDs(binding.threats)
                          || checkingEMPTYFIELDs(binding.medidasPropuestas)
                  ) {
                      avm.createFlora(addFlora());
                  }


              }
          });
      }
    //region Metodo añadir Flora
      private Flora addFlora() {
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
          flora.setIdentificacion(binding.ide.getText().toString());
          flora.setDispersion(binding.dipersion.getText().toString());
          return  flora;

      }
    //endregion

  }