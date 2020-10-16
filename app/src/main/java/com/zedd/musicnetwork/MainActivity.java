package com.zedd.musicnetwork;

import static java.util.Objects.requireNonNull;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ZEDD_M_N";

    @Nullable private TextView artistName;
    @Nullable private TextView albumName;
    @Nullable private TextView songTitle;

    private final MusicMetaReceiver metaReceiver = new MusicMetaReceiver();
    private final IntentFilter spotifyFilter = new IntentFilter();
    {
        spotifyFilter.addAction("com.spotify.music.metadatachanged");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        artistName = findViewById(R.id.artistName);
        albumName = findViewById(R.id.albumName);
        songTitle = findViewById(R.id.songTitle);
    }

    @Override protected void onResume() {
        super.onResume();
        registerReceiver(metaReceiver, spotifyFilter);
    }

    @Override protected void onStop() {
        super.onStop();
        unregisterReceiver(metaReceiver);
    }

    private class MusicMetaReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "Received intent from Spotify: " + intent);

            requireNonNull(artistName).setText("👩‍🎤 " + intent.getStringExtra("artist"));
            requireNonNull(albumName).setText("💽 " + intent.getStringExtra("album"));
            requireNonNull(songTitle).setText("🎼 " + intent.getStringExtra("track"));
        }
    }
}
