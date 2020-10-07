package com.zedd.musicnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class NotificationListener extends NotificationListenerService {
    public static final String ACTION = "com.zedd.musicnetwork.MUSIC_BROADCAST";
    public static final String ARG_MESSAGE = "MUSIC_BROADCAST";
    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        Bundle extras = sbn.getNotification().extras;

        final String msg = "ID: " + sbn.getId() + "\t" +
                sbn.getNotification().tickerText + "\t" +
                sbn.getPackageName() + "\tArtist: " + extras.get("android.text");
        Log.i(TAG, msg);

        Intent i = new Intent(ACTION);
        i.putExtra(ARG_MESSAGE, msg);
        i.putExtra("artist", extras.get("android.text").toString());
        i.putExtra("title", sbn.getNotification().tickerText);
        sendBroadcast(i);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
    }

    //BroadcastReceiver {
//    public String artistName = "";
//    public String albumName = "";
//    public String songTitle = "";
//
//    public static final String TAG = "ZED broadCastReceive";
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        String action = intent.getAction();
//        String cmd = intent.getStringExtra("command");
//
//        Log.d("ZED onReceive", action + " / " + cmd);
//
//        String artist = intent.getStringExtra("artist");
//        String album = intent.getStringExtra("album");
//        String track = intent.getStringExtra("track");
//        boolean isPlaying = intent.getBooleanExtra("playing", false);
//
//        Log.d("ZED onReceive ", "🎼 " + track);
//        Log.d("ZED onReceive ", "👩‍🎤 " + artist);
//        Log.d("ZED onReceive ", "💽 " + album);
//
//        artistName = String.format("👩‍🎤 %s", artist);
//        albumName = String.format("💽 %s", album);
//        songTitle = String.format("🎼 %s", track);
//    }
}
