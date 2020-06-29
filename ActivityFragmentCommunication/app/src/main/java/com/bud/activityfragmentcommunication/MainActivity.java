package com.bud.activityfragmentcommunication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_open_second1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("intent_extra", "Extra传递的数据");
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_open_second2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent传递的数据都是在Binder transaction buffer内存缓存区，这个缓存区大小有限制，只有1M.
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("intent_bundle", "Bundle传递的数据");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_open_fragment_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FragmentActivity.class));
            }
        });
    }
}