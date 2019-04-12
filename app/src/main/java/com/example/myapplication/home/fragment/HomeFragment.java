package com.example.myapplication.home.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.myapplication.Base.BaseFragment;
import com.example.myapplication.R;
import com.example.myapplication.home.adapter.HomeFragmentAdapter;
import com.example.myapplication.home.bean.ResultBeanData;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

import static android.content.ContentValues.TAG;

public class HomeFragment extends BaseFragment {

    private RecyclerView rvHome;
    private ImageView ib_top;
    private TextView tv_search_home;
    private TextView tv_message_home;



    private HomeFragmentAdapter adapter;

    /**
     * 返回的数据
     */

    private List<ResultBeanData.ResultBean> resultBean;

    @Override
    public View initView() {
        Log.e(TAG,"主页Fragment的UI被初始化了");

        View view = View.inflate( mContext, R.layout.fragment_home,null );
        rvHome = view.findViewById(R.id.rv_home);
        rvHome.setLayoutManager(new LinearLayoutManager(mContext));//这里用线性显示 类似于listview

//        rvHome.setLayoutManager(new GridLayoutManager(mContext, 2));//这里用线性宫格显示 类似于grid view
//        rvHome.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流
        ib_top = view.findViewById( R.id.ib_top );
        tv_search_home = view.findViewById( R.id.tv_search_home );
        tv_message_home = view.findViewById( R.id.tv_message_home );
        //设置点击事件
        initListener();
        return view;
    }


    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"主页的Fragment的数据被初始化了");

        //联网请求主页数据
        getDataFromNet();

    }

    private void getDataFromNet() {

        String url = "https://api.apiopen.top/musicRankings";
        OkHttpUtils
                .get()
                .url( url )
                .build()
                .execute( new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,"数据请求失败了");
                    }
                    // response 请求成功的数据
                    @Override
                    public void onResponse(String response, int id) {
                        //解析数据
                        processData(response);
                    }
                } );
    }

    private void processData(String json) {

        Log.e(TAG,"成功后返回的数据"+json);

        ResultBeanData resultBeanData = JSON.parseObject(json, ResultBeanData.class);
        resultBean = resultBeanData.getResult();

        if (resultBean != null){
            adapter = new HomeFragmentAdapter(mContext,resultBean);
            rvHome.setAdapter(adapter);
        }
    }

    private void initListener() {

        //置顶的点击事件
        ib_top.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到顶部
                rvHome.scrollToPosition( 0 );
            }
        } );


        //搜索的点击事件
        tv_search_home.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText( mContext,"搜索",Toast.LENGTH_SHORT ).show();

            }
        } );

        //消息的监听
        tv_message_home.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( mContext,"进去消息中心",Toast.LENGTH_SHORT ).show();
            }
        } );
    }




}
