package com.jcodee.temapokemon.rest;

import com.jcodee.temapokemon.rest.response.GeneracionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by johannfjs on 17/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public interface ServicioWS {
    @GET("v2/generation/{numero_generacion}/")
    Call<GeneracionResponse> obtenerGeneracion(
            @Path("numero_generacion") int numeroGeneracion);
}
