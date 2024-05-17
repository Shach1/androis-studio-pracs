package com.example.database;

public class Lesson
{
    private int id;
    private int numberPair;
    private String teacher;
    private String subject;
    private String classroom;
    private String time;

    public Lesson(int id, int numberPair, String teacher, String subject, String classroom, String time) {
        this.id = id;
        this.numberPair = numberPair;
        this.teacher = teacher;
        this.subject = subject;
        this.classroom = classroom;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberPair() {
        return numberPair;
    }

    public void setNumberPair(int numberPair) {
        this.numberPair = numberPair;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
