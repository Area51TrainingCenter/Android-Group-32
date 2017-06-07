package com.jcodee.mod2class4.fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jcodee.mod2class4.R;
import com.jcodee.mod2class4.rest.HelperWS;
import com.jcodee.mod2class4.rest.TypicodeWS;
import com.jcodee.mod2class4.rest.response.PostResponse;
import com.jcodee.mod2class4.rest.response.UserResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroPostFragment extends Fragment {


    @BindView(R.id.spUsuario)
    Spinner spUsuario;
    @BindView(R.id.etTitulo)
    EditText etTitulo;
    @BindView(R.id.etCuerpo)
    EditText etCuerpo;
    Unbinder unbinder;
    private ArrayList<UserResponse> listaUsuario;

    public RegistroPostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registro_post, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        TypicodeWS typicodeWS = HelperWS.obtenerConfiguracion().create(TypicodeWS.class);
        Call<ArrayList<UserResponse>> usuarios = typicodeWS.obtenerUsuarios();
        usuarios.enqueue(new Callback<ArrayList<UserResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<UserResponse>> call, Response<ArrayList<UserResponse>> response) {
                //Obtenemos los datos que han sido devueltos por el web service
                listaUsuario = response.body();
                //Verificamos que traigan datos
                if (listaUsuario != null) {
                    //Creamos una lista donde almacenaremos los textos
                    ArrayList<String> lista = new ArrayList<String>();
                    //Recorremos el listado
                    for (UserResponse item : listaUsuario) {
                        //Agregamos los textos en la lista
                        lista.add(item.getName());
                    }
                    //Creamos el adapter que iterara nuestra lista
                    ArrayAdapter arrayAdapter = new ArrayAdapter(
                            getActivity(),
                            android.R.layout.simple_spinner_dropdown_item,
                            lista
                    );
                    //Seteamos el adapter de nuestro spinner
                    spUsuario.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserResponse>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnEjecutar)
    public void onViewClicked() {

        String usuario = spUsuario.getSelectedItem().toString();
        String titulo = etTitulo.getText().toString();
        String cuerpo = etCuerpo.getText().toString();

        int idUsuario = 0;

        for (UserResponse item : listaUsuario) {
            if (item.getName().equals(usuario)) {
                idUsuario = item.getId();
                break;
            }
        }
        TypicodeWS typicodeWS = HelperWS.obtenerConfiguracion().create(TypicodeWS.class);
        Call<PostResponse> post = typicodeWS.insertarPost(titulo, cuerpo, idUsuario);
        post.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                PostResponse resultado = response.body();
                if (resultado != null) {
                    Toast.makeText(getActivity(),
                            "Código de registro -> " + resultado.getId(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {

            }
        });

    }
}
