package com.example.pr5.task3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pr5.R;

public class MainActivityFor3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for3);
    }

    public void onAddText(View view)
    {
        EditText editText = (EditText) findViewById(R.id.editTextText2);
        TextView textView = (TextView) findViewById(R.id.textView);

        String textToAdd = editText.getText().toString();
        String text = textView.getText().toString();

        if (!textToAdd.isEmpty())
        {
            text += "\n    " + textToAdd;
            editText.setText("");
            textView.setText(text);
        }
    }
}