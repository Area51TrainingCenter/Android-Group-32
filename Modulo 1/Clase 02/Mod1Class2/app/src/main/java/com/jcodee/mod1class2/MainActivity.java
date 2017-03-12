package com.jcodee.mod1class2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText nombre, apellido;
    private Button procesar, siguiente;
    private TextView resultado;
    public static String valorNombre = "", valorApellido = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Vinculamos las variables que creamos en la clase java a los componentes
        //que tenemos en el diseño
        nombre = (EditText) findViewById(R.id.etNombre);
        apellido = (EditText) findViewById(R.id.etApellido);
        procesar = (Button) findViewById(R.id.btnProcesar);
        siguiente = (Button) findViewById(R.id.btnSiguiente);
        resultado = (TextView) findViewById(R.id.tvResultado);
    }

    @Override
    protected void onResume() {
        super.onResume();

        procesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager =
                        (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                //Obtenemos los datos de la caja de texto
                String valorNombre = nombre.getText().toString();
                String valorApellido = apellido.getText().toString();

                //Validamos que se hayan ingresado datos en las cajas de texto
                if (valorNombre.trim().length() > 0 &&
                        valorApellido.trim().length() > 0) {
                    //Pintamos el resultado en el TextView
                    resultado.setText(valorNombre + " " + valorApellido);
                    //Limpiar cajas de texto
                    nombre.setText("");
                    apellido.setText("");
                    //Indicar el cursor en la primera caja de texto
                    nombre.requestFocus();
                } else {
                    //Mostramos un mensaje para indicar que ingrese datos
                    Toast.makeText(MainActivity.this,
                            "Ingrese nombre y apellido.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Obtenemos los datos de el nombre y apellido
                String valorNombre = nombre.getText().toString();
                String valorApellido = apellido.getText().toString();

                if (valorNombre.trim().length() > 0 &&
                        valorApellido.trim().length() > 0) {
                    //Asignando el valor que se ha ingresado antes de enviarlo
                    MainActivity.valorNombre = valorNombre;
                    MainActivity.valorApellido = valorApellido;

                    //Decimos en que pantalla estamos y a que pantalla estamos pasando
                    Intent intent = new Intent(MainActivity.this, SiguienteActivity.class);

                    //Enviamos los parametros a la siguiente pantalla
                    intent.putExtra("nombre", valorNombre);
                    intent.putExtra("apellido", valorApellido);

                    //Ejecutamos la acción a realizar
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this,
                            "Ingrese nombre y apellido.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
        //addTextChangeListener
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if (valorNombre.length() > 0) {
            nombre.setText(valorNombre);
        }
        if (valorApellido.length() > 0) {
            apellido.setText(valorApellido);
        }
    }
}
