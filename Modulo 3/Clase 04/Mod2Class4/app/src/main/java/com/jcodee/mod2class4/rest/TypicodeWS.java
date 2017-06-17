package com.jcodee.mod2class4.rest;

import com.jcodee.mod2class4.rest.response.PostResponse;
import com.jcodee.mod2class4.rest.response.UserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by johannfjs on 3/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public interface TypicodeWS {

    @POST("posts")
    @FormUrlEncoded
    Call<PostResponse> insertarPost(@Field("title") String title,
                                    @Field("body") String body,
                                    @Field("userId") int userId);

    @GET("users")
    Call<ArrayList<UserResponse>> obtenerUsuarios();
}
