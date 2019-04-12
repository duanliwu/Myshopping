package com.example.myapplication.shoppingcart;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.Base.BaseFragment;

import static android.content.ContentValues.TAG;

public class ShoppingCartFragment extends BaseFragment {
    private TextView textView;

    @Override
    public View initView() {
        Log.e(TAG,"购物车的Fragment的UI被初始化了");

        textView = new TextView( mContext );
        textView.setTextSize( 25 );
        textView.setGravity( Gravity.CENTER );
        textView.setText( "Shopping 页面" );
        return textView;
    }

}
