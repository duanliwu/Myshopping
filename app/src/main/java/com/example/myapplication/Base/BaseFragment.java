package com.example.myapplication.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {


    protected Context mContext;

    /**
     * 当该类被系统创建的时候被回调
     *
     * @param savedInstanceState
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        mContext = getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();

    }


    /**
     * 抽象类，由孩子实现，实现不同的效果
     * @return
     */

    public abstract View initView() ;

    /**
     * 当Activity被创建了的时候回调这个方法
     * @param savedInstanceState
     */

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        initData();
    }


    /**
     * 当子类需要联网请求数据的时候，可以重写该方法，在该方法中联网请求
     */

    protected void initData() {
    }

}
