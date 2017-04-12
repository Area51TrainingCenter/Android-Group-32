package com.jcodee.mod2class1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jcodee.mod2class1.R;
import com.jcodee.mod2class1.modelos.NoticiaModelo;

import java.util.ArrayList;

/**
 * Created by johannfjs on 8/04/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class NoticiaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<NoticiaModelo> lista;

    public NoticiaAdapter(Context context, ArrayList<NoticiaModelo> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lista.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView =
                    LayoutInflater.from(context).inflate(R.layout.item_lista, parent, false);
        }
        TextView tvCategoria = (TextView) convertView.findViewById(R.id.tvCategoria);
        TextView tvTitulo = (TextView) convertView.findViewById(R.id.tvTitulo);
        TextView tvNoticia = (TextView) convertView.findViewById(R.id.tvNoticia);

        NoticiaModelo noticiaModelo = (NoticiaModelo) getItem(position);
        tvCategoria.setText(noticiaModelo.getCategoria());
        tvTitulo.setText(noticiaModelo.getTitulo());
        tvNoticia.setText(noticiaModelo.getNoticia());

        return convertView;
    }
}
