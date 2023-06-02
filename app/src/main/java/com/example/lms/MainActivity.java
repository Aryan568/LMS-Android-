package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
VideoView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_act_splash);
        v= findViewById(R.id.videoView2);

        String vPath= new StringBuilder("android.resource://")
                .append(getPackageName())
                .append("/raw/lmsplash").toString();
        v.setVideoPath(vPath);
        v.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(MainActivity.this, MainActivity2.class));
                        finish();
                    }
                },0);
            }
        });
        v.start();
    }
}