package com.example.test;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.SplittableRandom;

public class Student implements Serializable
{

    private String name;
    private int age;
    private String group;
    private int desired_grade;

    public Student(String name, int age, String group, int desired_grade)
    {
        this.name = name;
        this.age = age;
        this.group = group;
        this.desired_grade = desired_grade;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGroup() {
        return group;
    }

    public int getDesired_grade() {
        return desired_grade;
    }
}
