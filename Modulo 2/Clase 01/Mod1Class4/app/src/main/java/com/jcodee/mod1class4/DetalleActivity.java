package com.jcodee.mod1class4;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

public class DetalleActivity extends AppCompatActivity {
    private SimpleDraweeView sdvImagen;
    private TextView tvTitulo, tvDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        sdvImagen = (SimpleDraweeView) findViewById(R.id.sdvImagen);
        tvTitulo = (TextView) findViewById(R.id.tvTitulo);
        tvDescripcion = (TextView) findViewById(R.id.tvDescripcion);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getIntent() != null) {
            if (getIntent().hasExtra("ruta_foto")) {
                String ruta = getIntent().getStringExtra("ruta_foto");
                sdvImagen.setImageURI(Uri.parse(ruta));
            }
            if (getIntent().hasExtra("titulo")) {
                String titulo = getIntent().getStringExtra("titulo");
                tvTitulo.setText(titulo);
            }
            if (getIntent().hasExtra("descripcion")) {
                String descripcion = getIntent().getStringExtra("descripcion");
                tvDescripcion.setText(descripcion);
            }
        }
    }
}
