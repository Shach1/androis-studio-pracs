package com.example.pr12;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class UserProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        Toast.makeText(getContext(), "UserProvider.onCreate()", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d("UserProvider", "query() called");
        MatrixCursor cursor = new MatrixCursor(new String[] {"json"});
        try {
            // Читаем JSON из SharedPreferences
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
            String jsonData = sharedPreferences.getString("userJson", "");

            cursor.addRow(new Object[] {jsonData});
            Log.d("UserProvider", "Cursor JSON: " + jsonData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("UserProvider", "query() returned cursor with " + cursor.getCount() + " rows");
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not implemented");
    }
}