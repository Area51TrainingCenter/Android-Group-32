package com.jcodee.mod1class2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by johannfjs on 11/03/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class SiguienteActivity extends AppCompatActivity {
    private Spinner estadoCivil;
    private EditText nombre, apellido, direccion;
    private RadioButton masculino, femenino;
    private CheckBox alertas;
    private Button procesar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Asignamos el diseño del activity
        setContentView(R.layout.activity_siguiente);

        estadoCivil = (Spinner) findViewById(R.id.spEstadoCivil);
        nombre = (EditText) findViewById(R.id.etNombre);
        apellido = (EditText) findViewById(R.id.etApellido);
        direccion = (EditText) findViewById(R.id.etDireccion);
        masculino = (RadioButton) findViewById(R.id.rbMasculino);
        femenino = (RadioButton) findViewById(R.id.rbFemenino);
        alertas = (CheckBox) findViewById(R.id.cbAlerta);
        procesar = (Button) findViewById(R.id.btnProcesar);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //verificamos si es que se han enviado datos
        if (getIntent() != null) {
            //Consultamos si el key que enviamos se está obteniendo aqui
            if (getIntent().hasExtra("nombre")) {
                //Obtenemos el dato
                String nombreParametro = getIntent().getStringExtra("nombre");
                nombre.setText(nombreParametro);
            }
            if (getIntent().hasExtra("apellido")) {
                String apellidoParametro = getIntent().getStringExtra("apellido");
                apellido.setText(apellidoParametro);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String valorNombre = nombre.getText().toString();
                MainActivity.valorNombre = valorNombre;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        apellido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String valorApellido = apellido.getText().toString();
                MainActivity.valorApellido = valorApellido;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        /*
         ->Indu         http://www.androidhive.info/
         http://www.androidbegin.com/
         ->China        http://www.mkyong.com/

         Repositorios
         Descargar      https://git-scm.com/
         Publico        https://github.com/
         Privado        https://bitbucket.org/
         Privado        https://gitlab.com/
         */

        procesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String estadoCivilParametro = estadoCivil.getSelectedItem().toString();
                String nombreParametro = nombre.getText().toString();
                String apellidoParametro = apellido.getText().toString();
                String direccionParametro = direccion.getText().toString();
                boolean masculinoParametro = masculino.isChecked();
                boolean femeninoParametro = femenino.isChecked();
                boolean alertaParametro = alertas.isChecked();

                if (estadoCivil.getSelectedItemPosition() > 0 &&
                        estadoCivilParametro.trim().length() > 0 &&
                        nombreParametro.trim().length() > 0 &&
                        !apellidoParametro.isEmpty() &&
                        !direccionParametro.equals("")) {

                    Intent intent = new Intent(SiguienteActivity.this, DetalleActivity.class);
                    intent.putExtra("estado_civil", estadoCivilParametro);
                    intent.putExtra("nombre", nombreParametro);
                    intent.putExtra("apellido", apellidoParametro);
                    intent.putExtra("direccion", direccionParametro);
                    intent.putExtra("genero",
                            masculinoParametro ? "Masculino" :
                                    (femeninoParametro ? "Femenino" : ""));
                    intent.putExtra("alerta", alertaParametro ? "Si" : "No");

                    /*
                    (pregunta)?valor positivo:valor negativo
                    */

                    startActivity(intent);

                } else {
                    Toast.makeText(SiguienteActivity.this,
                            "Llenar todos los datos", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
