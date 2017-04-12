package com.jcodee.mod1class4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.jcodee.mod1class4.adapters.GaleriaAdapter;
import com.jcodee.mod1class4.modelos.Foto;

public class GaleriaActivity extends AppCompatActivity {
    private GridView gvDatos;
    private GaleriaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        gvDatos = (GridView) findViewById(R.id.gvDatos);

    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter = new GaleriaAdapter(GaleriaActivity.this, MainActivity.lista);
        gvDatos.setAdapter(adapter);

        gvDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*
                Foto foto = MainActivity.lista.get(position);

                Intent intent = new Intent(GaleriaActivity.this, DetalleActivity.class);
                intent.putExtra("ruta_foto", foto.getRutaFoto());
                intent.putExtra("titulo", foto.getTitulo());
                intent.putExtra("descripcion", foto.getDescripcion());
                startActivity(intent);
                */
                Intent intent = new Intent(GaleriaActivity.this, DetalleGaleriaActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }
}
