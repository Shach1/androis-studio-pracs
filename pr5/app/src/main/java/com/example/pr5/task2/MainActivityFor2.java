package com.example.pr5.task2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;
import com.example.pr5.R;
import java.util.ArrayList;


public class MainActivityFor2 extends AppCompatActivity {

    ArrayList<State> states = new ArrayList<State>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for2);

        setInitialData();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        StateAdapter stateAdapter = new StateAdapter(states);
        recyclerView.setAdapter(stateAdapter);
    }

    private void setInitialData()
    {
        states.add(new State("Россия", "Москва", R.drawable.russian));
        states.add(new State("Швеция", "Стокгольм", R.drawable.swedish));
        states.add(new State("Америка", "Вашингтон", R.drawable.usa));
        states.add(new State("Великобритания", "Лондон", R.drawable.british));
    }

}