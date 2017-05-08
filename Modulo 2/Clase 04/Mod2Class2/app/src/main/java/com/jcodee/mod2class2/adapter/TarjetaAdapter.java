package com.jcodee.mod2class2.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodee.mod2class2.EditarActivity;
import com.jcodee.mod2class2.R;
import com.jcodee.mod2class2.database.MetodosRealm;
import com.jcodee.mod2class2.entidades.TarjetaEntidad;
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
        @BindView(R.id.cvTarjeta)
        CardView cvTarjeta;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Tarjeta item = lista.get(position);
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        if (myViewHolder != null) {
            myViewHolder.tvNumero.setText(item.getNumero());
            myViewHolder.tvFecha.setText(item.getFecha());
            myViewHolder.tvCvv.setText(item.getCvv());

            myViewHolder.cvTarjeta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setTitle("Opciones");
                    alert.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(context, EditarActivity.class);
                            intent.putExtra("idTarjeta", item.getId());
                            context.startActivity(intent);

                        }
                    });
                    alert.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            /*
                            TarjetaEntidad tarjetaEntidad = new TarjetaEntidad();
                            tarjetaEntidad.setId(item.getId());
                            tarjetaEntidad.setCvv(item.getCvv());
                            tarjetaEntidad.setFecha(item.getFecha());
                            tarjetaEntidad.setNumero(item.getNumero());
                            MetodosRealm.delete(tarjetaEntidad);
                            */

                            //Eliminamos el registro de la base de datos
                            MetodosRealm.delete(item.getId());
                            //Eliminamos el registro del diseño de la lista
                            lista.remove(position);
                            //Actualizamos los registros a mostrar
                            notifyDataSetChanged();

                        }
                    });
                    alert.create().show();

                }
            });
        }
    }

    //Cantidad de elementos que hay en el listado
    @Override
    public int getItemCount() {
        return lista.size();
    }
}
