package com.jcodee.mod2class4.fragmentos;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.jcodee.mod2class4.R;
import com.jcodee.mod2class4.Reusables;
import com.jcodee.mod2class4.adapters.SpinnerAdapter;
import com.jcodee.mod2class4.rest.EmpresaWS;
import com.jcodee.mod2class4.rest.HelperWS;
import com.jcodee.mod2class4.rest.response.EmpresaResponse;
import com.jcodee.mod2class4.rest.response.SucursalResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmpresaFragment extends Fragment {


    @BindView(R.id.spEmpresas)
    Spinner spEmpresas;
    @BindView(R.id.spPuntosVenta)
    Spinner spPuntosVenta;
    Unbinder unbinder;

    private ArrayList<EmpresaResponse> empresas;

    public EmpresaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_empresa, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//Creamos la configuración
        final EmpresaWS empresaWS = HelperWS.obtenerNuevaConfiguracion().create(EmpresaWS.class);

        if (Reusables.isOnline(getActivity())) {
            //Armamos la petición al web service
            Call<ArrayList<EmpresaResponse>> empresaWSAll = empresaWS.getAll();
            //Enviamos la petición al web service
            empresaWSAll.enqueue(new Callback<ArrayList<EmpresaResponse>>() {
                @Override
                public void onResponse(Call<ArrayList<EmpresaResponse>> call, Response<ArrayList<EmpresaResponse>> response) {
                    //Obtenemos el resultado
                    empresas = response.body();
                    //Cerificamos que haya traido información
                    if (empresas != null) {
                        //Creamos un listado
                        ArrayList<String> listaEmpresas = new ArrayList<String>();
                        //Recorremos las empresas que nos ha devuelto
                        for (EmpresaResponse item : empresas) {
                            listaEmpresas.add(item.getRazonSocial());
                        }
                        //Creamos el nuevo adapter para el spinner
                        ArrayAdapter arrayAdapter = new ArrayAdapter(
                                getActivity(),
                                android.R.layout.simple_spinner_dropdown_item,
                                listaEmpresas
                        );
                        //Seteamos la configuración que hemos creado al spinner
                        spEmpresas.setAdapter(arrayAdapter);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<EmpresaResponse>> call, Throwable t) {

                }
            });
        } else {
            Toast.makeText(getActivity(), "No hay internet", Toast.LENGTH_SHORT).show();
        }

        spEmpresas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                EmpresaResponse empresaResponse = empresas.get(position);

                Call<ArrayList<SucursalResponse>> sucursalPorEmpresaWS =
                        empresaWS.getSucursalPorEmpresa(empresaResponse.getCodigo());
                sucursalPorEmpresaWS.enqueue(new Callback<ArrayList<SucursalResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SucursalResponse>> call, Response<ArrayList<SucursalResponse>> response) {
                        ArrayList<SucursalResponse> listaSucrusales = response.body();
                        if (listaSucrusales != null) {
                            ArrayList<String> sucursales = new ArrayList<String>();
                            for (SucursalResponse item : listaSucrusales) {
                                sucursales.add(item.getNombreComercial());
                            }
                            /*ArrayAdapter arrayAdapter = new ArrayAdapter(
                                    getActivity(),
                                    android.R.layout.simple_spinner_dropdown_item,
                                    sucursales
                            );
                            spPuntosVenta.setAdapter(arrayAdapter);
                            */
                            SpinnerAdapter sa = new SpinnerAdapter(getActivity(), sucursales);
                            spPuntosVenta.setAdapter(sa);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SucursalResponse>> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
