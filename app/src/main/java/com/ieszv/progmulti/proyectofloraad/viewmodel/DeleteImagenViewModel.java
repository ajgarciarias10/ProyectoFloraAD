package com.ieszv.progmulti.proyectofloraad.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.ieszv.progmulti.proyectofloraad.model.Repository;

public class DeleteImagenViewModel extends AndroidViewModel {

    private Repository repository;

    public DeleteImagenViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public MutableLiveData<Long> getDeleteImagenLiveData() {
        return repository.getDeleteImagenLiveData();
    }

    public void deleteImagen(long id) {
        repository.deleteImagen(id);
    }
}
