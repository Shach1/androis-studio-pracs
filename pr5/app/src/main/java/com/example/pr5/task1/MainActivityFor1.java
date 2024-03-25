package com.example.pr5.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pr5.R;

public class MainActivityFor1 extends AppCompatActivity {

    private String[] Categories = {"Овощи", "Фрукты", "Ягоды"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for1);


        ListView CategoryListView = (ListView) findViewById(R.id.MainListForFirstAct);
        ArrayAdapter<String> CategoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Categories);
        CategoryListView.setAdapter(CategoryAdapter);

        CategoryListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        switch (position)
                        {
                            case 0:
                            {
                                Intent intent = new Intent(MainActivityFor1.this, VegetableActivity.class);
                                startActivity(intent);
                                break;
                            }
                            case 1:
                            {
                                Intent intent = new Intent(MainActivityFor1.this, FruitsActivity.class);
                                startActivity(intent);
                                break;
                            }
                            case 2:
                                Intent intent = new Intent(MainActivityFor1.this, BerriesActivity.class);
                                startActivity(intent);
                                break;
                        }
                    }
                });
    }
}