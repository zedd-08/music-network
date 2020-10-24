package com.zedd.musicnetwork;

import static com.zedd.musicnetwork.ApplicationConstants.APPLICATION_TAG;
import static com.zedd.musicnetwork.ApplicationConstants.NOTIFICATION_LISTENER_DATA;
import static com.zedd.musicnetwork.ApplicationConstants.NOTIFICATION_LISTENER_DATA_TEXT_EXTRA;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.Intent;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class NotificationListener extends NotificationListenerService {
    public static boolean BINDED = FALSE;

    @Override public IBinder onBind(Intent intent) {
        Log.d(APPLICATION_TAG, "Notification listener bound");
        BINDED = TRUE;
        return super.onBind(intent);
    }

    @Override public boolean onUnbind(Intent intent) {
        Log.d(APPLICATION_TAG, "Notification listener unbound");
        BINDED = FALSE;
        return super.onUnbind(intent);
    }

    @Override public void onNotificationPosted(StatusBarNotification sbn) {
        Log.d(APPLICATION_TAG, "Notification: " + sbn.getNotification().tickerText);
        Intent intent = new Intent(NOTIFICATION_LISTENER_DATA);
        intent.putExtra(NOTIFICATION_LISTENER_DATA_TEXT_EXTRA, sbn.getNotification().tickerText);
        sendBroadcast(intent);
    }
}
