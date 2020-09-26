package com.zedd.musicnetwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MusicMetaReceiver extends BroadcastReceiver {
    public String artistName = "";
    public String albumName = "";
    public String songTitle = "";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String cmd = intent.getStringExtra("command");

        Log.d("ZED onReceive", action + " / " + cmd);

        String artist = intent.getStringExtra("artist");
        String album = intent.getStringExtra("album");
        String track = intent.getStringExtra("track");
        boolean isPlaying = intent.getBooleanExtra("playing", false);

        Log.d("ZED onReceive ", "🎼 " + track);
        Log.d("ZED onReceive ", "👩‍🎤 " + artist);
        Log.d("ZED onReceive ", "💽 " + album);

        artistName = String.format("👩‍🎤 %s", artist);
        albumName = String.format("💽 %s", album);
        songTitle = String.format("🎼 %s", track);
    }
}
