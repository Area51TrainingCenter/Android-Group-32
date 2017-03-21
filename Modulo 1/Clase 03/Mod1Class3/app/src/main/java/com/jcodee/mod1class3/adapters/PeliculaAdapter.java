package com.jcodee.mod1class3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jcodee.mod1class3.R;
import com.jcodee.mod1class3.modelos.Pelicula;

import java.util.ArrayList;

/**
 * Created by johannfjs on 18/03/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class PeliculaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Pelicula> lista;

    public PeliculaAdapter(Context context, ArrayList<Pelicula> lista) {
        this.context = context;
        this.lista = lista;
    }

    //Cantidad de elementos que tiene la lista
    @Override
    public int getCount() {
        return lista.size();
    }

    //Obtenemos un objeto de la lista
    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    //Obtenemos un identificador unico de un objeto que tiene la lista
    @Override
    public long getItemId(int position) {
        return lista.get(position).getId();
    }

    //Obtenemos el diseño a mostrar en el listado
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //el LayoutInflater es como el setContentView en el activity
        convertView = LayoutInflater.from(context)
                .inflate(R.layout.item_pelicula, parent, false);

        //Vinculamos los componentes con las variables que hemos creado
        TextView pelicula = (TextView) convertView.findViewById(R.id.tvPelicula);
        TextView critica = (TextView) convertView.findViewById(R.id.tvCritica);
        TextView estado = (TextView) convertView.findViewById(R.id.tvEstado);

        //Obtener los datos que vamos a mostrar
        Pelicula datos = (Pelicula) getItem(position);

        //Cambiamos los datos a mostrar
        pelicula.setText(datos.getNombre());
        critica.setText(datos.getCritica());
        estado.setText(datos.getEstado());

        /*
        for(Pelicula item:datos.getUsuarios()){
            item.getNombre();
        }
        */

        return convertView;
    }
}
