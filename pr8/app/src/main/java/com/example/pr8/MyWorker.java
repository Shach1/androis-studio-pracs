package com.example.pr8;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker
{
    private String TAG = "MyWorker";

    public MyWorker(@NonNull Context context,
                    @NonNull WorkerParameters workerParams)
    {
        super(context, workerParams);
        TAG = workerParams.getInputData().getString("tag");
    }

    @NonNull
    @Override
    public Result doWork()
    {
        Log.v(TAG, "Work is in progress");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Work finished");
        return Result.success();
    }
}
