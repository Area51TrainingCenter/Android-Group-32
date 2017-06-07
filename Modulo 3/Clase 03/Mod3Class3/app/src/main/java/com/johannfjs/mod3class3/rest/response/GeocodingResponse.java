package com.johannfjs.mod3class3.rest.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by johannfjs on 3/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class GeocodingResponse {
    private String status;
    @SerializedName("error_message")
    private String errorStatus;
    private ArrayList<ResultResponse> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(String errorStatus) {
        this.errorStatus = errorStatus;
    }

    public ArrayList<ResultResponse> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultResponse> results) {
        this.results = results;
    }
}
