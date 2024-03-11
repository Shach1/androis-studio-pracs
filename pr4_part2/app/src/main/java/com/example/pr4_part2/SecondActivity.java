package com.example.pr4_part2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout_secondActivity,
                FirstFragment.class, null).commit();
    }

    public void onFirstFragment(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_secondActivity,
                FirstFragment.class, null).commit();
    }

    public void onSecondFragment(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_secondActivity,
                SecondFragment.class, null).commit();
    }

    public void onThirdFragment(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_secondActivity,
                ThirdFragment.class, null).commit();
    }

    public void onFirstFragment2(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,
                FirstFragment.class, null).commit();
    }

    public void onSecondFragment2(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,
                SecondFragment.class, null).commit();
    }

    public void onThirdFragment2(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,
                ThirdFragment.class, null).commit();
    }
}