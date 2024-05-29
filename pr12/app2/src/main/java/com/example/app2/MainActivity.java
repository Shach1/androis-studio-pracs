package com.example.app2;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app2.entity.User;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    public static final Uri CONTENT_URI = Uri.parse("content://com.example.pr12.UserProvider");
    private TextView tvName;
    private TextView tvSurname;
    private TextView tvEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, "com.example.pr12.READ_USER_DATA") != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{"com.example.pr12.READ_USER_DATA"}, 0);
        }

        tvName = findViewById(R.id.tvName);
        tvSurname = findViewById(R.id.tvSurname);
        tvEmail = findViewById(R.id.tvEmail);
    }

    public void onLoad(View view) {

        // Используем ContentResolver для запроса данных
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("UserProvider", "Cursor: " + cursor);

        if (cursor != null && cursor.moveToFirst()) {
            // Получаем данные из первой строки и первой колонки (там где хранится JSON)
            String jsonData = cursor.getString(0);

            // Разбираем JSON
            User user = new Gson().fromJson(jsonData, User.class);

            // Отображаем данные в TextView
            if (user != null) {
                tvName.setText(user.getName());
                tvSurname.setText(user.getSurname());
                tvEmail.setText(user.getEmail());
            }

            cursor.close();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}