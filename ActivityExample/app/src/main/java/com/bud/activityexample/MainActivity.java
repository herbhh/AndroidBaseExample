package com.bud.activityexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_open_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName componentName = new ComponentName("com.bud.activityexample", "com.bud.activityexample.SecondActivity");
                intent.setComponent(componentName);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_open_third).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName("com.bud.activityexample", "com.bud.activityexample.ThirdActivity");
                startActivity(intent);
            }
        });
        Log.d(TAG, "========onCreate()========");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "========onRestart()========");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "========onStart()========");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "========onResume()========");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "========onPause()========");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "========onStop()========");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "========onDestroy()========");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "========onSaveInstanceState()========");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "========onRestoreInstanceState()========");
    }
}