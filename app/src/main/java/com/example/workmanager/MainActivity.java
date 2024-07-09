package com.example.workmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.Worker;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);

        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true)
                .build();

        WorkRequest worker = new OneTimeWorkRequest
                .Builder(DemoWorker.class)
                .setConstraints(constraints)
                .build();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkManager.getInstance(getApplicationContext()).enqueue(worker);
            }
        });

        //  display worker status

        WorkManager.getInstance(getApplicationContext())
                .getWorkInfoByIdLiveData(worker.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if (workInfo != null){
                            Toast.makeText(getApplicationContext(),
                                    "Status  " + workInfo.getState().name(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}