package com.example.pr6.task2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.pr6.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity2 extends AppCompatActivity {

    private ActionBar actionBar;
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        fragmentManager = getSupportFragmentManager();
        actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true); // Показать кнопку назад
            actionBar.setTitle("Главная"); // Установить заголовок
        }

        // Создание обработчика нажатий на элементы меню
        bottomNavigationView = findViewById(R.id.bottomAppBar);
        bottomNavigationView.setOnItemSelectedListener(
                (item) -> {
                    if(item.getItemId() == R.id.search)
                    {
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView,
                                SearchFragment.class, null).commit();
                    }
                    else if(item.getItemId() == R.id.home)
                    {
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView,
                                HomeFragment.class, null).commit();
                    }
                    else if(item.getItemId() == R.id.user)
                    {
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView,
                                UserFragment.class, null).commit();
                    }
                    actionBar.setTitle(item.getTitle());
                    Toast toast = Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 80);
                    toast.show();
                    return true;
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    // Метод для создания и отображения меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        actionBar.setTitle("Search");
        return super.onCreateOptionsMenu(menu);
    }

    // Метод для обработки нажатий на элементы меню
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        bottomNavigationView.setSelectedItemId(item.getItemId());
        return super.onOptionsItemSelected(item);
    }


}