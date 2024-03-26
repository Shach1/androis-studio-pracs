package com.example.pr5.task4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pr5.R;

public class MainActivityFor4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for4);

        String[] fruits = getResources().getStringArray(R.array.fruits);

        Spinner fruitSpinner = (Spinner) findViewById(R.id.fruitSpinner);
        ArrayAdapter<String> fruitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, fruits);
        fruitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fruitSpinner.setAdapter(fruitAdapter);



        fruitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Lees go " + fruits[position], Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}