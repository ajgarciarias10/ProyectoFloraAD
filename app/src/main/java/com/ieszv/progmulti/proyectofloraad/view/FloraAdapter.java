package com.ieszv.progmulti.proyectofloraad.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ieszv.progmulti.proyectofloraad.R;
import com.ieszv.progmulti.proyectofloraad.model.entity.Flora;
import com.ieszv.progmulti.proyectofloraad.view.viewholder.ViewHolderFlora;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FloraAdapter extends RecyclerView.Adapter<ViewHolderFlora> {
    private Flora flora = new Flora();
    private Context context;
    private List<Flora> floraList;
    public FloraAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderFlora onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolderFlora(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFlora holder, int position) {
        Flora flora = floraList.get(position);
        holder.flora = flora;
        String url ="https://informatica.ieszaidinvergeles.org:10020/AD/felixRDLFApp/public/api/imagen/" + flora.getId() + "/flora";
        holder.tv_id.setText(String.valueOf(flora.getId()));
        holder.tv_flora.setText(flora.getBiotipo());
        holder.tv_nombre.setText(flora.getNombre());
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(holder.iv_Flora);

        Log.v("xyz",url);

    }

    @Override
    public int getItemCount() {
        if(floraList == null) {
            return 0;
        }
        return floraList.size();
    }

    public void setFloraList(List<Flora> floraList) {
        this.floraList = floraList;
        notifyDataSetChanged();
    }

}