package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private static final String LOG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(LOG, "Activity is Created!");
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.i(LOG, "Activity is Started!");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.i(LOG, "Activity is Paused!");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.i(LOG, "Activity is Stopped!");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.i(LOG, "Activity is Restarted!");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.w(LOG, "Activity is Destroyed!");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.i(LOG, "Activity is Resumed!");
        Toast.makeText(getApplicationContext(), R.string.a1_Activity_success, Toast.LENGTH_LONG).show();
        Button myButton = findViewById(R.id.button_program); // Получения ссылки на кнопку
        myButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), R.string.a1_switch_activity_prog, Toast.LENGTH_SHORT).show();
                onNextActivity();
            }
        });
    }
    public void onNextActivityDeclatarate(View view)
    {
        Toast.makeText(this, R.string.a1_switch_activity_dec, Toast.LENGTH_SHORT).show();
        onNextActivity();
    }

    private void onNextActivity()
    {
        EditText nameText = findViewById(R.id.editTextName);
        EditText groupText = findViewById(R.id.editTextNumberGroup);
        EditText ageText = findViewById(R.id.editTextAge);
        EditText markText = findViewById(R.id.editTextMark);

        String name, group;
        int age, mark;

        name = nameText.getText().toString();
        group = groupText.getText().toString();
        if (!ageText.getText().toString().isEmpty() && !markText.getText().toString().isEmpty())
        {
            age =  Integer.parseInt(ageText.getText().toString());
            mark = Integer.parseInt(markText.getText().toString());
        }
        else
        {
            age = 0;
            mark = 0;
        }

        if (!name.isEmpty() && !group.isEmpty() && age > 0 && mark > 0)
        {
            Student student = new Student(name, age, group, mark);
            Intent intent = new Intent(this, SecondActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(Student.class.getSimpleName(), student);
            startActivity(intent);
        }
        else Toast.makeText(this, R.string.a1_fill_all_fields, Toast.LENGTH_SHORT).show();
    }
}