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
        Toast.makeText(getApplicationContext(), "Activity успешно функционирует", Toast.LENGTH_LONG).show();
        Button myButton = findViewById(R.id.button_program); // Получения ссылки на кнопку
        myButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Переход к новому Activity при помощи программного метода", Toast.LENGTH_SHORT).show();
                onNextActivity();
            }
        });
    }
    public void onNextActivityDeclatarate(View view)
    {
        Toast.makeText(this, "Переход к новому Activity при помощи делкративного метода", Toast.LENGTH_SHORT).show();
        onNextActivity();
    }

    private void onNextActivity()
    {
        EditText nameText = findViewById(R.id.editTextName);
        EditText groupText = findViewById(R.id.editTextNumberGroup);
        EditText ageText = findViewById(R.id.editTextAge);
        EditText markText = findViewById(R.id.editTextMark);

        String name, group, age, mark;
        name = nameText.getText().toString();
        group = groupText.getText().toString();
        age = ageText.getText().toString();
        mark = markText.getText().toString();

        if (!name.isEmpty() && !group.isEmpty() && !age.isEmpty() && !mark.isEmpty())
        {
            // Создаем намерение и добавляем данные, которые хотим перенести
            //в другое Activity
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("group", group);
            intent.putExtra("age", age);
            intent.putExtra("mark", mark);

            startActivity(intent);
        }
        else Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
    }
}