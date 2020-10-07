package com.zedd.musicnetwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView artistName = null;
    TextView albumName = null;
    TextView songTitle = null;
    private NotificationReceiver nReceiver;
    TextView txtNoNotificationAccess;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        artistName = (TextView) findViewById(R.id.artistName);
        albumName = (TextView) findViewById(R.id.albumName);
        songTitle = (TextView) findViewById(R.id.songTitle);

        txtNoNotificationAccess = (TextView) findViewById(R.id.txtNoNotificationAccess);
        button = (Button) findViewById(R.id.button);
        updateButtonAndText();

        nReceiver = new NotificationReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(NotificationListener.ACTION);
        registerReceiver(nReceiver, filter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateButtonAndText();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(nReceiver);
    }

    private void updateButtonAndText() {
        if (PermissionUtils.hasNotificationAccess(this)) {
            txtNoNotificationAccess.setText("Notification access granted 🎉");
            txtNoNotificationAccess.setTextColor(Color.GREEN);
            button.setText("GET CURRENT PLAYING SONG DETAILS");
        } else {
            txtNoNotificationAccess.setText("Notification access permission not provided!");
            txtNoNotificationAccess.setTextColor(Color.RED);
            button.setText("GET NOTIFICATION ACCESS");
        }
    }

    public void getSongDataFromPlaying(View v) {
        AudioManager manager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        if (manager.isMusicActive()) {
            if (PermissionUtils.hasNotificationAccess(this)) {
                Toast.makeText(this, "Something is playing! 🎶", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
            }
        } else {
            Toast.makeText(this, "Nothing is playing 🙁", Toast.LENGTH_SHORT).show();
        }
    }

    class NotificationReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String artist = "👩‍🎤 " + intent.getStringExtra("artist");
            String album = "💽 " + intent.getStringExtra("album");
            String track = "🎼 " + intent.getStringExtra("title");

            artistName.setText(artist);
            albumName.setText(album);
            songTitle.setText(track);
        }
    }
//        AppCompatActivity {
//    TextView artistName = null;
//    TextView albumName = null;
//    TextView songTitle = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        if(NotificationManagerCompat.getEnabledListenerPackages(getApplicationContext())
//                .contains(getApplicationContext().getPackageName())) {
//            Toast.makeText(this, "Yay! We have notification access 🥳", Toast.LENGTH_SHORT).show();
//       } else {
//            Toast.makeText(this, "No notification access 😢", Toast.LENGTH_SHORT).show();
//            // startActivity(new Intent(
//            //        "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
//        }
//
//        Log.d("ZED main", "Main activity started");
//    }
//
//    public void getSongDataFromPlaying(View view) {
//        AudioManager manager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
//        if (manager.isMusicActive()) {
//            Toast.makeText(this, "Something is playing! 🎶", Toast.LENGTH_SHORT).show();
////            BroadcastReceiver br = new MusicMetaReceiver();
//
////            IntentFilter iF = new IntentFilter();
////            iF.addAction("com.android.music.musicservicecommand");
////            iF.addAction("com.android.music.metachanged");
////            iF.addAction("com.android.music.playstatechanged");
////            iF.addAction("com.android.music.updateprogress");
//
////        this.artistName = findViewById(R.id.artistName);
////        this.albumName = findViewById(R.id.albumName);
////        this.songTitle = findViewById(R.id.songTitle);
//
////            this.registerReceiver(br, iF);
//        } else {
//            Toast.makeText(this, "Nothing is playing 🙁", Toast.LENGTH_SHORT).show();
//        }
//    }
}