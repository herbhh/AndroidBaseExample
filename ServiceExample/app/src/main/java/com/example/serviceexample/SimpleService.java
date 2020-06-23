package com.example.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Date;

import androidx.annotation.Nullable;

/**
 * </br>
 * <p>
 * &nbsp
 *
 * @author: NIO
 * @since: 1.0.0, 6/23/20 4:05 PM
 * @version: 1.0.0
 */
public class SimpleService extends Service {

    private static final String TAG = SimpleService.class.getSimpleName();

    private SimpleBinder mSimpleBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        mSimpleBinder = new SimpleBinder();
        Log.d(TAG, "======= onCreate() ========");
    }

    Thread startThread = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
                Log.d(TAG, "Current Time : " + new Date(System.currentTimeMillis()));
            }
        }
    });

    Thread bindThread = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
                Log.d(TAG, "doTask Current Time : " + new Date(System.currentTimeMillis()));
            }
        }
    });

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "======= onStartCommand() ======== flags = " + flags + "; startId = " + startId);
        startThread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        startThread.interrupt();
        Log.d(TAG, "======= onDestroy() ========");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "======= onUnbind() ========");
        bindThread.interrupt();
        return super.onUnbind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "======= onBind() ========");
        if (mSimpleBinder != null) {
            return mSimpleBinder;
        } else {
            return null;
        }
    }

    class SimpleBinder extends Binder {
        void doTask() {
            bindThread.start();
            Log.d(TAG, "======= doTask() ========");
        }
    }
}
