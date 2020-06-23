package com.example.serviceexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    private SimpleService.SimpleBinder mSimpleBinder;
    private boolean isBind;
    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "====== onServiceConnected() =======");
            mSimpleBinder = (SimpleService.SimpleBinder) iBinder;
            isBind = true;
            mSimpleBinder.doTask();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "====== onServiceDisconnected() =======");
            mSimpleBinder = null;
            isBind = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_stop).setOnClickListener(this);
        findViewById(R.id.btn_bind).setOnClickListener(this);
        findViewById(R.id.btn_unbind).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                Intent startIntent = new Intent(this, SimpleService.class);
                startService(startIntent);
                break;
            case R.id.btn_stop:
                Intent stopIntent = new Intent(this, SimpleService.class);
                stopService(stopIntent);
                break;
            case R.id.btn_bind:
                Intent bindIntent = new Intent(this, SimpleService.class);
                bindService(bindIntent, mServiceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind:
                if (isBind) {
                    unbindService(mServiceConnection);
                }
                break;
            default:
                break;
        }
    }
}