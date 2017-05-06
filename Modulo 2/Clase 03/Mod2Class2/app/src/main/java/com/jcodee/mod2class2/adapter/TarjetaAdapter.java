package com.jcodee.mod2class2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodee.mod2class2.R;
import com.jcodee.mod2class2.modelos.Tarjeta;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by johannfjs on 29/04/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class TarjetaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Tarjeta> lista;

    public TarjetaAdapter(Context context, ArrayList<Tarjeta> lista) {
        this.context = context;
        this.lista = lista;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvNumero)
        TextView tvNumero;
        @BindView(R.id.tvCvv)
        TextView tvCvv;
        @BindView(R.id.tvFecha)
        TextView tvFecha;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //Para definir el diseño y las variables
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new MyViewHolder(view);
    }

    //Seteamos datos a mostrar
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Tarjeta item = lista.get(position);
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        if (myViewHolder != null) {
            myViewHolder.tvNumero.setText(item.getNumero());
            myViewHolder.tvFecha.setText(item.getFecha());
            myViewHolder.tvCvv.setText(item.getCvv());
        }
    }

    //Cantidad de elementos que hay en el listado
    @Override
    public int getItemCount() {
        return lista.size();
    }
}
