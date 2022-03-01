package com.ieszv.progmulti.proyectofloraad.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.ieszv.progmulti.proyectofloraad.model.Repository;
import com.ieszv.progmulti.proyectofloraad.model.entity.Flora;

public class EditFloraViewModel extends AndroidViewModel {

    private Repository repository;

    public EditFloraViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public MutableLiveData<Long> getEditFloraLiveData() {

        return repository.getEditFloraLiveData();
    }

    public void editFlora(long id, Flora flora) {
        repository.editFlora(id, flora);
    }
}