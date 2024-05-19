package com.example.pr11;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity3 extends AppCompatActivity {

    ImageView imageView;
    Button btDance;
    Button btNextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btDance = findViewById(R.id.btDance);
        imageView = findViewById(R.id.imageView);
        btNextActivity = findViewById(R.id.btNextActivity);
    }

    public void onDanceNow(View view)
    {

        ObjectAnimator animBtDance = ObjectAnimator.ofFloat(btDance, "translationY", 200f);
        animBtDance.setDuration(2000);
        animBtDance.setRepeatCount(5);
        animBtDance.setRepeatMode(ObjectAnimator.REVERSE);
        animBtDance.start();

        ObjectAnimator animBtNextActivity = ObjectAnimator.ofFloat(btNextActivity, "translationX", -800f);
        animBtNextActivity.setDuration(2000);
        animBtNextActivity.setRepeatCount(ObjectAnimator.INFINITE);
        animBtNextActivity.setRepeatMode(ObjectAnimator.REVERSE);
        animBtNextActivity.start();


        ObjectAnimator animImageView = ObjectAnimator.ofFloat(imageView, "rotation", 360f);
        animImageView.setDuration(2000);
        animImageView.setRepeatCount(5);
        animImageView.setRepeatMode(ObjectAnimator.REVERSE);
        animImageView.start();
    }

    public void onNextActivity(View view)
    {
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
    }

}