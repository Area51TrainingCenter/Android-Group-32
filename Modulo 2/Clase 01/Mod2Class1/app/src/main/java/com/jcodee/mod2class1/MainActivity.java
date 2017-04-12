package com.jcodee.mod2class1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jcodee.mod2class1.database.SentenciaSQL;
import com.jcodee.mod2class1.modelos.CategoriaModelo;
import com.jcodee.mod2class1.modelos.NoticiaModelo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Spinner spCategoria;
    private EditText etTitulo, etNoticia;
    private Button btnAgregar, btnVisualizar;

    private SentenciaSQL sentenciaSQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spCategoria = (Spinner) findViewById(R.id.spCategoria);
        etTitulo = (EditText) findViewById(R.id.etTitulo);
        etNoticia = (EditText) findViewById(R.id.etNoticia);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnVisualizar = (Button) findViewById(R.id.btnVisualizar);

        sentenciaSQL = new SentenciaSQL(MainActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Obtenemos los datos de la base de datos
        final ArrayList<CategoriaModelo> lista = sentenciaSQL.obtenerCategorias();
        //Creamos una lista de String donde vamos a obtener los textos de la categoría a mostrar
        ArrayList<String> categorias = new ArrayList<>();
        //Llenamos la lista con los textos a mostrar
        for (CategoriaModelo item : lista) {
            categorias.add(item.getDescripcion());
        }
        ArrayAdapter adapter = new ArrayAdapter(
                MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                categorias);
        spCategoria.setAdapter(adapter);


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String categoria = spCategoria.getSelectedItem().toString();
                String titulo = etTitulo.getText().toString();
                String noticia = etNoticia.getText().toString();
                int idCategoria = 0;

                for (CategoriaModelo item : lista) {
                    if (item.getDescripcion().equals(categoria)) {
                        idCategoria = item.getId();
                    }
                }

                NoticiaModelo noticiaModelo = new NoticiaModelo();
                noticiaModelo.setIdCategoria(idCategoria);
                noticiaModelo.setTitulo(titulo);
                noticiaModelo.setNoticia(noticia);
                sentenciaSQL.registraNoticia(noticiaModelo);
                Toast.makeText(MainActivity.this, "Se registro la noticia.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        btnVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, VisualizarActivity.class);
                startActivity(intent);

            }
        });
    }
}
