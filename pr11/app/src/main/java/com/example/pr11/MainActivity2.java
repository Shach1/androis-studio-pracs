package com.example.pr11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("http://95.174.91.133/song.mp3");
            mediaPlayer.prepare(); // Можно использовать prepareAsync() для сетевых потоков
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onPlayPause(View view)
    {
        if (mediaPlayer.isPlaying())
        {
            mediaPlayer.pause();
        }
        else
        {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void onNextActivity(View view)
    {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
}