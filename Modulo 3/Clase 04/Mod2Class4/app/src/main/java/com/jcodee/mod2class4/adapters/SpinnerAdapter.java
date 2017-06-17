package com.jcodee.mod2class4.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jcodee.mod2class4.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by johannfjs on 10/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class SpinnerAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> datos;

    public SpinnerAdapter(Context context, ArrayList<String> datos) {
        this.context = context;
        this.datos = datos;
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_spinner, parent, false);
        String valor = datos.get(position);

        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tvTexto.setText(valor);

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tvTexto)
        TextView tvTexto;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
