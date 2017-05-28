package com.johannfjs.mod3class2_1.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by johannfjs on 27/05/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class HelperWS {
    public static Retrofit configuracion() {
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
