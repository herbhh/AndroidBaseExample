package com.bud.activityfragmentcommunication;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * </br>
 * <p>
 * &nbsp
 *
 * @author: NIO
 * @since: 1.0.0, 6/29/20 10:05 AM
 * @version: 1.0.0
 */
public class FirstFragment extends Fragment {

    private final static String TAG = FirstFragment.class.getSimpleName();

    private Context mContext;
    private String mActivityMessage;
    private String mFragmentMessage;
    private TextView mTvActivityMessage;
    private TextView mTvFragmentMessage;

    private static FirstFragment instance;
    private FirstListener mFirstListener;

    public static FirstFragment getInstance() {
        if (instance == null) {
            synchronized (FirstFragment.class) {
                if (instance == null) {
                    instance = new FirstFragment();
                }
            }
        }
        return instance;
    }

    public void setActivityMessage(String msg) {
        mTvActivityMessage.setText(msg);
    }

    public void setMessage(String msg) {
        mTvFragmentMessage.setText(msg);
    }

    public interface FirstListener {
        void firstSendActivityData(String msg);
        void firstSendFragmentData(String msg);
    }

    public void setFirstListener(FirstListener firstListener) {
        mFirstListener = firstListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "====== onCreate() =======");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "====== onCreateView() =======");
        View view = inflater.inflate(R.layout.fragment_first, container,false);
        mTvActivityMessage = view.findViewById(R.id.tv_get_activity_message);
        mTvFragmentMessage = view.findViewById(R.id.tv_get_fragment_message);
        mTvActivityMessage.setText(mActivityMessage);
        mTvFragmentMessage.setText(mFragmentMessage);

        view.findViewById(R.id.btn_send_fragment_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mFirstListener) {
                    mFirstListener.firstSendActivityData("FirstFragment发送给FragmentActivity的数据！！！");
                }
            }
        });
        view.findViewById(R.id.btn_send_second_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mFirstListener) {
                    mFirstListener.firstSendFragmentData("FirstFragment发送给SecondFragment的数据！！！");
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
        if (getArguments() != null) {
            mActivityMessage = getArguments().getString("activity_send_first");
            mFragmentMessage = getArguments().getString("fragment_send_first");
        }
        Log.d(TAG, "====== onAttach() =======" + instance);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "====== onActivityCreated() =======");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "====== onStart() =======");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "====== onResume() =======");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "====== onPause() =======");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "====== onStop() =======");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "====== onDestroyView() =======");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "====== onDestroy() =======");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "====== onDetach() =======");
    }
}
