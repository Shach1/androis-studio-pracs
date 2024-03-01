package com.example.pr4;

import android.os.Parcelable;
import android.os.Parcel;

import androidx.annotation.NonNull;


public class User implements Parcelable
{
    private String name;
    private String surname;

    public User(String name, String surname)
    {
        this.name = name;
        this.surname = surname;
    }

    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags)
    {
        dest.writeString(name);
        dest.writeString(surname);
    }

    protected User(Parcel in) {
        name = in.readString();
        surname = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>()
    {
        @Override
        public User createFromParcel(Parcel in)
        {
            return new User(in);
        }

        @Override
        public User[] newArray(int size)
        {
            return new User[size];
        }
    };
}
