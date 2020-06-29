package com.bud.activityfragmentcommunication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentActivity extends AppCompatActivity implements FirstFragment.FirstListener, SecondFragment.SecondListener{

    private TextView mTvFirst;
    private TextView mTvSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        mTvFirst = (TextView) findViewById(R.id.tv_get_first_message);
        mTvSecond = (TextView) findViewById(R.id.tv_get_second_message);

        final FirstFragment firstFragment = FirstFragment.getInstance();
        firstFragment.setFirstListener(FragmentActivity.this);
        Bundle bundle = new Bundle();
        bundle.putString("activity_send_first", "Activity传递给FirstFragment的数据!");
        firstFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_first, firstFragment);
        transaction.commit();

        findViewById(R.id.btn_send_first_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstFragment.getInstance().setActivityMessage("Activity传递123123123123123123");
            }
        });
        findViewById(R.id.btn_send_second_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondFragment secondFragment = SecondFragment.getInstance();
                secondFragment.setSecondListener(FragmentActivity.this);
                Bundle bundle1 = new Bundle();
                bundle1.putString("activity_send_second", "Activity传递给SecondFragment的数据!");
                secondFragment.setArguments(bundle1);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_second, secondFragment);
                transaction.commit();
            }
        });
    }

    @Override
    public void firstSendActivityData(String msg) {
        mTvFirst.setText(msg);
    }

    @Override
    public void firstSendFragmentData(String msg) {
        SecondFragment.getInstance().setMessage(msg);
    }

    @Override
    public void secondSendActivityData(String msg) {
        mTvSecond.setText(msg);
    }

    @Override
    public void secondSendFragmentData(String msg) {
        FirstFragment.getInstance().setMessage(msg);
    }
}