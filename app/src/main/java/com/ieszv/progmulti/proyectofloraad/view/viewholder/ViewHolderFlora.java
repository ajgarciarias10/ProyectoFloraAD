package com.ieszv.progmulti.proyectofloraad.view.viewholder;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.ieszv.progmulti.proyectofloraad.R;
import com.ieszv.progmulti.proyectofloraad.model.entity.Flora;


    public class ViewHolderFlora extends RecyclerView.ViewHolder {
        //Declaramos el objeto pasar luego al tocar el item
        public Flora flora;
        //Declaramos los texView a utilizar en el Adaptador
        public TextView tv_flora,tv_biotiopo,tv_id,tv_nombre;
        //Declaramos la imagen a utilizar en el Adaptador
        public ImageView iv_Flora;

        public ViewHolderFlora(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_flora = itemView.findViewById(R.id.tv_flora);
            tv_biotiopo = itemView.findViewById(R.id.tv_biotipo);
            iv_Flora = itemView.findViewById(R.id.iv_flora);
            tv_nombre = itemView.findViewById(R.id.tv_nombre);

        }
    }
