package com.johannfjs.mod3class2;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.johannfjs.mod3class2.database.MetodosRealm;
import com.johannfjs.mod3class2.entidades.UbicacionEntidad;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etTitulo)
    EditText etTitulo;
    @BindView(R.id.etDescripcion)
    EditText etDescripcion;
    @BindView(R.id.etLatitud)
    EditText etLatitud;
    @BindView(R.id.etLongitud)
    EditText etLongitud;
    @BindView(R.id.etBuscar)
    EditText etBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnMostrar)
    public void onBtnMostrarClicked() {

        Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.btnRegistrar)
    public void onBtnRegistrarClicked() {

        String titulo = etTitulo.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String latitud = etLatitud.getText().toString();
        String longitud = etLongitud.getText().toString();

        UbicacionEntidad ubicacionEntidad = new UbicacionEntidad();
        ubicacionEntidad.setId(MetodosRealm.nextId());
        ubicacionEntidad.setTitulo(titulo);
        ubicacionEntidad.setDescripcion(descripcion);
        ubicacionEntidad.setLatitud(Double.parseDouble(latitud));
        ubicacionEntidad.setLongitud(Double.parseDouble(longitud));

        MetodosRealm.registrar(ubicacionEntidad);

        Toast.makeText(this, "Se registro correctamente", Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.btnBuscar)
    public void onViewClicked() {

        String direccion = etBuscar.getText().toString();

        Geocoder geocoder = new Geocoder(MainActivity.this);
        try {
            List<Address> lista = geocoder.getFromLocationName(direccion, 1);

            if (lista != null && lista.size() > 0) {

                Address address = lista.get(0);

                if (address != null) {
                    etLatitud.setText(String.valueOf(address.getLatitude()));
                    etLongitud.setText(String.valueOf(address.getLongitude()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
