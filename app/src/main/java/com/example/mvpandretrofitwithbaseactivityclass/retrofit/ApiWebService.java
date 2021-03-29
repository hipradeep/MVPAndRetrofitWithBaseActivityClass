package com.example.mvpandretrofitwithbaseactivityclass.retrofit;


import com.example.mvpandretrofitwithbaseactivityclass.notification.model.NotificationRequest;
import com.example.mvpandretrofitwithbaseactivityclass.notification.model.NotificationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiWebService {
    @POST("notification")
    Call<NotificationResponse> getNotificationList(@Body NotificationRequest bodyObject);

}
