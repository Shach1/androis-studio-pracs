package com.example.pr8.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonToImg extends Worker
{
    private String firstUrl;
    private ImageView imageView;
    public JsonToImg(@NonNull Context context,
                     @NonNull WorkerParameters workerParams)
    {
        super(context, workerParams);
        firstUrl = workerParams.getInputData().getString("firstUrl");
        //imageView = (ImageView) workerParams.getInputData().getKeyValueMap().get("imageView");
    }

    @NonNull
    @Override
    public Result doWork()
    {
        String DogUrl = getGogUrl(firstUrl);
        Bitmap bitmap = getImageFromUrl(DogUrl);
        Log.d("JsonToImg", "bitmap получен");
        //imageView.setImageBitmap(bitmap);
        return Result.success();
    }

    public static String getGogUrl(String jsonUrl) {
        String dogUrl = "some url";
        try
        {
            // Открываем поток для чтения данных с URL
            BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(jsonUrl).openStream()));

            // Читаем JSON-файл в строку
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            reader.close(); // Закрываем поток чтения

            Gson gson = new Gson();
            dogUrl = gson.fromJson(jsonContent.toString(), JsonObject.class).getUrl();
            if (!(dogUrl.contains(".jpg") || dogUrl.contains(".JPG") || dogUrl.contains(".png"))) {
                return getGogUrl(jsonUrl);
            } else {
                return dogUrl;
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return dogUrl;
    }

    public static Bitmap getImageFromUrl(String url_str) {
        try {
            // Открываем соединение
            URL url = new URL(url_str);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();

            // Получаем входной поток
            InputStream input = connection.getInputStream();
            connection.disconnect();

            // Декодируем поток в Bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
