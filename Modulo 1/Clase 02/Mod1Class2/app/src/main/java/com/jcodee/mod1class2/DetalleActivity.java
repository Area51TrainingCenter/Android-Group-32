package com.jcodee.mod1class2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by johannfjs on 11/03/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class DetalleActivity extends AppCompatActivity {
    private TextView estadoCivil, nombreCompleto, direccion, genero, alertas;
    private Button cerrarTodo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        estadoCivil = (TextView) findViewById(R.id.tvEstadoCivil);
        nombreCompleto = (TextView) findViewById(R.id.tvNombreCompleto);
        direccion = (TextView) findViewById(R.id.tvDireccion);
        genero = (TextView) findViewById(R.id.tvGenero);
        alertas = (TextView) findViewById(R.id.tvAlerta);
        cerrarTodo = (Button) findViewById(R.id.btnCerrarTodo);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getIntent() != null) {
            if (getIntent().hasExtra("estado_civil")) {
                String estadoCivilParametro = getIntent().getStringExtra("estado_civil");
                estadoCivil.setText(estadoCivilParametro);
            }
            if (getIntent().hasExtra("nombre") &&
                    getIntent().hasExtra("apellido")) {
                String nombreParametro = getIntent().getStringExtra("nombre");
                String apellidoParametro = getIntent().getStringExtra("apellido");
                nombreCompleto.setText(nombreParametro + " " + apellidoParametro);
            }
            if (getIntent().hasExtra("direccion")) {
                String direccionParametro = getIntent().getStringExtra("direccion");
                direccion.setText(direccionParametro);
            }
            if (getIntent().hasExtra("genero")) {
                String generoParametro = getIntent().getStringExtra("genero");
                genero.setText(generoParametro);
            }
            if (getIntent().hasExtra("alerta")) {
                String alertaParametro = getIntent().getStringExtra("alerta");
                alertas.setText(alertaParametro);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        cerrarTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetalleActivity.this, MainActivity.class);
                //Limpia todo el historial de pantallas
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //Limpia todas las tareas o eventos que se puedan haber estado ejecutando
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //Crea una nueva actividad como la primera
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            }
        });
    }
}
