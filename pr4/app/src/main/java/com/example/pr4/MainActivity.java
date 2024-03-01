package com.example.pr4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onNextActivity(View view)
    {
        EditText nameEdit = findViewById(R.id.editTextName);
        EditText surnameEdit = findViewById(R.id.editTextSurname);
        String name = nameEdit.getText().toString();
        String surname = surnameEdit.getText().toString();

        if (!name.isEmpty() && !surname.isEmpty())
        {
            User user = new User(name, surname);
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra(User.class.getSimpleName(), user);
            startActivity(intent);
        }
    }
}