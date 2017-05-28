package com.jcodee.mod2class4.fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jcodee.mod2class4.R;
import com.jcodee.mod2class4.adapters.CursoAdapter;
import com.jcodee.mod2class4.database.MetodosRealm;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListadoFragment extends Fragment {


    @BindView(R.id.rvDatos)
    RecyclerView rvDatos;
    Unbinder unbinder;
    @BindView(R.id.etBuscar)
    EditText etBuscar;

    private CursoAdapter cursoAdapter;

    public ListadoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listado, container, false);
        unbinder = ButterKnife.bind(this, view);


        cursoAdapter = new CursoAdapter(getActivity(),
                MetodosRealm.obtenerCursos(
                        etBuscar.getText().toString()
                ));
        rvDatos.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvDatos.setAdapter(cursoAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnBuscar)
    public void onViewClicked() {

        cursoAdapter = new CursoAdapter(getActivity(),
                MetodosRealm.obtenerCursos(
                        etBuscar.getText().toString()
                ));
        rvDatos.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvDatos.setAdapter(cursoAdapter);

    }
}
