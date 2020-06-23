package com.example.broadcastexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DynamicReceiver mDynamicReceiver;
    private final static String DYNAMIC_ACTION="DYNAMIC_ACTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_send_simple).setOnClickListener(this);
        findViewById(R.id.btn_send_order).setOnClickListener(this);
        findViewById(R.id.btn_register_dynamic).setOnClickListener(this);
        findViewById(R.id.btn_send_dynamic).setOnClickListener(this);
        findViewById(R.id.btn_unregister_dynamic).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_simple:
                Intent sIntent = new Intent("android.intent.action.SIMPLERECEIVER");
                sendBroadcast(sIntent);
                break;
            case R.id.btn_send_order:
                Intent oIntent = new Intent("android.intent.action.ORDERRECEIVER");
                sendOrderedBroadcast(oIntent, "bud.permission.ORDERRECEIVER");
                break;
            case R.id.btn_register_dynamic:
                register();
                break;
            case R.id.btn_send_dynamic:
                Intent dIntent = new Intent(DYNAMIC_ACTION);
                sendBroadcast(dIntent);
                break;
            case R.id.btn_unregister_dynamic:
                unregister();
                break;
            default:

                break;
        }
    }

    private void register() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DYNAMIC_ACTION);
        mDynamicReceiver = new DynamicReceiver();
        registerReceiver(mDynamicReceiver, intentFilter);
    }

    public void unregister() {
        if (mDynamicReceiver != null) {
            unregisterReceiver(mDynamicReceiver);
        }
    }
}