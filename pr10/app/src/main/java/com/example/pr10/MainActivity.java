package com.example.pr10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String userName;
    private EditText etUserName;
    private TextView tvUserName;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName = findViewById(R.id.etUserName);
        tvUserName = findViewById(R.id.tvUserName);
        sharedPreferences = getSharedPreferences("myPreferences", MODE_PRIVATE);
    }

    public void onSaveUserName(View view)
    {
        if (editor == null) editor = sharedPreferences.edit();

        userName = etUserName.getText().toString();
        if (userName.isEmpty())
        {
            Toast.makeText(this, "Введите имя пользователя!", Toast.LENGTH_SHORT).show();
            return;
        }
        editor.putString("userName", userName);
        editor.apply();
        etUserName.setText("");
        Toast.makeText(this, "Имя пользователя сохранено", Toast.LENGTH_SHORT).show();
    }

    public void onDisplayUserName(View view)
    {
        userName = sharedPreferences.getString("userName", "Default Name");
        tvUserName.setText(userName);
    }

    public void onDeleteUserName(View view)
    {
        if (editor == null) editor = sharedPreferences.edit();
        editor.remove("userName");
        editor.apply();
        Toast.makeText(this, "Имя пользователя удалено", Toast.LENGTH_SHORT).show();
    }

}