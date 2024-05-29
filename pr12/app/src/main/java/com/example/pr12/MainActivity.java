package com.example.pr12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pr12.entity.User;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    public static final Uri CONTENT_URI = Uri.parse("content://com.example.pr12.UserProvider");
    private User user;
    private Gson gson;
    private String userJson;
    private EditText etName;
    private EditText etSurname;
    private EditText email;

    private TextView tvName;
    private TextView tvSurname;
    private TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        email = findViewById(R.id.etEmail);

        tvName = findViewById(R.id.tvName);
        tvSurname = findViewById(R.id.tvSurname);
        tvEmail = findViewById(R.id.tvEmail);

        gson = new Gson();
    }



    public void onSave(View view)
    {
        parseUserFromUI();
        if (user == null)
        {
            return;
        }

        try {
            userJson = parseUserToJson(user);
            Log.i("User","data saved:\n" + userJson);
            // save json to file

            // добавляем данные в SharedPreferences для передачи с помощью ContentProvider
            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("userJson", userJson);
            editor.apply();

            File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            if (!storageDir.exists())
            {
                // Создаем директорию, если она не существует
                storageDir.mkdirs();
            }
            File file = new File(storageDir, "jsonFile.json");

            // Записываем JSON в файл
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(userJson.getBytes());
            fos.close();
        }
        catch (Exception e) {
            Log.e("User", "Error saving user data", e);
        }
    }

    private String parseUserToJson(User user)
    {
        return gson.toJson(user);
    }

    private void parseUserFromUI()
    {
        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String email = this.email.getText().toString();

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty())
        {

            user = null;
            return;
        }

        user = new User(name, surname, email);
    }

    public void onLoad(View view) {
        try {
            File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(storageDir, "jsonFile.json");

            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);

            User user = new Gson().fromJson(isr, User.class);
            isr.close();


            if (user != null) {
                Toast.makeText(this, "Данные загружены", Toast.LENGTH_SHORT).show();
                tvName.setText(user.getName());
                tvSurname.setText(user.getSurname());
                tvEmail.setText(user.getEmail());
            }
        } catch (Exception e) {
            Log.e("User", "Error loading user data", e);
        }
    }

    public void onTransmit(View view) {
        // Сохраняем данные пользователя
        onSave(view);

        // Уведомляем заинтересованные стороны о том, что данные изменились
        getContentResolver().notifyChange(CONTENT_URI, null);

        Toast.makeText(this, "Данные переданы", Toast.LENGTH_SHORT).show();
    }
}