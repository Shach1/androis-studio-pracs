package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("name").toString();
        String group = arguments.get("group").toString();
        String age = arguments.get("age").toString();
        String mark = arguments.get("mark").toString();

        TextView nameView = findViewById(R.id.textView);
        nameView.setText("Студент: " + name);
        TextView groupView = findViewById(R.id.textView2);
        groupView.setText("Группа: " + group);
        TextView ageView = findViewById(R.id.textView3);
        ageView.setText("Возраст: " + age);
        TextView markView = findViewById(R.id.textView4);
        markView.setText("Желаемая оценка: " + mark);

    }

    public void onPreviousAction(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}