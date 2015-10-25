package com.example.hoang.revproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by hoang on 10/24/2015.
 */
public class HomeAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<HomeItem> arr = new ArrayList<HomeItem>();

    public HomeAdapter(Context context, ArrayList<HomeItem> arr){
        this.mContext = context;
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);

            viewHolder.itemName = (TextView) convertView.findViewById(R.id.itemText);
            viewHolder.itemImage = (ImageView) convertView.findViewById(R.id.itemImage);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HomeItem item = arr.get(position);
        viewHolder.itemName.setText(item.getItemName());
        viewHolder.itemImage.setImageResource(item.getItemImage());

        return convertView;
    }

    private class ViewHolder{
        private TextView itemName;
        private ImageView itemImage;
    }
}
