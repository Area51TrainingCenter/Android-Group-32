package com.jcodee.mod1class4.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodee.mod1class4.R;
import com.jcodee.mod1class4.modelos.Foto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johannfjs on 25/03/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class GaleriaAdapter extends ArrayAdapter<Foto> {
    private Context context;
    private ArrayList<Foto> lista;

    public GaleriaAdapter(@NonNull Context context, @NonNull ArrayList<Foto> objects) {
        super(context, R.layout.item_galeria, objects);
        this.context = context;
        this.lista = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.item_galeria, parent, false);

        Foto foto = lista.get(position);

        TextView tvTitulo = (TextView) convertView.findViewById(R.id.tvTitulo);
        SimpleDraweeView sdvImagen = (SimpleDraweeView) convertView.findViewById(R.id.sdvImagen);

        tvTitulo.setText(foto.getTitulo());
        sdvImagen.setImageURI(Uri.parse(foto.getRutaFoto()));

        return convertView;
    }
}
