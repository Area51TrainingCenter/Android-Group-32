package com.johannfjs.mod3class3.rest;

import com.johannfjs.mod3class3.rest.response.GeocodingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by johannfjs on 3/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public interface GoogleWS {
//https://maps.googleapis.com/maps/api/geocode/json
// ?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA
// &key=AIzaSyAm9g0KpeWfvCje8QVUwus6tyna7L8Xn84

    @GET("geocode/json")
    Call<GeocodingResponse> obtenerGeolocalizacion(@Query("address") String address,
                                                   @Query("key") String key);

    //http://maps.googleapis.com/maps/api/geocode/json?latlng=-12.1021498,-77.0276599
    @GET("geocode/json")
    Call<GeocodingResponse> obtenerLugares(@Query("latlng") String latlng);
}
