package com.example.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class DemoWorker extends Worker {

    public DemoWorker(@NonNull Context context, @NonNull WorkerParameters params){
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {

        for (int i = 0; i < 100; i++) {
            Log.i("TAGRES", " count is " + i);
        }

        return Result.success();
    }
}
