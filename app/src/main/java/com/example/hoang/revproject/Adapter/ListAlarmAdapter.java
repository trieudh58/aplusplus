package com.example.hoang.revproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.hoang.revproject.Activity.AlarmDetailActivity;
import com.example.hoang.revproject.Model.AlarmModel;
import com.example.hoang.revproject.Model.VocabularyModel;
import com.example.hoang.revproject.R;

import java.util.List;

/**
 * Created by hoang on 10/26/2015.
 */
public class ListAlarmAdapter extends BaseAdapter{

    private Context mContext;
    private List<VocabularyModel> list;
    private VocabularyModel model;

    public ListAlarmAdapter(Context context, List<VocabularyModel> arr){
        this.mContext = context;
        this.list = arr;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.notification, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.word = (TextView) convertView.findViewById(R.id.title);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.imgNoti);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);
            viewHolder.container = (CardView) convertView.findViewById(R.id.item_container);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        model = list.get(position);
        viewHolder.word.setText(model.getWord());
        viewHolder.description.setText(model.getDescription());
        int imageResource = mContext.getResources().getIdentifier(model.getImagePath(), null, mContext.getPackageName());
        Drawable res = mContext.getResources().getDrawable(imageResource);
        viewHolder.image.setImageDrawable(res);

        return convertView;
    }

    public class ViewHolder{
        private TextView word, description;
        private ImageView image;
        private CardView container;
    }
}
