package com.example.pr7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MusicPlayer musicPlayer;
    public static ArrayList<Integer> songs = new ArrayList<>();
    public static int currentSong = 0;
    private Intent IMusicPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songs.add(R.raw.budcurt_arizona_boshki_fast_remix);
        songs.add(R.raw.miyagi_marlboro);
        songs.add(R.raw.chase_bennett_on_me);

        musicPlayer = new MusicPlayer();
        IMusicPlayer = new Intent(this, musicPlayer.getClass());
        startService(IMusicPlayer);

        Toast.makeText(this, "Music Player Started", Toast.LENGTH_SHORT).show();


    }

    public void onNextSong(View view)
    {
        currentSong++;
        if (currentSong >= songs.size()) currentSong = 0;

        stopService(IMusicPlayer);
        IMusicPlayer = new Intent(this, MusicPlayer.class);
        startService(IMusicPlayer);
    }
}