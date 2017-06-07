package com.johannfjs.mod3class3.rest.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by johannfjs on 3/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class ResultResponse {
    @SerializedName("address_components")
    private ArrayList<AddressComponentResponse> addressComponents;
    private String formatted_address;
    private GeometryResponse geometry;
    @SerializedName("place_id")
    private String placeId;
    private String[] types;

    public ArrayList<AddressComponentResponse> getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(ArrayList<AddressComponentResponse> addressComponents) {
        this.addressComponents = addressComponents;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public GeometryResponse getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryResponse geometry) {
        this.geometry = geometry;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }
}
