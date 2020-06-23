package com.bud.activityexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SecondActivity extends Activity {

    public final static String TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.btn_open_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_open_third).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.THIRD");
                startActivity(intent);
            }
        });
        Log.d(TAG, "========onCreate()========");
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
