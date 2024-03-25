package com.example.pr5.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pr5.R;

public class BerriesActivity extends AppCompatActivity {

    private String[] berries = {"Клубника", "Земляника", "Малина", "Ежевика",
            "Черника", "Голубика", "Клюква", "Брусника", "Смородина",
            "Черная смородина", "Крыжовник", "Боярышник", "Шиповник",
            "Арония", "Белая смородина"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berries);

        ListView berriesList = (ListView) findViewById(R.id.FourthListForFirstTask);
        ArrayAdapter<String> berriesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, berries);
        berriesList.setAdapter(berriesAdapter);
    }
}