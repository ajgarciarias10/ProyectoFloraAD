package com.ieszv.progmulti.proyectofloraad.ui.Flora;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ieszv.progmulti.proyectofloraad.databinding.FragmentSlideshowBinding;
import com.ieszv.progmulti.proyectofloraad.view.FloraAdapter;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initialize();

        return root;
    }

    private void initialize() {
        FloraAdapter floraAdapter = new FloraAdapter(getContext());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(floraAdapter);

        slideshowViewModel.getFloraLiveData().observe(this, floraPlural -> {
            Log.v("xyzyx", floraPlural.toString());
            floraAdapter.setFloraList(floraPlural);


        });
        slideshowViewModel.getFlora();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}