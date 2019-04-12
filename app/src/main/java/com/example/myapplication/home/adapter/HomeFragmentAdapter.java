package com.example.myapplication.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.home.bean.ResultBeanData;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.List;

import utils.Constants;

import static android.content.ContentValues.TAG;

public class HomeFragmentAdapter extends RecyclerView.Adapter {

    /**
     * 广告条幅类型
     */
    public static final int BANNER = 0;    //初始化页面布局

    /**
     * 频道类型
     */
    public static final int CHANNEL = 1;
    /**
     * 活动类型
     */
    public static final int ACT = 2;
    /**
     * 秒杀类型
     */
    public static final int SECKILL = 3;

    /**
     * 数据
     */
    private List<ResultBeanData.ResultBean> resultBean;

    private LayoutInflater mLayoutInflater;
    private Context mContext;

    /**
     * 当前类型
     */
    private int currentType = BANNER;

    public HomeFragmentAdapter(Context mContext, List<ResultBeanData.ResultBean> resultBean) {
        this.mContext = mContext;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from( mContext );
    }

    /**
     * 相当于getView 创建ViewHolder部分代码
     * 创建ViewHolder
     *
     * @param viewGroup
     * @param i 当前的类型
     * @return
     */

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (i == BANNER){
            return new BannerViewHolder(mContext,mLayoutInflater.inflate( R.layout.banner_viewpager,null ));
        }else if (i == CHANNEL ){
            return new ChannelViewHolder(mContext,mLayoutInflater.inflate(R.layout.channel_item,null));
        }else if (i == ACT){
            return new ActViewHolder(mContext,mLayoutInflater.inflate(R.layout.act_item,null));
        }else if (i == SECKILL){
            return new SeckillViewHolder(mContext,mLayoutInflater.inflate(R.layout.seckill_item,null));
        }

        return null;
    }

    /**
     * 相当于getview中的绑定数据模块
     *
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if (getItemViewType( i ) == BANNER){
            BannerViewHolder bannerViewHolder = (BannerViewHolder) viewHolder;
            bannerViewHolder.setData(resultBean);
        }else if (getItemViewType(i) == CHANNEL){
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) viewHolder;
            channelViewHolder.setData(resultBean);
        }else if (getItemViewType(i) == ACT){
            ActViewHolder actViewHolder = (ActViewHolder) viewHolder;
            actViewHolder.setData(resultBean);
        }else if (getItemViewType(i) == SECKILL){
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) viewHolder;
            seckillViewHolder.setData(resultBean);
            
        }
    }

    @Override
    public int getItemViewType (int i){
        switch (i){
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
        }
        return currentType;
    }


    //设置RecyclerView
    @Override
    public int getItemCount() {
        return 3;
    }

    //上面轮播图片
    private class BannerViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private Banner banner;

        public BannerViewHolder(Context mContext, View inflate) {
            super(inflate);
            this.mContext = mContext;
            this.banner = (Banner) inflate.findViewById( R.id.banner );
        }

        public void setData(List<ResultBeanData.ResultBean> resultBean) {

//            设置Banner的数据
//            得到图片集合地址
            final List<String> imagesUrl = new ArrayList<>(  );
            for (int i = 0;i<resultBean.size();i++){
                String imageUrl = resultBean.get( i ).getPic_s444();
                imagesUrl.add( imageUrl );
            }

//            Log.e( TAG,"++++" +imagesUrl );
//            设置循环点
            banner.setBannerStyle( BannerConfig.CIRCLE_INDICATOR );
            banner.setBannerAnimation( Transformer.Accordion );
            banner.setImageLoader(new GlideImageLoader());
            banner.setImages( imagesUrl );
            banner.start();

            banner.setOnBannerListener( new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                }
            } );
        }

    }

    public class GlideImageLoader implements ImageLoaderInterface {
        @Override
        public void displayImage(Context context, Object path, View imageView) {

//            Log.e(TAG,"图片+++++++"+path);
            Glide.with(context).load(path).into( (ImageView) imageView );

        }

        @Override
        public View createImageView(Context context) {
            return null;
        }
    }


    class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private GridView gv_channel;
        private ChannelAdapter adapter;

        public ChannelViewHolder(final Context mContext, View inflate) {
            super(inflate);
            this.mContext =mContext;
            gv_channel = itemView.findViewById(R.id.gv_channel);

            //设置item 的点击事件
            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext,"position"+position,Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData(List<ResultBeanData.ResultBean> resultBean) {
            //得到数据了
            //设置GridView的适配器
            adapter = new ChannelAdapter(mContext, resultBean);
            gv_channel.setAdapter(adapter);
        }
    }

    private class ActViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private ViewPager act_viewpager;


        public ActViewHolder(Context mContext, View inflate) {
            super(inflate);
            this.mContext = mContext;
            act_viewpager = itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(final List<ResultBeanData.ResultBean> resultBean) {
            act_viewpager.setPageMargin(20);
            act_viewpager.setOffscreenPageLimit(3);
            act_viewpager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return resultBean.size();
                }

                @Override
                public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                    return view == o;
                }

                @NonNull
                @Override
                public Object instantiateItem(@NonNull ViewGroup container, final int position) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

//                    Log.e( TAG,"++++" +resultBean.get(position).getPic_s210() );

                    Glide.with(mContext).load(resultBean.get(position).getPic_s210()).into(imageView );

                    //添加到容器
                    container.addView(imageView);

                    //点击事件
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext,"position=="+position,Toast.LENGTH_SHORT).show();
                        }
                    });

                    return imageView;
                }

                @Override
                public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                    container.removeView((View) object);
                }
            });
        }
    }

    private class SeckillViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private TextView tv_time_seckill;
        private TextView tv_more_seckil;
        private RecyclerView rv_seckill;
        private SeckillRecyclerViewAdapter adapter;



        public SeckillViewHolder(Context mContext, View inflate) {
            super(inflate);
            tv_time_seckill = inflate.findViewById(R.id.tv_time_seckill);
            tv_more_seckil = inflate.findViewById(R.id.tv_more_seckill);
            this.mContext = mContext;
        }

        public void setData(List<ResultBeanData.ResultBean> resultBean) {
            //有数据设置适配器
//            adapter = new SeckillRecyclerViewAdapter(mContext,resultBean);
//            rv_seckill.setAdapter(adapter);


        }
    }
}
