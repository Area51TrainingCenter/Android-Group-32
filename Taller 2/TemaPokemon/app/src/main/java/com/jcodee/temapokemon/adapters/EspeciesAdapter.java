package com.jcodee.temapokemon.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodee.temapokemon.R;
import com.jcodee.temapokemon.entidades.EspecieEntidad;
import com.jcodee.temapokemon.entidades.GeneracionEntidad;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by johannfjs on 24/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class EspeciesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private RealmList<EspecieEntidad> lista;

    public EspeciesAdapter(Context context, RealmList<EspecieEntidad> lista) {
        this.context = context;
        this.lista = lista;
    }

    class EspecieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvEspecie)
        TextView tvEspecie;
        @BindView(R.id.cvEspecie)
        CardView cvEspecie;

        public EspecieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_especie, parent, false);
        return new EspecieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        EspecieViewHolder viewHolder = (EspecieViewHolder) holder;
        if (viewHolder != null) {
            EspecieEntidad especieEntidad = lista.get(position);
            viewHolder.tvEspecie.setText(especieEntidad.getName());
            viewHolder.cvEspecie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
