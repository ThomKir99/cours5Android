package com.example.thomaskirouac.cours5.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.google.firebase.firestore.FirebaseFirestore;

public class NotificationService extends Service {
    public static final String CHANNEL_ID = "NotificationService";

    NotificationManager notificationManager;
    FirebaseFirestore database;
    int idNotification = 2;

    @Override
    public void onCreate() {
        createNotificationChannel();
        database = FirebaseFirestore.getInstance();
        super.onCreate();
    }

    public void createNotificationChannel() {
        createNotificationChannelService();
        createNotificationChannelMessage();
    }

    private void createNotificationChannelService(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelId= CHANNEL_ID;
            CharSequence channelName = "NotificationService";
            String channelDescription = "Notification service";
            int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel= new NotificationChannel(channelId, channelName,channelImportance);
            channel.setDescription(channelDescription);
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void createNotificationChannelMessage(){

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
