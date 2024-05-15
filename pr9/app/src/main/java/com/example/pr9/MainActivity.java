package com.example.pr9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private String fileName;
    private String fileContent;
    private EditText fileNameEditText = null;
    private EditText fileContentEditText = null;
    private TextView fileContentTextView = null;
    private Context context = null;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileNameEditText = findViewById(R.id.etFileName);
        fileContentEditText = findViewById(R.id.etFileContent);
        fileContentTextView = findViewById(R.id.tvFileContent);
        context = getApplicationContext();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("fileNameEditText", fileNameEditText.getText().toString());
        outState.putString("fileContentEditText", fileContentEditText.getText().toString());
        outState.putString("fileContentTextView", fileContentTextView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        fileNameEditText.setText(savedInstanceState.getString("fileNameEditText"));
        fileContentEditText.setText(savedInstanceState.getString("fileContentEditText"));
        fileContentTextView.setText(savedInstanceState.getString("fileContentTextView"));
    }

    public void onSave(View view) {
        if (checkFileName() && checkFileContent()) {
            try(FileOutputStream fos = context.openFileOutput(fileName, MODE_PRIVATE))
            {
                fos.write(fileContent.getBytes());
                fileContentEditText.setText("");
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else Toast.makeText(context, "Введите название файла и информацию!", Toast.LENGTH_SHORT).show();
    }

    public void onRead(View view)
    {
        if (checkFileName())
        {
            try(FileInputStream fis = context.openFileInput(fileName))
            {
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                StringBuilder stringBuilder = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(isr))
                {
                    String line = reader.readLine();
                    while (line != null)
                    {
                        stringBuilder.append(line).append('\n');
                        line = reader.readLine();
                    }
                    if (stringBuilder.length() > 0)
                    {
                        fileContentTextView.setText(stringBuilder.toString());
                    }
                }
            } catch (Exception e)
            {
                Toast.makeText(context, "Название файла введено неверно!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
        else Toast.makeText(context, "Введите название файла!", Toast.LENGTH_SHORT).show();
    }

    public void onDelete(View view) {
        if (checkFileName())
        {
            try(FileInputStream fis = context.openFileInput(fileName)) {
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Подтверждение");
                builder.setMessage("Вы уверены, что хотите выполнить это действие?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(context.deleteFile(fileName))
                        {
                            Toast.makeText(context, "Файл " + fileName + " удален", Toast.LENGTH_SHORT).show();

                            fileNameEditText.setText("");
                        }
                    }
                });

                builder.setNegativeButton("Отмена", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            } catch (Exception e)
            {
                Toast.makeText(context, "Название файла введено неверно!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
        else Toast.makeText(context, "Введите название файла!", Toast.LENGTH_SHORT).show();
    }

    public void onAddContent(View view) {
        if (checkFileName() && checkFileContent()) {
            try(FileOutputStream fos = context.openFileOutput(fileName, MODE_APPEND))
            {
                fos.write((" " + fileContent).getBytes());
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else Toast.makeText(context, "Введите название файла и информацию!", Toast.LENGTH_SHORT).show();
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

    public void onNextActivity(View view)
    {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}