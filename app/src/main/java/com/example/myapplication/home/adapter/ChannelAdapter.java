package com.example.myapplication.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.home.bean.ResultBeanData;

import java.util.List;

import utils.Constants;

public class ChannelAdapter extends BaseAdapter {

    private final Context mContext;
    private final List datas;

    public ChannelAdapter(Context mContext, List datas) {
        this.mContext = mContext;
        this.datas = datas;
    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHoider viewHoider;

        if (convertView == null){
            convertView = View.inflate(mContext, R.layout.item_channel,null);
            viewHoider = new ViewHoider();
            viewHoider.iv_icon = convertView.findViewById(R.id.iv_channel);
            viewHoider.tv_title = convertView.findViewById(R.id.tv_channel);
            convertView.setTag(viewHoider);

        }else
        {
            viewHoider = (ViewHoider) convertView.getTag();
        }

        //根据位置得到相应的数据
//        ResultBeanData.ResultBean.ContentBean contentBean = (ResultBeanData.ResultBean.ContentBean) datas.get(position);
//        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+contentBean.getPic_small()).into(viewHoider.iv_icon);
//        viewHoider.tv_title.setText(contentBean.getTitle());

        ResultBeanData.ResultBean resultBean = (ResultBeanData.ResultBean) datas.get(position);
        viewHoider.tv_title.setText(resultBean.getName());
        Glide.with(mContext).load(resultBean.getPic_s192()).into(viewHoider.iv_icon);

        return convertView;
    }

    static class ViewHoider {
        ImageView iv_icon;
        TextView tv_title;

    }
}
