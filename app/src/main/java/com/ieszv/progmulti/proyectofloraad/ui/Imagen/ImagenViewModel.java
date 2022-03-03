package com.ieszv.progmulti.proyectofloraad.ui.Imagen;

import android.app.Application;
import android.media.Image;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ieszv.progmulti.proyectofloraad.model.Repository;
import com.ieszv.progmulti.proyectofloraad.model.entity.Flora;

import java.util.ArrayList;

public class ImagenViewModel extends AndroidViewModel {
    private Repository repository;

    private MutableLiveData<String> mText;

    public ImagenViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        mText = new MutableLiveData<>();

    }

    public MutableLiveData<ArrayList<Image>> getImagenLiveData() {
        return repository.getImagenLiveData();
    }

    public void getFlora() {
        repository.getFlora();
    }
}
