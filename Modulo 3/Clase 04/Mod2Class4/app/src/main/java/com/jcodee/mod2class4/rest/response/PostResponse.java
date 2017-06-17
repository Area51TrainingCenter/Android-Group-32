package com.jcodee.mod2class4.rest.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johannfjs on 3/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class PostResponse {
    @SerializedName("title")
    private String title;
    private String body;
    private int userId;
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
