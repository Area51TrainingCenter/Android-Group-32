package com.johannfjs.mod3class3.rest.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johannfjs on 3/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class AddressComponentResponse {
    @SerializedName("long_name")
    private String longName;
    @SerializedName("short_name")
    private String shortName;
    private String[] types;

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }
}
