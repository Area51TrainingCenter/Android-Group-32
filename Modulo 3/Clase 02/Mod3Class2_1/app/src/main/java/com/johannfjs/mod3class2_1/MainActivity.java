package com.johannfjs.mod3class2_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.johannfjs.mod3class2_1.response.PostResponse;
import com.johannfjs.mod3class2_1.rest.HelperWS;
import com.johannfjs.mod3class2_1.rest.ServicioWS;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tvTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTexto = (TextView) findViewById(R.id.tvTexto);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ServicioWS servicioWS = HelperWS.configuracion().create(ServicioWS.class);
        Call<ArrayList<PostResponse>> result = servicioWS.posts();
        result.enqueue(new Callback<ArrayList<PostResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<PostResponse>> call, Response<ArrayList<PostResponse>> response) {
                ArrayList<PostResponse> datos = response.body();
                if (datos != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (PostResponse item : datos) {
                        if (item.getId() < 10) {
                            //item.getTitulo().contains("hola");
                            stringBuilder.append(item.getTitulo());
                            stringBuilder.append("\n");
                            stringBuilder.append(item.getCuerpo());
                            stringBuilder.append("\n--------\n");
                        }
                    }
                    tvTexto.setText(stringBuilder.toString());
                } else {
                    tvTexto.setText("No trajo datos");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PostResponse>> call, Throwable t) {

            }
        });

        //sdvImagen.setImageUri(Uri.parse("http://"))
    }
}
