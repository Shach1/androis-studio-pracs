package com.example.pr8;


import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import android.graphics.Bitmap;
import android.os.StrictMode;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.work.WorkManager;
import com.example.pr8.image.JsonToImg;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public final String TAG = "MainActivity";
    private final String jsonUrl = "https://random.dog/woof.json";
    private ImageView imageView;

    private Data data;
    private OneTimeWorkRequest workRequest1, workRequest2, workRequest3; //для 3 последовательных задач
    private OneTimeWorkRequest workRequest4, workRequest5; //для 2 параллельных задач

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        imageView = findViewById(R.id.imageView);
    }

    //запуск 3 задач последовательно
    public void onSerial(View view)
    {
        data = new Data.Builder()
                .putString("tag", "workRequest1").build();
        workRequest1 = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(data).build();

        data = new Data.Builder()
                .putString("tag", "workRequest2").build();
        workRequest2 = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(data).build();

        data = new Data.Builder()
                .putString("tag", "workRequest3").build();
        workRequest3 = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(data).build();

        WorkManager.getInstance(getApplicationContext())
                .beginWith(workRequest1)
                .then(workRequest2)
                .then(workRequest3)
                .enqueue();
    }

    //запуск 2 задач параллельно
    public void onParallel(View view)
    {
        data = new Data.Builder()
                .putString("tag", "workRequest4")
                .build();

        workRequest4 = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(data)
                .build();

        data = new Data.Builder()
                .putString("tag", "workRequest5")
                .build();
        workRequest5 = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(data)
                .build();

        List<OneTimeWorkRequest> workRequests = new ArrayList<>();
        workRequests.add(workRequest4);
        workRequests.add(workRequest5);
        WorkManager.getInstance(getApplicationContext())
                .beginWith(workRequests)
                .enqueue();
    }

    public void onJustClick(View view)
    {
        Log.i(TAG, "Just clicked");
    }

    public void onDisplayDog(View view)
    {
        new Thread(() -> {
            final Bitmap bitmap = JsonToImg.getImageFromUrl(JsonToImg.getDogUrl(jsonUrl));
            runOnUiThread(() -> imageView.setImageBitmap(bitmap));
        }).start();
    }
}