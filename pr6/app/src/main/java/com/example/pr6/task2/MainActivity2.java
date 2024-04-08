package com.example.pr6.task2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.pr6.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity2 extends AppCompatActivity {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true); // Показать кнопку назад
            actionBar.setTitle("Главная"); // Установить заголовок
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Создание обработчика нажатий на элементы меню
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomAppBar);
        bottomNavigationView.setOnItemSelectedListener(
                (item) -> {
                    Toast toast = Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT);;
                    toast.setGravity(Gravity.TOP, 0, 80);
                    toast.show();
                    return false;
                });
    }

    // Метод для создания и отображения меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Метод для обработки нажатий на элементы меню
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId() == R.id.home)
        {
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (item.getItemId() == R.id.search)
        {
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (item.getItemId() == R.id.user)
        {
            Toast.makeText(this, "User", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}