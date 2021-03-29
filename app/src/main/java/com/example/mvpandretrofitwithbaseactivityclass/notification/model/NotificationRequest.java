package com.example.mvpandretrofitwithbaseactivityclass.notification.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationRequest {

    @SerializedName("partner_id")
    @Expose
    private String partnerId;
    @SerializedName("username")
    @Expose
    private String username;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}