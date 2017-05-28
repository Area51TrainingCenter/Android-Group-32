package com.johannfjs.mod3class2_1.rest;

import com.johannfjs.mod3class2_1.response.PostResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by johannfjs on 27/05/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public interface ServicioWS {

    /*
        GET
        POST
        PUT
        DELETE
        PATCH
    */

    @GET("posts")
    Call<ArrayList<PostResponse>> posts();
}
