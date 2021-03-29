package com.example.mvpandretrofitwithbaseactivityclass.notification;
import com.example.mvpandretrofitwithbaseactivityclass.notification.contract.NotificationContract;
import com.example.mvpandretrofitwithbaseactivityclass.notification.model.CallModel;
import com.example.mvpandretrofitwithbaseactivityclass.notification.model.NotificationListData;
import com.example.mvpandretrofitwithbaseactivityclass.notification.model.NotificationRequest;

import java.util.List;

public class NotificationPresenter implements NotificationContract.Presenter, NotificationContract.Model.OnResponseListener {
    private final NotificationContract.View mNotificationListView;
    private final NotificationContract.Model mNotificationListModel;

    public NotificationPresenter(NotificationContract.View mNotificationListView) {
        this.mNotificationListView = mNotificationListView;
        mNotificationListModel = new CallModel();
    }

    @Override
    public void onResponseComplete(List<NotificationListData> list) {
        mNotificationListView.setDataToRecyclerview(list);
        if(mNotificationListView != null) {
            mNotificationListView.hideProgress();
        }

    }

    @Override
    public void onFailure(Throwable throwable) {
        mNotificationListView.onResponseFailure(throwable);
        if(mNotificationListView != null) {
            mNotificationListView.hideProgress();
        }
    }

    @Override
    public void requestDataFromServer(NotificationRequest notificationRequest) {
        if(mNotificationListView != null) {
            mNotificationListView.showProgress();
        }
        mNotificationListModel.getNotificationList(this, notificationRequest);
    }
}
