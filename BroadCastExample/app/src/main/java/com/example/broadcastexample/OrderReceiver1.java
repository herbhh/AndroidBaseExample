package com.example.broadcastexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * </br>
 * <p>
 * &nbsp
 *
 * @author: NIO
 * @since: 1.0.0, 6/23/20 5:20 PM
 * @version: 1.0.0
 */
public class OrderReceiver1 extends BroadcastReceiver {

    private static final String TAG = OrderReceiver1.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "====== onReceive() ======");
    }
}
