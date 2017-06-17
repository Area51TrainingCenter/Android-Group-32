package com.jcodee.temapokemon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import com.jcodee.temapokemon.rest.HelperWS;
import com.jcodee.temapokemon.rest.ServicioWS;
import com.jcodee.temapokemon.rest.response.GeneracionResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etGeneracion)
    EditText etGeneracion;
    @BindView(R.id.rvGeneraciones)
    RecyclerView rvGeneraciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnBuscar)
    public void onViewClicked() {

        String generacion = etGeneracion.getText().toString();

        ServicioWS servicioWS = HelperWS.obtenerConfiguracion().create(ServicioWS.class);
        Call<GeneracionResponse> result =
                servicioWS.obtenerGeneracion(Integer.parseInt(generacion));
        result.enqueue(new Callback<GeneracionResponse>() {
            @Override
            public void onResponse(Call<GeneracionResponse> call, Response<GeneracionResponse> response) {
                GeneracionResponse generacionResponse = response.body();
                if (generacionResponse != null) {
                    Toast.makeText(MainActivity.this,
                            "datos obtenidos " + generacionResponse.getName(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GeneracionResponse> call, Throwable t) {

            }
        });

    }
}
