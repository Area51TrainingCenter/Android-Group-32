package com.johannfjs.mod3class3;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.johannfjs.mod3class3.rest.GoogleWS;
import com.johannfjs.mod3class3.rest.HelperWS;
import com.johannfjs.mod3class3.rest.response.GeocodingResponse;
import com.johannfjs.mod3class3.rest.response.LocationResponse;
import com.johannfjs.mod3class3.rest.response.ResultResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etLatitud)
    EditText etLatitud;
    @BindView(R.id.etLongitud)
    EditText etLongitud;
    @BindView(R.id.etDireccion)
    EditText etDireccion;
    @BindView(R.id.tvDireccion)
    TextView tvDireccion;
    @BindView(R.id.sdvImagen)
    SimpleDraweeView sdvImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnBuscarPosicion)
    public void onBtnBuscarPosicionClicked() {

        String latitud = etLatitud.getText().toString();
        String longitud = etLongitud.getText().toString();

        GoogleWS googleWS = HelperWS.obtenerConfiguracion().create(GoogleWS.class);
        Call<GeocodingResponse> consulta = googleWS.obtenerLugares(latitud + "," + longitud);
        consulta.enqueue(new Callback<GeocodingResponse>() {
            @Override
            public void onResponse(Call<GeocodingResponse> call, Response<GeocodingResponse> response) {
                GeocodingResponse respuesta = response.body();
                if (respuesta != null) {
                    if (respuesta.getResults() != null &&
                            respuesta.getResults().size() > 0) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (ResultResponse item : respuesta.getResults()) {
                            stringBuilder.append(item.getFormatted_address());
                            stringBuilder.append("\n");
                        }
                        tvDireccion.setText(stringBuilder.toString());
                    }

                    Toast.makeText(MainActivity.this, "->" + respuesta.getStatus(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GeocodingResponse> call, Throwable t) {

            }
        });

    }

    @OnClick(R.id.btnBuscarDireccion)
    public void onBtnBuscarDireccionClicked() {

        String direccion = etDireccion.getText().toString();

        GoogleWS googleWS = HelperWS.obtenerConfiguracion().create(GoogleWS.class);
        Call<GeocodingResponse> consulta = googleWS.obtenerGeolocalizacion(
                direccion,
                getResources().getString(R.string.api_key));
        consulta.enqueue(new Callback<GeocodingResponse>() {
            @Override
            public void onResponse(Call<GeocodingResponse> call, Response<GeocodingResponse> response) {
                GeocodingResponse respuesta = response.body();
                if (respuesta != null) {
                    /*
                    status      string boolean
                    data        [] {}
                    error       string {}
                     */
                    if (respuesta.getResults() != null &&
                            respuesta.getResults().size() > 0) {
                        tvDireccion.setText(respuesta.getResults().get(0).getFormatted_address());

                        LocationResponse locationResponse = respuesta
                                .getResults()
                                .get(0)
                                .getGeometry()
                                .getLocation();

                        String ruta =
                                obtenerImagen(locationResponse.getLat().toString(),
                                        locationResponse.getLng().toString());

                        sdvImagen.setImageURI(Uri.parse(ruta));
                    }
                    Toast.makeText(MainActivity.this, "->" + respuesta.getStatus(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GeocodingResponse> call, Throwable t) {

            }
        });
    }

    private String obtenerImagen(String latitud, String longitud) {
        return "https://maps.googleapis.com/maps/api/staticmap?center=" +
                latitud + "," + longitud + "&zoom=15&size=600x600" +
                "&key=" + getResources().getString(R.string.api_key);
    }
}
