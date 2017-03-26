package com.jcodee.mod1class4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jcodee.mod1class4.modelos.Foto;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private Spinner spFoto;
    private EditText etTitulo, etDescripcion;
    private Button btnAlmacenar, btnVisualizar;

    public static ArrayList<Foto> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spFoto = (Spinner) findViewById(R.id.spFotos);
        etTitulo = (EditText) findViewById(R.id.etTitulo);
        etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        btnAlmacenar = (Button) findViewById(R.id.btnAlmacenar);
        btnVisualizar = (Button) findViewById(R.id.btnVisualizar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayAdapter arrayAdapter = new ArrayAdapter(
                MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.fotos)
        );
        spFoto.setAdapter(arrayAdapter);

        btnAlmacenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String foto = spFoto.getSelectedItem().toString();
                String titulo = etTitulo.getText().toString();
                String descripcion = etDescripcion.getText().toString();

                Foto item = new Foto();
                item.setId(lista.size());
                item.setTitulo(titulo);
                item.setDescripcion(descripcion);

                if (foto.equals("Foto 1")) {
                    item.setRutaFoto("res:/" + R.drawable.foto1);
                } else if (foto.equals("Foto 2")) {
                    item.setRutaFoto("res:/" + R.drawable.foto2);
                } else if (foto.equals("Foto 3")) {
                    item.setRutaFoto("res:/" + R.drawable.foto3);
                } else if (foto.equals("Foto 4")) {
                    item.setRutaFoto("res:/" + R.drawable.foto4);
                }
                lista.add(item);
                Toast.makeText(MainActivity.this, "Se registro correctamente.", Toast.LENGTH_SHORT).show();
/*
                int seleccionFoto = spFoto.getSelectedItemPosition();
                switch (seleccionFoto) {
                    case 0:
                    item.setRutaFoto("res:/" + R.drawable.foto1);
                        break;
                    case 1:
                    item.setRutaFoto("res:/" + R.drawable.foto2);
                        break;
                    case 2:
                    item.setRutaFoto("res:/" + R.drawable.foto3);
                        break;
                    case 3:
                    item.setRutaFoto("res:/" + R.drawable.foto4);
                        break;
                }
                */


            }
        });

        btnVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, GaleriaActivity.class);
                startActivity(intent);

            }
        });
    }
}
