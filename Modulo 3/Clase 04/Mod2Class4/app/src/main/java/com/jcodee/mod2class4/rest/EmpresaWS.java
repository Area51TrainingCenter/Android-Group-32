package com.jcodee.mod2class4.rest;

import com.jcodee.mod2class4.rest.response.EmpresaResponse;
import com.jcodee.mod2class4.rest.response.SucursalResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by johannfjs on 10/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public interface EmpresaWS {
    @GET("empresa/getall")
    Call<ArrayList<EmpresaResponse>> getAll();

    @GET("sucursal/getsucursalesbycodigoempresa/{codigo_empresa}")
    Call<ArrayList<SucursalResponse>> getSucursalPorEmpresa(
            @Path("codigo_empresa") String codigoEmpresa);
}
