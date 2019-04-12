package com.example.myapplication.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.Base.BaseFragment;

import static android.content.ContentValues.TAG;

public class CommunityFragment extends BaseFragment {

    private TextView textView;
    @Override
    public View initView() {

        Log.e(TAG,"发现的Fragment的UI被初始化了");

        textView = new TextView(mContext);
        textView.setGravity( Gravity.CENTER);
        textView.setTextSize(25);
        textView.setText( "发现页面" );

        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
    }
}
