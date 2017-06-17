package com.jcodee.mod2class4.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodee.mod2class4.R;
import com.jcodee.mod2class4.entidad.CursoEntidad;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by johannfjs on 13/05/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class CursoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private RealmResults<CursoEntidad> lista;

    public CursoAdapter(Context context, RealmResults<CursoEntidad> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final CursoEntidad cursoEntidad = lista.get(position);

        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvCiclo.setText(cursoEntidad.getCiclo().getDescripcion());
        viewHolder.tvModulo.setText(cursoEntidad.getModulo().getDescripcion());
        viewHolder.tvTitulo.setText(cursoEntidad.getTitulo());

        viewHolder.llElemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(cursoEntidad.getTitulo());
                builder.setMessage(
                        Html.fromHtml(cursoEntidad.getModulo().getDescripcion() +
                                "<br/>" +
                                cursoEntidad.getCiclo().getDescripcion() +
                                "<br/>" +
                                cursoEntidad.getDescripcion())
                );
                builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitulo)
        TextView tvTitulo;
        @BindView(R.id.tvModulo)
        TextView tvModulo;
        @BindView(R.id.tvCiclo)
        TextView tvCiclo;
        @BindView(R.id.llElemento)
        LinearLayout llElemento;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
