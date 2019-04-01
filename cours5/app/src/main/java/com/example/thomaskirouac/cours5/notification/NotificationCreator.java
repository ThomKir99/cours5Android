package com.example.thomaskirouac.cours5.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.thomaskirouac.cours5.MainActivity;
import com.example.thomaskirouac.cours5.R;
import com.example.thomaskirouac.cours5.notification.model.ImportantMessageModel;
import com.example.thomaskirouac.cours5.notification.model.MessageModel;

import static android.app.Notification.EXTRA_NOTIFICATION_ID;

public class NotificationCreator {

public static Notification createNotificationForMessage(Context context, MessageModel messageModel){
    NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"42")
            .setSmallIcon(R.drawable.choice_toxicity_icon)
            .setContentTitle(messageModel.getSender())
            .setContentText(messageModel.getMessage())
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    return builder.build();
}

    public static Notification createImportantNotificationForMessage(Context context, ImportantMessageModel messageModel){
        Intent snoozeIntent = new Intent(context, MainActivity.class);

        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(context, 0, snoozeIntent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"43")
                .setSmallIcon(R.drawable.choice_toxicity_icon)
                .setContentTitle(messageModel.getSender())
                .setContentText(messageModel.getMessage())
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .addAction(R.drawable.choice_toxicity_icon, "Marqué comme lue",
                snoozePendingIntent);
        return builder.build();
    }



}
