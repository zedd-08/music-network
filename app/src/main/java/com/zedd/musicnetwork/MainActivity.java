package com.zedd.musicnetwork;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {
    TextView artistName = null;
    TextView albumName = null;
    TextView songTitle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(NotificationManagerCompat.getEnabledListenerPackages(getApplicationContext())
                .contains(getApplicationContext().getPackageName())) {
            Toast.makeText(this, "No notification access 😢", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Yay! We have notification access 🥳", Toast.LENGTH_SHORT).show();
        }

        Log.d("ZZZZZ", "Hello");
//
//        IntentFilter iF = new IntentFilter();
//        iF.addAction("com.android.music.musicservicecommand");
//        iF.addAction("com.android.music.metachanged");
//        iF.addAction("com.android.music.playstatechanged");
//        iF.addAction("com.android.music.updateprogress");
//
//        this.artistName = findViewById(R.id.artistName);
//        this.albumName = findViewById(R.id.albumName);
//        this.songTitle = findViewById(R.id.songTitle);
//
//        registerReceiver(mReceiver, iF);
    }

    public void getSongDataFromPlaying(View view) {
        AudioManager manager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        if (manager.isMusicActive()) {
            Toast.makeText(this, "Something is playing! 🎶", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Nothing is playing 🙁", Toast.LENGTH_SHORT).show();
        }
    }
}