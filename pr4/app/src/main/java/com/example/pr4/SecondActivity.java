package com.example.pr4;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        Bundle arguments = getIntent().getExtras();
        if (arguments != null)
        {
            User user = arguments.getParcelable(User.class.getSimpleName());

            TextView nameView = findViewById(R.id.textViewName);
            nameView.setText(getString( R.string.name) + user.getName());
            TextView surnameView = findViewById(R.id.textViewSurname);
            surnameView.setText(getString( R.string.surname) + user.getSurname());
        }
    }

    static final String ACCESS_MSG = "ACCESS";

    ActivityResultLauncher<Intent> startForResult = registerForActivityResult
            (
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>()
                {
                    @Override
                    public void onActivityResult(ActivityResult result)
                    {
                        TextView subjectView = findViewById(R.id.textViewSubject);
                        if (result.getResultCode() == Activity.RESULT_OK)
                        {
                            Intent intent = result.getData();
                            String subject = intent.getStringExtra(ACCESS_MSG);
                            subjectView.setText(subject);
                            Toast.makeText(getApplicationContext(), R.string.alert_ok, Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(getApplicationContext(), R.string.alert_canceled, Toast.LENGTH_SHORT).show();
                    }
                }
            );

    public void onNextActivity(View view)
    {
        Intent intent = new Intent(this, ThirdActivity.class);
        startForResult.launch(intent);
    }
}