package com.example.broadcastexample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

/**
 * </br>
 * <p>
 * &nbsp
 *
 * @author: NIO
 * @since: 1.0.0, 6/23/20 5:15 PM
 * @version: 1.0.0
 */
public class SimpleReceiver extends BroadcastReceiver {

    private static final String TAG = SimpleReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "====== onReceive() ======");
        showNotification(context);
    }

    private void showNotification(Context context) {
        NotificationManager mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent resultIntent = new Intent(context, SecondActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                context,
                0,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        Notification notification;
        // 8.0之后
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel("chanel_id", "name",
                    NotificationManager.IMPORTANCE_LOW);
            mNotificationManager.createNotificationChannel(mChannel);
            notification = new Notification.Builder(context)
                    .setChannelId("channel_id")
                    .setContentTitle("活动")
                    .setContentIntent(resultPendingIntent)
                    .setContentText("您有一项活动，快去参加吧")
                    .setSmallIcon(R.mipmap.ic_launcher).build();
        } else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("标题来了")
                    .setContentText("内容来了来了")
                    .setDefaults(Notification.DEFAULT_ALL);
            builder.setContentIntent(resultPendingIntent);
            notification = builder.build();
        }
        mNotificationManager.notify(1, notification);
    }
}
