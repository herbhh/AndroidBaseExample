package com.bud.activityfragmentcommunication;

import android.content.Context;
import android.os.Bundle;
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
public class SecondFragment extends Fragment {

    private Context mContext;
    private String mActivityMessage;
    private String mFragmentMessage;

    private static SecondFragment instance;

    public static SecondFragment getInstance() {
        if (instance == null) {
            synchronized (SecondFragment.class) {
                if (instance == null) {
                    instance = new SecondFragment();
                }
            }
        }
        return instance;
    }

    private SecondListener mSecondListener;

    public void setSecondListener(SecondListener mSecondListener) {
        this.mSecondListener = mSecondListener;
    }

    public interface SecondListener{
        void secondSendActivityData(String msg);
        void secondSendFragmentData(String msg);
    }

    public void setMessage(String message){
        if (null != mTvFragmentMessage)
            mTvFragmentMessage.setText(message);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private TextView mTvActivityMessage;
    private TextView mTvFragmentMessage;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        mTvActivityMessage = view.findViewById(R.id.tv_get_activity_message);
        mTvFragmentMessage = view.findViewById(R.id.tv_get_fragment_message);
        mTvActivityMessage.setText(mActivityMessage);
        mTvFragmentMessage.setText(mFragmentMessage);

        view.findViewById(R.id.btn_send_fragment_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSecondListener != null) {
                    mSecondListener.secondSendActivityData("SecondFragment发送给FragmentActivity的数据!!!");
                }
            }
        });
        view.findViewById(R.id.btn_send_first_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSecondListener != null) {
                    mSecondListener.secondSendFragmentData("SecondFragment发送给FirstFragment的数据!!!");
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
            mActivityMessage = getArguments().getString("activity_send_second");
            mFragmentMessage = getArguments().getString("fragment_send_second");
        }
    }
}
