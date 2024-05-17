package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper
{
    private static final String TABLE_NAME = "Schedule";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NUMBER_PAIR = "number_pair";
    private static final String COLUMN_TEACHER = "teacher";
    private static final String COLUMN_SUBJECT = "subject";
    private static final String COLUMN_CLASSROOM = "classroom";
    private static final String COLUMN_TIME = "time";

    public DBHelper(Context context)
    {
        super(context, "lessons.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NUMBER_PAIR + " INT, "
                + COLUMN_TEACHER + " TEXT, "
                + COLUMN_SUBJECT + " TEXT, "
                + COLUMN_CLASSROOM + " TEXT, "
                + COLUMN_TIME + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addLesson(Lesson lesson) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NUMBER_PAIR, lesson.getNumberPair());
        cv.put(COLUMN_TEACHER, lesson.getTeacher());
        cv.put(COLUMN_SUBJECT, lesson.getSubject());
        cv.put(COLUMN_CLASSROOM, lesson.getClassroom());
        cv.put(COLUMN_TIME, lesson.getTime());
        long result = db.insert(TABLE_NAME, null, cv);
        db.close();
        return result != -1;
    }

    public boolean deleteLesson(String subject) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COLUMN_SUBJECT + " = ?", new String[]{subject});
        db.close();
        return result > 0;
    }

    public List<Lesson> findLessonsBySubject(String subject) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME
                + " WHERE " + COLUMN_SUBJECT + " = '" + subject + "'", null);

        List<Lesson> lessonList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst())
        {
            do {
                Lesson lesson = new Lesson(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5));
                lessonList.add(lesson);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
            return lessonList;
        }
        if (cursor != null) cursor.close();
        db.close();
        return null;
    }

    public List<Lesson> getAllLessons() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        List<Lesson> lessonList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst())
        {
            do {
                Lesson lesson = new Lesson(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5));
                lessonList.add(lesson);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
            return lessonList;
        }
        if (cursor != null) cursor.close();
        db.close();
        return null;
    }
}
