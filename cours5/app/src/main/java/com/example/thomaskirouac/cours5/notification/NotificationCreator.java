package com.example.thomaskirouac.cours5.notification;

import android.app.Notification;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.example.thomaskirouac.cours5.R;
import com.example.thomaskirouac.cours5.notification.model.MessageModel;

public class NotificationCreator {

public static Notification createNotificationForMessage(Context context, MessageModel messageModel){
    NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"42")
            .setSmallIcon(R.drawable.choice_toxicity_icon)
            .setContentTitle(messageModel.getSender())
            .setContentText(messageModel.getMessage())
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    return builder.build();
}

}
