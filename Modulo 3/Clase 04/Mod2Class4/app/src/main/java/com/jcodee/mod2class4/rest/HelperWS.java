package com.jcodee.mod2class4.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by johannfjs on 3/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class HelperWS {
    public static Retrofit obtenerConfiguracion() {
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //http://170.81.242.4:8080/api/empresa/getall

    public static Retrofit obtenerNuevaConfiguracion() {
        return new Retrofit.Builder()
                .baseUrl("http://170.81.242.4:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
