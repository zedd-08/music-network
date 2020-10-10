package com.zedd.musicnetwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ZEDD_M_N";
    TextView artistName = null;
    TextView albumName = null;
    TextView songTitle = null;
    private MusicMetaReceiver nReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        artistName = (TextView) findViewById(R.id.artistName);
        albumName = (TextView) findViewById(R.id.albumName);
        songTitle = (TextView) findViewById(R.id.songTitle);

        nReceiver = new MusicMetaReceiver();
        IntentFilter iF = new IntentFilter();
        // Spotify broadcasts
        iF.addAction("com.spotify.music.metadatachanged");
        iF.addAction("com.spotify.music.playbackstatechanged");
        iF.addAction("com.spotify.music.queuechanged");
        registerReceiver(nReceiver, iF);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(nReceiver);
    }

    class MusicMetaReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i(TAG, "Action: " + action);

            artistName.setText("👩‍🎤 " + intent.getStringExtra("artist"));
            albumName.setText("💽 " + intent.getStringExtra("track"));
            songTitle.setText("🎼 " + intent.getStringExtra("track"));
        }
    }
}