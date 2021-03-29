package com.example.mvpandretrofitwithbaseactivityclass.notification;


import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpandretrofitwithbaseactivityclass.R;
import com.example.mvpandretrofitwithbaseactivityclass.common.BaseActivity;
import com.example.mvpandretrofitwithbaseactivityclass.notification.adapter.NotificationAdapter;
import com.example.mvpandretrofitwithbaseactivityclass.notification.contract.NotificationContract;
import com.example.mvpandretrofitwithbaseactivityclass.notification.model.NotificationListData;
import com.example.mvpandretrofitwithbaseactivityclass.notification.model.NotificationRequest;
import com.example.mvpandretrofitwithbaseactivityclass.notification.model.NotificationResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends BaseActivity implements NotificationContract.View {

    @BindView(R.id.notification_recycler_view)
    RecyclerView mNotificationRV;
    private List<NotificationListData> notificationListDataList;
    private LinearLayoutManager layoutManager;
    private NotificationPresenter mNotificationPresenter;
    private NotificationAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        configureToolbar(getResources().getString(R.string.notification));

        notificationListDataList = new ArrayList<>();
         layoutManager = new LinearLayoutManager(NotificationActivity.this);
        mNotificationRV.setLayoutManager(layoutManager);
        mNotificationRV.setHasFixedSize(true);

        mNotificationPresenter =new NotificationPresenter(this);

        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setPartnerId("1");
        notificationRequest.setUsername("4446465");
        mNotificationPresenter.requestDataFromServer(notificationRequest);
    }

    @Override
    public void setDataToRecyclerview(List<NotificationListData> List) {
        notificationListDataList.addAll(List);
        adapter=new NotificationAdapter(notificationListDataList);
        mNotificationRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        showError(throwable);
    }

    @Override
    public void showProgress() {
        showLoader();
    }

    @Override
    public void hideProgress() {
        dismissLoader();
    }
}