package com.bud.activityfragmentcommunication;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView mTvIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mTvIntent = (TextView) findViewById(R.id.tv_intent);

        String extra = getIntent().getStringExtra("intent_extra");
        String bundle = getIntent().getExtras().getString("intent_bundle");

        if (!TextUtils.isEmpty(extra)) {
            mTvIntent.setText(extra);
        }

        if (!TextUtils.isEmpty(bundle)) {
            mTvIntent.setText(bundle);
        }
    }
}