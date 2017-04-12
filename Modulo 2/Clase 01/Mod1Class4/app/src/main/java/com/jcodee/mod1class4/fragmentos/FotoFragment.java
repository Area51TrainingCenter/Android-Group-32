package com.jcodee.mod1class4.fragmentos;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodee.mod1class4.MainActivity;
import com.jcodee.mod1class4.R;
import com.jcodee.mod1class4.modelos.Foto;

/**
 * A simple {@link Fragment} subclass.
 */
public class FotoFragment extends Fragment {
    private SimpleDraweeView sdvImagen;
    private TextView tvTitulo, tvDescripcion;

    public FotoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_foto, container, false);

        sdvImagen = (SimpleDraweeView) view.findViewById(R.id.sdvImagen);
        tvTitulo = (TextView) view.findViewById(R.id.tvTitulo);
        tvDescripcion = (TextView) view.findViewById(R.id.tvDescripcion);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        //Obtenemos el dato que estamos enviando a través del adapter
        Bundle bundle = getArguments();
        int position = bundle.getInt("position");

        //Obtenemos el objeto de tipo foto que se está seleccionando
        Foto foto = MainActivity.lista.get(position);
        tvTitulo.setText(foto.getTitulo());
        tvDescripcion.setText(foto.getDescripcion());
        sdvImagen.setImageURI(Uri.parse(foto.getRutaFoto()));
    }
}
