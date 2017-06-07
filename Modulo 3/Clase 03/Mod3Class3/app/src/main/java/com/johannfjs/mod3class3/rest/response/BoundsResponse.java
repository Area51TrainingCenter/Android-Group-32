package com.johannfjs.mod3class3.rest.response;

/**
 * Created by johannfjs on 3/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class BoundsResponse {
    private LocationResponse northeast;
    private LocationResponse southwest;

    public LocationResponse getNortheast() {
        return northeast;
    }

    public void setNortheast(LocationResponse northeast) {
        this.northeast = northeast;
    }

    public LocationResponse getSouthwest() {
        return southwest;
    }

    public void setSouthwest(LocationResponse southwest) {
        this.southwest = southwest;
    }
}
