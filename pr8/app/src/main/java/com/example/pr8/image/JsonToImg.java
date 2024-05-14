package com.example.pr8.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonToImg
{
    public static String getDogUrl(String jsonUrl) {
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
                return getDogUrl(jsonUrl);
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

            // Декодируем поток в Bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            connection.disconnect();
            return bitmap;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
