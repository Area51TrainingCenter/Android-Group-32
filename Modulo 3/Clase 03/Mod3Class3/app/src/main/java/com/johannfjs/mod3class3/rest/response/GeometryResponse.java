package com.johannfjs.mod3class3.rest.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johannfjs on 3/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class GeometryResponse {
    private BoundsResponse bounds;
    private LocationResponse location;
    @SerializedName("location_type")
    private String locationType;
    private BoundsResponse viewport;

    public BoundsResponse getBounds() {
        return bounds;
    }

    public void setBounds(BoundsResponse bounds) {
        this.bounds = bounds;
    }

    public LocationResponse getLocation() {
        return location;
    }

    public void setLocation(LocationResponse location) {
        this.location = location;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public BoundsResponse getViewport() {
        return viewport;
    }

    public void setViewport(BoundsResponse viewport) {
        this.viewport = viewport;
    }
}
