package com.example.mvpandretrofitwithbaseactivityclass.notification.model;


import com.example.mvpandretrofitwithbaseactivityclass.notification.contract.NotificationContract;
import com.example.mvpandretrofitwithbaseactivityclass.retrofit.ApiWebService;
import com.example.mvpandretrofitwithbaseactivityclass.retrofit.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallModel implements  NotificationContract.Model {
    public ApiWebService apiWebService;
    @Override
    public void getNotificationList(OnResponseListener onResponseListener, NotificationRequest notificationRequest) {

        apiWebService =  WebService.createService(ApiWebService.class);
        Call<NotificationResponse> call = apiWebService.getNotificationList(notificationRequest);
        call.enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                assert response.body() != null;
                if (response.body().getStatus()){
                    List<NotificationListData> list=response.body().getData();
                    onResponseListener.onResponseComplete(list);
                }
            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                onResponseListener.onFailure(t);
            }
        });
    }
}
