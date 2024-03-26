package com.example.pr5.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.pr5.R;

import java.util.ArrayList;
import java.util.Collections;

public class FruitsActivity extends AppCompatActivity {

    private String[] FRUITS;

    private ArrayList<String> fruits = new ArrayList<String>();
    private ArrayList<String> selectedFruits = new ArrayList<String>();
    private ListView fruitList;
    private ArrayAdapter<String> fruitsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits);

        FRUITS = getResources().getStringArray(R.array.fruits);

        //Копируем в ArrayList fruits массив строк FRUITS
        Collections.addAll(fruits, FRUITS);

        fruitList = (ListView) findViewById(R.id.ThirdListForFirstTask);
        fruitsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, fruits);
        fruitList.setAdapter(fruitsAdapter);

        fruitList.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        // Проверяем item на нажатие, если нажат добавляем в соответствующий список
                        String fruit = fruitsAdapter.getItem(position);
                        if (fruitList.isItemChecked(position)) selectedFruits.add(fruit);
                        else selectedFruits.remove(fruit);
                    }
                }
        );
    }

    public void onRemove(View view)
    {
        if (!selectedFruits.isEmpty()) fruits.removeAll(selectedFruits);
        fruitList.clearChoices();
        selectedFruits.clear();
        fruitsAdapter.notifyDataSetChanged();
    }

    public void onAdd(View view)
    {
        EditText newFruit = findViewById(R.id.editTextText);
        String fruit = newFruit.getText().toString();
        newFruit.setText("");
        if (!fruit.isEmpty())
        {
            fruits.add(fruit);
            fruitsAdapter.notifyDataSetChanged();
        }
    }
}