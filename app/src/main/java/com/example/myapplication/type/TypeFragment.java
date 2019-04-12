package com.example.myapplication.type;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.Base.BaseFragment;

import static android.content.ContentValues.TAG;

public class TypeFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {

        Log.e(TAG,"分类的Fragment的UI被初始化了");

        textView = new TextView(mContext);
        textView.setGravity( Gravity.CENTER);
        textView.setText( "分类的页面" );
        textView.setTextSize(25);
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
    }
}
