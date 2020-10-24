package com.zedd.musicnetwork;

import static com.zedd.musicnetwork.ApplicationConstants.APPLICATION_TAG;
import static com.zedd.musicnetwork.ApplicationConstants.NOTIFICATION_LISTENER_DATA;
import static com.zedd.musicnetwork.ApplicationConstants.NOTIFICATION_LISTENER_DATA_TEXT_EXTRA;
import static java.util.Objects.requireNonNull;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Nullable private TextView artistName;
    @Nullable private TextView albumName;
    @Nullable private TextView songTitle;
    @Nullable private TextView notificationStatus;
    @Nullable private Button grantNotificationAccessBtn;

    private final MusicMetaReceiver metaReceiver = new MusicMetaReceiver();
    private final IntentFilter spotifyFilter = new IntentFilter();

    {
        spotifyFilter.addAction("com.spotify.music.metadatachanged");
    }

    private final NotificationDataReceiver notificationReceiver = new NotificationDataReceiver();
    private final IntentFilter notificationFilter = new IntentFilter();

    {
        notificationFilter.addAction(NOTIFICATION_LISTENER_DATA);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        artistName = findViewById(R.id.artistName);
        albumName = findViewById(R.id.albumName);
        songTitle = findViewById(R.id.songTitle);
        notificationStatus = findViewById(R.id.notificationStatus);
        grantNotificationAccessBtn = findViewById(R.id.grantNotificationAccessBtn);
        grantNotificationAccessBtn.setOnClickListener(v -> startActivity(new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)));
    }

    @Override protected void onResume() {
        super.onResume();
        registerReceiver(metaReceiver, spotifyFilter);
        registerReceiver(notificationReceiver, notificationFilter);
        requireNonNull(grantNotificationAccessBtn).setVisibility(NotificationListener.BINDED ? View.GONE : View.VISIBLE);
        requireNonNull(notificationStatus).setText(NotificationListener.BINDED ? "✅ Access granted" : "❌ Access not granted");
    }

    @Override protected void onStop() {
        super.onStop();
        unregisterReceiver(metaReceiver);
        unregisterReceiver(notificationReceiver);
    }

    private class MusicMetaReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(APPLICATION_TAG, "Received intent from Spotify: " + intent);

            requireNonNull(artistName).setText("👩‍🎤 " + intent.getStringExtra("artist"));
            requireNonNull(albumName).setText("💽 " + intent.getStringExtra("album"));
            requireNonNull(songTitle).setText("🎼 " + intent.getStringExtra("track"));
        }
    }

    private class NotificationDataReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(APPLICATION_TAG, "Received notification service intent: " + intent);
            if (intent.getAction().equals(NOTIFICATION_LISTENER_DATA)) {
                Log.d(APPLICATION_TAG, "Notification text: " + intent.getExtras()
                        .getString(NOTIFICATION_LISTENER_DATA_TEXT_EXTRA));
            }
        }
    }
}
