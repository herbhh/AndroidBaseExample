package com.bud.localdatastore;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

/**
 * </br>
 * <p>
 * &nbsp
 *
 * @author: NIO
 * @since: 1.0.0, 6/28/20 2:24 PM
 * @version: 1.0.0
 */
public class SharedPreferencesActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        mTvValue = (TextView) findViewById(R.id.tv_value);
        findViewById(R.id.btn_save_file).setOnClickListener(this);
        findViewById(R.id.btn_open_sqlite).setOnClickListener(this);
        findViewById(R.id.btn_read_file).setOnClickListener(this);
        findViewById(R.id.btn_add_long).setOnClickListener(this);
        findViewById(R.id.btn_add_float).setOnClickListener(this);
        findViewById(R.id.btn_add_set).setOnClickListener(this);
        findViewById(R.id.btn_remove_string).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_get_string).setOnClickListener(this);
        findViewById(R.id.btn_get_int).setOnClickListener(this);
        findViewById(R.id.btn_get_boolean).setOnClickListener(this);
        findViewById(R.id.btn_get_float).setOnClickListener(this);
        findViewById(R.id.btn_get_long).setOnClickListener(this);
        findViewById(R.id.btn_get_set).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save_file:
                SharedPreferencesHelper.getInstance(this).putString("testString", "测试字符串");
                break;
            case R.id.btn_open_sqlite:
                SharedPreferencesHelper.getInstance(this).putBoolean("testBoolean", true);
                break;
            case R.id.btn_read_file:
                SharedPreferencesHelper.getInstance(this).putInt("testInt", 110);
                break;
            case R.id.btn_add_long:
                SharedPreferencesHelper.getInstance(this).putLong("testLong", 10000000000L);
                break;
            case R.id.btn_add_float:
                SharedPreferencesHelper.getInstance(this).putFloat("testFloat", 123.123f);
                break;
            case R.id.btn_add_set:
                List<String> list = new ArrayList<>();
                list.add("test1");
                list.add("test2");
                list.add("test3");
                SharedPreferencesHelper.getInstance(this).putStringSet("testSet", new HashSet<>(list));
                break;
            case R.id.btn_get_string:
                String valueString = SharedPreferencesHelper.getInstance(this).getString("testString");
                mTvValue.setText(valueString);
                break;
            case R.id.btn_get_int:
                int valueInt = SharedPreferencesHelper.getInstance(this).getInt("testInt");
                mTvValue.setText(valueInt + "");
                break;
            case R.id.btn_get_long:
                Long valueLong = SharedPreferencesHelper.getInstance(this).getLong("testLong");
                mTvValue.setText(valueLong + "");
                break;
            case R.id.btn_get_float:
                float valueFloat = SharedPreferencesHelper.getInstance(this).getFloat("testFloat");
                mTvValue.setText(valueFloat + "");
                break;
            case R.id.btn_get_boolean:
                boolean valueBoolean = SharedPreferencesHelper.getInstance(this).getBoolean("testBoolean");
                mTvValue.setText(valueBoolean + "");
                break;
            case R.id.btn_get_set:
                HashSet<String> hashSet = (HashSet<String>) SharedPreferencesHelper.getInstance(this).getSet("testSet");
                mTvValue.setText(hashSet.toString());
                break;
            case R.id.btn_remove_string:
                SharedPreferencesHelper.getInstance(this).remove("testString");
                break;
            case R.id.btn_clear:
                SharedPreferencesHelper.getInstance(this).clear();
                break;
        }
    }
}
