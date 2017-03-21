package com.jcodee.mod1class3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.jcodee.mod1class3.modelos.Pelicula;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Spinner pelicula;
    private RadioButton buena, mala;
    private EditText critica;
    private CheckBox terminos;
    private Button registro, listado;

    public static ArrayList<Pelicula> listaDatos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referenciamos las variables al diseño que hemos creado
        pelicula = (Spinner) findViewById(R.id.spPeliculas);
        buena = (RadioButton) findViewById(R.id.rbBuena);
        mala = (RadioButton) findViewById(R.id.rbMala);
        critica = (EditText) findViewById(R.id.etCritica);
        terminos = (CheckBox) findViewById(R.id.cbTerminos);
        registro = (Button) findViewById(R.id.btnRegistro);
        listado = (Button) findViewById(R.id.btnListado);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Cargar un spinner desde código Java
        ArrayList<String> datosPeliculas = new ArrayList<>();
        datosPeliculas.add("Pelicula 1");
        datosPeliculas.add("Pelicula 2");
        datosPeliculas.add("Pelicula 3");
        datosPeliculas.add("Pelicula 4");
        datosPeliculas.add("Pelicula 5");

        //Es la estructura que va a tener nuestro Spinner
        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                datosPeliculas);
        //Le indicamos al spinner que la estructura de datos ha cambiado por la que
        //nosotros hemos creado
        pelicula.setAdapter(arrayAdapter);

        //Evento onClick de nuestro boton de registro
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String valorPelicula = pelicula.getSelectedItem().toString();
                //Preguntamos si el radioButton de Buena esta seleccionada, para
                // mostrar el texto Buena, pero si el que esta seleccionado es
                // Mala se mantiene el texto Mala
                String valorEstado =
                        buena.isChecked() ? "Buena" :
                                mala.isChecked() ? "Mala" : "";
                String valorCritica = critica.getText().toString();
                boolean valorTerminos = terminos.isChecked();

                //Iniciamos que el campo de EditText no tendrá error
                critica.setError(null);
                //Validamos si es que todos los datos se han ingresado
                if (!valorPelicula.isEmpty() &&
                        !valorEstado.isEmpty() &&
                        !valorCritica.isEmpty() &&
                        valorTerminos) {

                    //Cerrar el teclado
                    InputMethodManager imm =
                            (InputMethodManager)
                                    getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(critica.getWindowToken(), 0);

                    //Agregamos los datos al listado y mostramos un mensaje de confirmación
                    Pelicula peliculaObjeto = new Pelicula();
                    peliculaObjeto.setId(listaDatos.size() + 1);
                    peliculaObjeto.setNombre(valorPelicula);
                    peliculaObjeto.setEstado(valorEstado);
                    peliculaObjeto.setCritica(valorCritica);
                    peliculaObjeto.setTerminos(valorTerminos);
                    listaDatos.add(peliculaObjeto);

                    //Mostramos mensaje de confirmación
                    Toast.makeText(MainActivity.this, "Se registro correctamente", Toast.LENGTH_SHORT).show();

                    //Limpiar campos
                    pelicula.setSelection(0);
                    buena.setChecked(true);
                    mala.setChecked(false);
                    critica.setText("");
                    terminos.setChecked(false);

                } else if (valorCritica.isEmpty()) {
                    //Si es que no tiene valor el campo de critica, se mostrará un
                    //mensaje de error
                    critica.setError("El campo es requerido.");
                } else {
                    Toast toast = Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.setText("Todos los campos son requeridos");
                    toast.show();
                }

            }
        });

        listado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pasamos de la pantalla actual a la siguiente
                Intent intent = new Intent(MainActivity.this, ListadoActivity.class);
                startActivity(intent);

            }
        });
    }
}
