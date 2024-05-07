package com.example.pr7;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity2 extends AppCompatActivity implements MyDialogFragment.OnTextEnteredListener{
    private int hour;
    private int minute;
    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



    }

    @Override
    public void onTextEntered(String text) {
        TextView textView = findViewById(R.id.textToSet);
        textView.setText(text);
    }
    private String reverseString(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }
    public void ButtonReflectText(View view) {
        EditText editText = findViewById(R.id.editTextToReflect);
        String text = editText.getText().toString();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Reflected text");
        builder.setMessage(reverseString(text));
        builder.create().show();

    }

    public void PickDateButton(View view) {
        // Создание обработчика выбора даты
        DatePickerDialog.OnDateSetListener dateSetListener = (view1, year, month, dayOfMonth) -> {
            // Обработка выбора даты
            this.year = year;
            this.month = month;
            this.day = dayOfMonth;
        };
        // Создание и отображение DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                Activity2.this,
                dateSetListener,
                year, // текущий год
                month, // текущий месяц
                day); // текущий день
        datePickerDialog.show();
    }

    public void PickTimeButton(View view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                Activity2.this,
                (view1, hourOfDay, minute) -> {
                    // Обработка выбора времени
                    this.hour = hourOfDay;
                    this.minute = minute;
                }, hour, minute, true);
        timePickerDialog.show();

    }

    public void ChangeTextButton(View view) {
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.setOnTextEnteredListener(this);
        dialogFragment.show(getSupportFragmentManager(), "test");
    }
}