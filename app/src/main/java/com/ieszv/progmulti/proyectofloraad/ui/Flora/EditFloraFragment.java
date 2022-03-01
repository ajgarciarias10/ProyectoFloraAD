package com.ieszv.progmulti.proyectofloraad.ui.Flora;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.ieszv.progmulti.proyectofloraad.R;
import com.ieszv.progmulti.proyectofloraad.databinding.FragmentEditFloraBinding;
import com.ieszv.progmulti.proyectofloraad.model.entity.Flora;
import com.ieszv.progmulti.proyectofloraad.ui.Imagen.AddImageFragment;
import com.ieszv.progmulti.proyectofloraad.viewmodel.DeleteFloraViewModel;
import com.ieszv.progmulti.proyectofloraad.viewmodel.DeleteImagenViewModel;
import com.ieszv.progmulti.proyectofloraad.viewmodel.EditFloraViewModel;


public class EditFloraFragment extends Fragment {


    FragmentEditFloraBinding binding;
    Flora flora = new Flora();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditFloraBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    private void initialize() {
        Bundle bundle = new Bundle();
        bundle = getArguments();
        flora = bundle.getParcelable("flora");

        seteandFloras();

        EditFloraViewModel evm = new ViewModelProvider(this).get(EditFloraViewModel.class);
        evm.getEditFloraLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                if(aLong >= 0) {
                    NavHostFragment.findNavController(EditFloraFragment.this).navigate(R.id.action_editFloraFragment_to_nav_slideshow);
                }
            }
        });

        DeleteFloraViewModel dvm = new ViewModelProvider(this).get(DeleteFloraViewModel.class);
        dvm.getDeleteFloraLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                if(aLong >=0) {

                }
            }
        });

        DeleteImagenViewModel dvm2 = new ViewModelProvider(this).get(DeleteImagenViewModel.class);
        dvm2.getDeleteImagenLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                if(aLong >=0) {

                }
            }
        });


        binding.fabAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertEdit(evm);
            }
        });

        binding.fabCancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(EditFloraFragment.this).navigate(R.id.action_editFloraFragment_to_nav_slideshow);
            }
        });

        binding.flBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDelete(dvm2,dvm);


            }
        });
    }
    //METODOS ALERT BUILD DIALOG PARA LA CONFIRMACION O CANCELACION DE ACCIONES
    private void alertDelete(DeleteImagenViewModel dvm2 ,DeleteFloraViewModel dvm) {
        AlertDialog.Builder builder  = new AlertDialog.Builder(getContext());
        builder.setTitle(" ¿Are you sure to remove it??")
                .setMessage("The operation has been done successfully")
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NavHostFragment.findNavController(EditFloraFragment.this).navigate(R.id.action_editFloraFragment_to_nav_slideshow);
                    }
                })
                .setPositiveButton( android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dvm2.deleteImagen(flora.getId());
                        dvm.deleteFlora(flora.getId());
                        NavHostFragment.findNavController(EditFloraFragment.this).navigate(R.id.action_editFloraFragment_to_nav_slideshow);
                    }
                });
        builder.create().show();
    }
    private void alertEdit(EditFloraViewModel evm) {
        AlertDialog.Builder builder  = new AlertDialog.Builder(getContext());
        builder.setTitle(" ¿Are you sure to edit it??")
                .setMessage("The operation has been done successfully")
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NavHostFragment.findNavController(EditFloraFragment.this).navigate(R.id.action_editFloraFragment_to_nav_slideshow);
                    }
                })
                .setPositiveButton( android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
                            evm.editFlora(flora.getId(),editFlora());
                        }

                    }
                });
        builder.create().show();
    }
    private void seteandFloras() {
        binding.idFlora.setText(flora.getNombre());
        binding.familia.setText(flora.getFamilia());
        binding.ide.setText(flora.getIdentificacion());
        binding.attitude.setText(flora.getAltitud());
        binding.habitat.setText(flora.getHabitat());
        binding.fitosociologia.setText(flora.getFitosociologia());
        binding.biotipo.setText(flora.getBiotipo());
        binding.reproduccion.setText(flora.getBiologia_reproductiva());
        binding.floracion.setText(flora.getFloracion());
        binding.fructificacion.setText(flora.getFructificacion());
        binding.sexualExpresion.setText(flora.getExpresion_sexual());
        binding.polinizacion.setText(flora.getPolinizacion());
        binding.dipersion.setText(flora.getDispersion());
        binding.numeroCromosmatico.setText(flora.getNumero_cromosomatico());
        binding.reproduccionSexual.setText(flora.getReproduccion_asexual());
        binding.distribucion.setText(flora.getDistribucion());
        binding.biologia.setText(flora.getBiologia());
        binding.demografA.setText(flora.getDemografia());
        binding.medidasPropuestas.setText(flora.getMedidas_propuestas());
        binding.threats.setText(flora.getAmenazas());
    }
    private Flora editFlora(){
        Flora flora = new Flora();
        String nombre= binding.idFlora.getText().toString(),
        familia =binding.familia.getText().toString(),
        identificacion =  binding.ide.getText().toString(),
        altitud = binding.attitude.getText().toString(),
        habitat = binding.habitat.getText().toString(),
        fitosociologia =binding.fitosociologia.getText().toString() ,
        biotipo =binding.biotipo.getText().toString() ,
        biologia =binding.biologia.getText().toString() ,
        biologia_reproductiva = binding.reproduccion.getText().toString() ,
        floracion = binding.floracion.getText().toString() ,
        fructificacion = binding.fructificacion.getText().toString(),
        expresion_sexual = binding.sexualExpresion.getText().toString(),
        polinizacion = binding.polinizacion.getText().toString() ,
        dispersion = binding.dipersion.getText().toString(),
        numero_cromosomatico = binding.numeroCromosmatico.getText().toString(),
        reproduccion_asexual = binding.reproduccionSexual.getText().toString(),
        distribucion = binding.distribucion.getText().toString(),
        demografia = binding.demografA.getText().toString(),
        amenzas = binding.threats.getText().toString(),
        medidas_propuestas = binding.medidasPropuestas.getText().toString();

        flora.setNombre(nombre);
        flora.setFamilia(familia);
        flora.setIdentificacion(identificacion);
        flora.setAltitud(altitud);
        flora.setHabitat(habitat);
        flora.setFitosociologia(fitosociologia);
        flora.setBiotipo(biotipo);
        flora.setBiologia(biologia);
        flora.setBiologia_reproductiva(biologia_reproductiva);
        flora.setFloracion(floracion);
        flora.setFructificacion(fructificacion);
        flora.setExpresion_sexual(expresion_sexual);
        flora.setPolinizacion(polinizacion);
        flora.setDispersion(dispersion);
        flora.setNumero_cromosomatico(numero_cromosomatico);
        flora.setReproduccion_asexual(reproduccion_asexual);
        flora.setDistribucion(distribucion);
        flora.setDemografia(demografia);
        flora.setAmenazas(amenzas);
        flora.setMedidas_propuestas(medidas_propuestas);

        return flora;
    }
    public  static   boolean checkingEMPTYFIELDs (TextInputEditText editalo){
        if(editalo.getText().toString().isEmpty() || editalo.getText().toString() == null){
            editalo.setError("Error debes rellenar el campo de texto");
            return false;
        }
        else{
            return true;
        }
    }
}