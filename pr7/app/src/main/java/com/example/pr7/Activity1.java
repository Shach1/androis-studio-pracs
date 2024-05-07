package com.example.pr7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Activity1 extends AppCompatActivity {
    private MusicPlayer musicPlayer;
    public static ArrayList<Integer> songs = new ArrayList<>();
    public static int currentSong = 0;
    private Intent IMusicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        songs.add(R.raw.waterfall140894);
        songs.add(R.raw.foreverlost);


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

    public void onStopSong(View view)
    {
        stopService(IMusicPlayer);
    }

}