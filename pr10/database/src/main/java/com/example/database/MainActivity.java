package com.example.database;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etNumberPair;
    private EditText etTeacher;
    private EditText etSubject;
    private EditText etClassroom;
    private EditText etTime;
    private RecyclerView rvSchedule;
    private DBHelper dbHelper;
    private List<Lesson> lessonList;
    private int LessonCount;
    private LessonAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("MainActivity", "ACTIVITY CREATED");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumberPair = findViewById(R.id.etNumberPair);
        etTeacher = findViewById(R.id.etTeacher);
        etSubject = findViewById(R.id.etSubject);
        etClassroom = findViewById(R.id.etClassroom);
        etTime = findViewById(R.id.etTime);

        rvSchedule = findViewById(R.id.rvSchedule);
        dbHelper = new DBHelper(this);

        lessonList = dbHelper.getAllLessons();
        //LessonCount = lessonList.size();
        adapter = new LessonAdapter(lessonList);

        rvSchedule.setLayoutManager(new LinearLayoutManager(this));
        rvSchedule.setAdapter(adapter);
    }


    public void onSave(View view)
    {
        String numberPairStr =  etNumberPair.getText().toString();
        String teacher = etTeacher.getText().toString();
        String subject = etSubject.getText().toString();
        String classroom = etClassroom.getText().toString();
        String time = etTime.getText().toString();
        int numberPair;

        if (numberPairStr.isEmpty() || teacher.isEmpty() || subject.isEmpty() || classroom.isEmpty() || time.isEmpty())
        {
            Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            numberPair = Integer.parseInt(numberPairStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Номер пары должен быть числом!", Toast.LENGTH_SHORT).show();
            return;
        }

        LessonCount = lessonList.get(lessonList.size() - 1).getId();
        Lesson lesson = new Lesson(LessonCount + 1, numberPair, teacher, subject, classroom, time);
        if (dbHelper.addLesson(lesson))
        {
            lessonList.add(lesson);
            LessonCount++;
            adapter.notifyItemInserted(lessonList.size() - 1);
            Toast.makeText(this, "Занятие успешно добавлено!", Toast.LENGTH_SHORT).show();
            etTime.setText("");
            etClassroom.setText("");
            etNumberPair.setText("");
            etSubject.setText("");
            etTeacher.setText("");

        } else
        {
            Toast.makeText(this, "Не удалось добавить занятие", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void onDelete(View view)
    {
        String subject = etSubject.getText().toString();
        if (subject.isEmpty())
        {
            Toast.makeText(this, "Введите предмет!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (dbHelper.deleteLesson(subject))
        {
            lessonList.clear();
            lessonList.addAll(dbHelper.getAllLessons());
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Занятие успешно удалено!", Toast.LENGTH_SHORT).show();
            etSubject.setText("");
        } else
        {
            Toast.makeText(this, "Занятие не найдено", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void onFind(View view)
    {
        String subject = etSubject.getText().toString();
        if (subject.isEmpty())
        {
            Toast.makeText(this, "Введите предмет!", Toast.LENGTH_SHORT).show();
            return;
        }
        List<Lesson> lessons = dbHelper.findLessonsBySubject(subject);
        if (lessons == null)
        {
            Toast.makeText(this, "Занятий по данному предмету не найдено", Toast.LENGTH_SHORT).show();
            return;
        }
        lessonList.clear();
        lessonList.addAll(lessons);
        adapter.notifyDataSetChanged();
        etSubject.setText("");
    }

    @SuppressLint("NotifyDataSetChanged")
    public void onDisplayAllSchedule(View view)
    {
        List<Lesson> lessons = dbHelper.getAllLessons();
        if (lessons == null)
        {
            Toast.makeText(this, "Расписание пусто", Toast.LENGTH_SHORT).show();
            return;
        }
        lessonList.clear();
        lessonList.addAll(lessons);
        adapter.notifyDataSetChanged();
    }

}