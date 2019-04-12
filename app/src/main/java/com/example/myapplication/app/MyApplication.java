package com.example.myapplication.app;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static Context mContext;

    public static Context getContext(){
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        //初始化OkhttpUtils
        initOkhttpClient();

    }

    private void initOkhttpClient() {
    }
}
