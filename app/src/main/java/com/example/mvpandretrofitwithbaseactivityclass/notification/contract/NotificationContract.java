package com.example.mvpandretrofitwithbaseactivityclass.notification.contract;

import com.example.mvpandretrofitwithbaseactivityclass.notification.model.NotificationListData;
import com.example.mvpandretrofitwithbaseactivityclass.notification.model.NotificationRequest;

import java.util.List;

public interface NotificationContract {

    interface Model{

        interface OnResponseListener {
            void onResponseComplete(List<NotificationListData> listData);
            void onFailure(Throwable throwable);
        }
        void getNotificationList(OnResponseListener onResponseListener, NotificationRequest notificationRequest);

    }

    interface View {
        void setDataToRecyclerview(List<NotificationListData> listData);
        void onResponseFailure(Throwable throwable);
        void showProgress();
        void hideProgress();
    }

    interface Presenter {
        void requestDataFromServer(NotificationRequest notificationRequest);
    }

}
