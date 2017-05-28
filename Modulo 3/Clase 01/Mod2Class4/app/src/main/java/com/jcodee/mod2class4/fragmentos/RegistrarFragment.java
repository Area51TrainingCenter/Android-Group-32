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
import com.jcodee.mod2class4.database.MetodosRealm;
import com.jcodee.mod2class4.entidad.CicloEntidad;
import com.jcodee.mod2class4.entidad.CursoEntidad;
import com.jcodee.mod2class4.entidad.ModuloEntidad;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrarFragment extends Fragment {


    @BindView(R.id.etTitulo)
    EditText etTitulo;
    @BindView(R.id.spModulo)
    Spinner spModulo;
    @BindView(R.id.spCiclo)
    Spinner spCiclo;
    @BindView(R.id.etDescripcion)
    EditText etDescripcion;
    Unbinder unbinder;

    public RegistrarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registrar, container, false);
        unbinder = ButterKnife.bind(this, view);


        ArrayList<String> datosCiclo = new ArrayList<>();
        for (CicloEntidad item : MetodosRealm.obtenerCiclo()) {
            datosCiclo.add(item.getDescripcion());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(
                getActivity(), android.R.layout.simple_spinner_dropdown_item,
                datosCiclo);
        spCiclo.setAdapter(arrayAdapter);

        ArrayList<String> datosModulo = new ArrayList<>();
        for (ModuloEntidad item : MetodosRealm.obtenerModulos()) {
            datosModulo.add(item.getDescripcion());
        }
        ArrayAdapter arrayAdapterMopdulo = new ArrayAdapter(
                getActivity(), android.R.layout.simple_spinner_dropdown_item,
                datosModulo);
        spModulo.setAdapter(arrayAdapterMopdulo);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnRegistrar)
    public void onViewClicked() {

        String titulo = etTitulo.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String modulo = spModulo.getSelectedItem().toString();
        String ciclo = spCiclo.getSelectedItem().toString();

        CicloEntidad cicloEntidad = null;
        ModuloEntidad moduloEntidad = null;

        int idCiclo = 0;
        int idModulo = 0;

        for (ModuloEntidad item : MetodosRealm.obtenerModulos()) {
            if (item.getDescripcion().equals(modulo)) {
                moduloEntidad = item;
                idModulo = item.getId();
                break;
            }
        }
        for (CicloEntidad item : MetodosRealm.obtenerCiclo()) {
            if (item.getDescripcion().equals(ciclo)) {
                cicloEntidad = item;
                idCiclo = item.getId();
                break;
            }
        }

        CursoEntidad cursoEntidad = new CursoEntidad();
        cursoEntidad.setId(MetodosRealm.getNextId(cursoEntidad));
        cursoEntidad.setDescripcion(descripcion);
        cursoEntidad.setTitulo(titulo);
        cursoEntidad.setCiclo(cicloEntidad);
        cursoEntidad.setModulo(moduloEntidad);

        cursoEntidad.setIdCiclo(idCiclo);
        cursoEntidad.setIdModulo(idModulo);

        MetodosRealm.insertar(cursoEntidad);
        Toast.makeText(getActivity(), "Se registro correctamente",
                Toast.LENGTH_SHORT).show();


    }
}
