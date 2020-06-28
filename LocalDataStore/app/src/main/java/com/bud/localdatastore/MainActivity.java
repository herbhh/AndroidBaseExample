package com.bud.localdatastore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvValue = (TextView) findViewById(R.id.tv_value);
        findViewById(R.id.btn_save_file).setOnClickListener(this);
        findViewById(R.id.btn_open_sqlite).setOnClickListener(this);
        findViewById(R.id.btn_read_file).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save_file:
                startActivity(new Intent(this, SharedPreferencesActivity.class));
                break;
            case R.id.btn_open_sqlite:
                startActivity(new Intent(this, SQLitectivity.class));
                break;
            case R.id.btn_read_file:
                startActivity(new Intent(this, FileActivity.class));
                break;
        }
    }
}