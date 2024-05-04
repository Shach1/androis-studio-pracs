package com.example.pr7;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.util.ArrayList;

public class MusicPlayer extends Service {

    private MediaPlayer mediaPlayer;



    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer = MediaPlayer.create(this, MainActivity.songs.get(MainActivity.currentSong));
        mediaPlayer.setVolume(100, 100);
        mediaPlayer.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        if (!mediaPlayer.isPlaying()) mediaPlayer.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (!mediaPlayer.isPlaying()) mediaPlayer.stop();

        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}