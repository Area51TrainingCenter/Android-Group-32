package com.jcodee.mod2class4.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jcodee.mod2class4.HomeActivity;
import com.jcodee.mod2class4.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FormularioFragment extends Fragment {
    @BindView(R.id.etRazonSocial)
    EditText etRazonSocial;
    @BindView(R.id.etRuc)
    EditText etRuc;
    Unbinder unbinder;

    public FormularioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_formulario, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnEnviar)
    public void onViewClicked() {

        String razonSocial = etRazonSocial.getText().toString();
        String ruc = etRuc.getText().toString();

        //Llamar al activity para usar uno de sus métodos
        ((HomeActivity) getActivity()).pasarDatos(razonSocial, ruc);

    }
}
