package com.ieszv.progmulti.proyectofloraad.ui.Flora;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ieszv.progmulti.proyectofloraad.model.Repository;
import com.ieszv.progmulti.proyectofloraad.model.entity.Flora;

import java.util.ArrayList;

public class SlideshowViewModel extends AndroidViewModel {
    private Repository repository;

    private MutableLiveData<String> mText;

    public SlideshowViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        mText = new MutableLiveData<>();

    }
    public MutableLiveData<ArrayList<Flora>> getFloraLiveData() {
        return repository.getFloraLiveData();
    }

    public void getFlora() {
        repository.getFlora();
    }
    public LiveData<String> getText() {
        return mText;
    }
}