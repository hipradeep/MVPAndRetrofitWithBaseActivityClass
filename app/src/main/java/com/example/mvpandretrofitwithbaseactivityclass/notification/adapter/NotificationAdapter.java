package com.example.mvpandretrofitwithbaseactivityclass.notification.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mvpandretrofitwithbaseactivityclass.R;
import com.example.mvpandretrofitwithbaseactivityclass.notification.model.NotificationListData;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    private List<NotificationListData> list;
    private Context context;

    public NotificationAdapter(List<NotificationListData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_notifications_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.notification_image)
        ImageView mNotificationImage;
        @BindView(R.id.notification_title)
        TextView mNotificationTitle;
        @BindView(R.id.notification_descriptions)
        TextView mNotificationDescriptions;
        @BindView(R.id.notification_created_days)
        TextView mNotificationCreatedDays;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(NotificationListData notification) {
            mNotificationTitle.setText(notification.getTitle());
            mNotificationDescriptions.setText(notification.getDescription());
            @SuppressLint("SimpleDateFormat") DateFormat format  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String currentDateAndTime= format .format(date);
            Date d1 = null;
            Date d2 = null;
            try {
                d1 = format.parse(notification.getCreated());
                //d1 = format.parse("2021-02-02 18:15:56");
                d2 = format.parse(currentDateAndTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // Get msec from each, and subtract.
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000;
            long diffMinutes = diff / (60 * 1000);
            long diffHours = diff / (60 * 60 * 1000);
            long diffDays = diff / (24 * 60 * 60 * 1000);

            if (diffDays>=1){
                mNotificationCreatedDays.setText(diffDays +"d");
            }else {
                if (diffHours<24 && diffHours>=1){
                    mNotificationCreatedDays.setText(diffHours +"h");
                }else {
                    if (diffMinutes<60){
                        mNotificationCreatedDays.setText(diffMinutes +"m");
                    }
                }
            }


        /*    Log.d("MUYYu_diff", String.valueOf(diff));
            Log.d("MUYYu_diff_s", String.valueOf(diffSeconds));
            Log.d("MUYYu_diff_m", String.valueOf(diffMinutes));
            Log.d("MUYYu_diff_h", String.valueOf(diffHours));
            Log.d("MUYYu_diff_d", String.valueOf(diffDays));
            Log.d("MUYYu_current", format .format(date).toString());
            Log.d("MUYYu_RT_crea", notification.getCreated());*/

        }

    }
}
