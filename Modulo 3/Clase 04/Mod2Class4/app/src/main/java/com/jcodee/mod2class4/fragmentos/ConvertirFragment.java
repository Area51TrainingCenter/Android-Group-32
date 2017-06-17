package com.jcodee.mod2class4.fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jcodee.mod2class4.R;
import com.jcodee.mod2class4.modelos.PersonaModelo;
import com.jcodee.mod2class4.rest.response.EmpresaResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConvertirFragment extends Fragment {


    @BindView(R.id.etNombre)
    EditText etNombre;
    @BindView(R.id.etApellido)
    EditText etApellido;
    @BindView(R.id.etCorreo)
    EditText etCorreo;
    @BindView(R.id.etDireccion)
    EditText etDireccion;
    @BindView(R.id.tvResultado)
    TextView tvResultado;
    Unbinder unbinder;

    public ConvertirFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_convertir, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnConvertir)
    public void onViewClicked() {

        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        String correo = etCorreo.getText().toString();
        String direccion = etDireccion.getText().toString();

        PersonaModelo personaModelo = new PersonaModelo();
        personaModelo.setNombre(nombre);
        personaModelo.setApellido(apellido);
        personaModelo.setCorreo(correo);
        personaModelo.setDireccion(direccion);
        //EmpresaResponse empresaResponse = new EmpresaResponse();
        //empresaResponse.setNumeroRuc("1234567890");
        //personaModelo.setEmpresa(empresaResponse);

        //declarar un objeto de tipo gson
        Gson gson = new Gson();
        //Convertir el objeto a un formato json
        String resultado = gson.toJson(personaModelo);
        //Pintar el resultado en el textView
        tvResultado.setText(resultado);

        //Convertir un json a un objeto java
        PersonaModelo modelo = gson.fromJson(resultado, PersonaModelo.class);

    }
}
