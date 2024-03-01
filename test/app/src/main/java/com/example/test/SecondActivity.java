package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
        if(arguments != null)
        {
            Student student = arguments.getParcelable(Student.class.getSimpleName());
            String name = student.getName();
            String group = student.getGroup();
            int age = student.getAge();
            int grade = student.getDesired_grade();

            TextView nameView = findViewById(R.id.textView);
            nameView.setText(getString(R.string.a2_student) + name);
            TextView groupView = findViewById(R.id.textView2);
            groupView.setText(getString(R.string.a2_group) + group);
            TextView ageView = findViewById(R.id.textView3);
            ageView.setText(getString(R.string.a2_age) + age);
            TextView markView = findViewById(R.id.textView4);
            markView.setText(getString(R.string.a2_mark) + grade);
        }
    }

    public void onPreviousAction(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}