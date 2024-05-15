package com.example.pr9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {

    private String fileName;
    private String fileContent;
    private EditText fileNameEditText = null;
    private EditText fileContentEditText = null;
    private Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        fileNameEditText = findViewById(R.id.etFileName);
        fileContentEditText = findViewById(R.id.etFileContent);
        context = getApplicationContext();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("fileNameEditText", fileNameEditText.getText().toString());
        outState.putString("fileContentEditText", fileContentEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        fileNameEditText.setText(savedInstanceState.getString("fileNameEditText"));
        fileContentEditText.setText(savedInstanceState.getString("fileContentEditText"));
    }

    public void onSave(View view)
    {
        if (checkFileName() && checkFileContent())
        {
            File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            if (!storageDir.exists()) {
                // Создаем директорию, если она не существует
                storageDir.mkdir();
            }
            File file = new File(storageDir, fileName);
            try {
                if (!file.exists()) {
                    // Создаем файл, если он не существует
                    boolean created = file.createNewFile();
                    if (created) {
                        // Записываем данные в файл
                        FileWriter writer = new FileWriter(file);
                        writer.append(fileContent);
                        writer.flush();
                        writer.close();
                        Toast.makeText(this,"Файл успешно создан!", Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else Toast.makeText(context, "Введите название файла и информацию!", Toast.LENGTH_SHORT).show();
    }

    public void onDelete(View view) {
        if (checkFileName())
        {
            File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(storageDir, fileName);
            if (file.exists())
            {
                boolean deleted = file.delete();
                if (deleted)
                {
                    // Файл успешно удален
                    Toast.makeText(MainActivity2.this,"File deleted", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else Toast.makeText(context, "Введите название файла!", Toast.LENGTH_SHORT).show();
    }

    private boolean checkFileName() {
        fileName = fileNameEditText.getText().toString();
        if (!fileName.isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean checkFileContent() {
        fileContent = fileContentEditText.getText().toString();
        if (!fileContent.isEmpty()) {
            return true;
        }
        return false;
    }
}