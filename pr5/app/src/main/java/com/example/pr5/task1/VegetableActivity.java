package com.example.pr5.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pr5.R;

public class VegetableActivity extends AppCompatActivity {

    private String[] vegetables = {"Морковь", "Помидоры", "Огурцы", "Картофель", "Брокколи",
            "Лук", "Шпинат", "Капуста", "Перец", "Чеснок", "Свекла", "Цветная капуста", "Тыква",
            "Кабачок", "Баклажан", "Лук-порей", "Спаржа", "Редис", "Черри-помидоры", "Фасоль"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetable);

        ListView vegetablesList = (ListView) findViewById(R.id.SecondListForFirstTask);
        ArrayAdapter<String> vegetablesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vegetables);
        vegetablesList.setAdapter(vegetablesAdapter);

    }
}