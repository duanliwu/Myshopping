package com.example.myapplication.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.myapplication.Base.BaseFragment;
import com.example.myapplication.R;
import com.example.myapplication.User.UserFragment;
import com.example.myapplication.fragment.CommunityFragment;
import com.example.myapplication.home.fragment.HomeFragment;
import com.example.myapplication.shoppingcart.ShoppingCartFragment;
import com.example.myapplication.type.TypeFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_type)
    RadioButton rbType;
    @BindView(R.id.rb_community)
    RadioButton rbCommunity;
    @BindView(R.id.rb_cart)
    RadioButton rbCart;
    @BindView(R.id.rb_user)
    RadioButton rbUser;

    private int position = 0;
    /**
     * 装多个Fragment的实例集合
     */
    private ArrayList<BaseFragment> fragments;

    /**
     * 缓存的Fragemnt或者上次显示的Fragment
     */
    private Fragment Fragemnt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ButterKnife.bind( this );



        /**
         * 初始化Fragment
         */
        initFragment();
        //设置RadioGroup的监听
        initListener();

    }


    private void initListener() {

        rgMain.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home://主页
                        position = 0;
                        break;
                    case R.id.rb_type:///分类
                        position = 1;
                        break;
                    case R.id.rb_community://发现
                        position = 2;
                        break;
                    case R.id.rb_cart://购物车
                        position = 3;
                        break;
                    case R.id.rb_user:////用户中心
                        position = 4;
                        break;
                    default:
                        position = 0;
                        break;
                }

                //根据位置不同取Fragment
                BaseFragment baseFragment = getFragment( position );
                /**
                 * 第一参数：上次显示的Fragment
                 * 第二参数：当前正要显示的Fragment
                 */
                switchFragment( Fragemnt, baseFragment );
            }


        } );

        rgMain.check( R.id.rb_home );

    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add( new HomeFragment() );
        fragments.add( new TypeFragment() );
        fragments.add( new CommunityFragment() );
        fragments.add( new ShoppingCartFragment() );
        fragments.add( new UserFragment() );
    }

    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get( position );
            return baseFragment;
        }
        return null;
    }

    /**
     * 切换Fragment
     */


    private void switchFragment(Fragment fragemnt, BaseFragment baseFragment) {

        if (Fragemnt != baseFragment) {
            Fragemnt = baseFragment;
            if (baseFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!baseFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fragemnt != null) {
                        transaction.hide(fragemnt);
                    }
                    //添加Fragment
                    transaction.add(R.id.frameLayout, baseFragment).commit();
                } else {
                    //隐藏当前Fragment
                    if (fragemnt != null) {
                        transaction.hide(fragemnt);
                    }
                    transaction.show(baseFragment).commit();
                }
            }
        }
    }
}
