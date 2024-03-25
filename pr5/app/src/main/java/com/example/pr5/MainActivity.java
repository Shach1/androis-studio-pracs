package com.example.pr5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pr5.task1.MainActivityFor1;
import com.example.pr5.task2.MainActivityFor2;
import com.example.pr5.task3.MainActivityFor3;
import com.example.pr5.task4.MainActivityFor4;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onFirstTaskActivity(View view)
    {
        Intent intent = new Intent(this, MainActivityFor1.class);
        startActivity(intent);
    }
    public void onSecondTaskActivity(View view)
    {
        Intent intent = new Intent(this, MainActivityFor2.class);
        startActivity(intent);
    }
    public void onThirdTaskActivity(View view)
    {
        Intent intent = new Intent(this, MainActivityFor3.class);
        startActivity(intent);
    }
    public void onFourthTaskActivity(View view)
    {
        Intent intent = new Intent(this, MainActivityFor4.class);
        startActivity(intent);
    }

}