package com.example.pr7;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Создание строителя диалоговых окон
        builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(R.layout.custom_dialog); // Позволяет засовывать свои лейауты внутрь окна помимо стандартных кнопок
        builder.setIcon(android.R.drawable.ic_dialog_alert);


    }

    public void onFirstTaskActivity(View view) {
        Intent intent = new Intent(MainActivity.this, Activity1.class);
        startActivity(intent);
    }

    public void onSecondTaskActivity(View view) {
        // Создание и отображение AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();

        Button buttonPositive = dialog.findViewById(R.id.button_positive);
        buttonPositive.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Activity2.class);
            startActivity(intent);
        });

        Button buttonNegative = dialog.findViewById(R.id.button_negative);
        buttonNegative.setOnClickListener(v -> {
            dialog.dismiss();
        });
    }
}