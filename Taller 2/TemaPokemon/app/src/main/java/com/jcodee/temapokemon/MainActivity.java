package com.jcodee.temapokemon;

import android.app.Notification;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodee.temapokemon.adapters.EspeciesAdapter;
import com.jcodee.temapokemon.database.MetodoRealm;
import com.jcodee.temapokemon.entidades.EspecieEntidad;
import com.jcodee.temapokemon.entidades.GeneracionEntidad;
import com.jcodee.temapokemon.rest.HelperWS;
import com.jcodee.temapokemon.rest.ServicioWS;
import com.jcodee.temapokemon.rest.response.EspecieResponse;
import com.jcodee.temapokemon.rest.response.GeneracionResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etGeneracion)
    EditText etGeneracion;
    @BindView(R.id.rvGeneraciones)
    RecyclerView rvGeneraciones;
    @BindView(R.id.tvGeneracion)
    TextView tvGeneracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnBuscar)
    public void onViewClicked() {

        String generacion = etGeneracion.getText().toString();

        if (VerificarInternet.exists(MainActivity.this)) {
            ServicioWS servicioWS = HelperWS.obtenerConfiguracion().create(ServicioWS.class);
            Call<GeneracionResponse> result =
                    servicioWS.obtenerGeneracion(Integer.parseInt(generacion));
            result.enqueue(new Callback<GeneracionResponse>() {
                @Override
                public void onResponse(Call<GeneracionResponse> call, Response<GeneracionResponse> response) {
                    GeneracionResponse generacionResponse = response.body();
                    if (generacionResponse != null) {
                        MetodoRealm.eliminarGeneracion();

                        GeneracionEntidad generacionEntidad = new GeneracionEntidad();
                        generacionEntidad.setNombre(generacionResponse.getName());
                        generacionEntidad.setId(generacionResponse.getId());

                        RealmList<EspecieEntidad> listaEspecies = new RealmList<EspecieEntidad>();
                        for (EspecieResponse item : generacionResponse.getPokemon_species()) {
                            EspecieEntidad especieEntidad = new EspecieEntidad();
                            especieEntidad.setName(item.getName());
                            especieEntidad.setUrl(item.getUrl());
                            listaEspecies.add(especieEntidad);
                        }

                        generacionEntidad.setEspecies(listaEspecies);
                        MetodoRealm.insertarGeneraciones(generacionEntidad);

                        //Toast.makeText(MainActivity.this,
                        //        "datos obtenidos " + generacionResponse.getName(), Toast.LENGTH_SHORT).show();
                    } else {
                        EspeciesAdapter especiesAdapter = new EspeciesAdapter(MainActivity.this, new RealmList<EspecieEntidad>());

                        rvGeneraciones.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        rvGeneraciones.setAdapter(especiesAdapter);
                    }
                    if (MetodoRealm.obtenerCantidadGeneracion() > 0) {
                        GeneracionEntidad generacionEntidad = MetodoRealm.obtenerGeneracion();
                        if (generacionEntidad != null) {
                            EspeciesAdapter especiesAdapter = new EspeciesAdapter(MainActivity.this, generacionEntidad.getEspecies());

                            rvGeneraciones.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            rvGeneraciones.setAdapter(especiesAdapter);
                            tvGeneracion.setText(generacionEntidad.getNombre());
                        }
                    } else {
                        Toast.makeText(MainActivity.this,
                                "No hay información disponible", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GeneracionResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this,
                            "No hay información disponible", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            GeneracionEntidad generacionEntidad = MetodoRealm.obtenerGeneracion();
            if (generacionEntidad != null) {
                EspeciesAdapter especiesAdapter = new EspeciesAdapter(MainActivity.this, generacionEntidad.getEspecies());

                rvGeneraciones.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                rvGeneraciones.setAdapter(especiesAdapter);
                tvGeneracion.setText(generacionEntidad.getNombre());
            } else {
                Toast.makeText(MainActivity.this,
                        "No hay información disponible", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
