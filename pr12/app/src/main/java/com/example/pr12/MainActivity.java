package com.example.pr12;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.pr12.entity.User;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    public static final Uri CONTENT_URI = Uri.parse("content://com.example.app.provider/user");
    private User user;
    private Gson gson;
    private String userJson;

    private EditText etName;
    private EditText etSurname;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        email = findViewById(R.id.etEmail);
        gson = new Gson();
    }



    public void onSave(View view)
    {
        parseUserFromUI();
        if (user == null)
        {
            return;
        }

        try {
            userJson = parseUserToJson(user);
            Log.i("User","data saved:\n" + userJson);
            // save json to file
        }
        catch (Exception e) {}
    }

    private String parseUserToJson(User user)
    {
        return gson.toJson(user);
    }

    private void parseUserFromUI()
    {
        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String email = this.email.getText().toString();

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty())
        {

            user = null;
            return;
        }

        user = new User(name, surname, email);
    }
}