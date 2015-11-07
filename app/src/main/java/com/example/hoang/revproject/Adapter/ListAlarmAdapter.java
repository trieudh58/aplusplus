package com.example.hoang.revproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.hoang.revproject.Activity.AlarmDetailActivity;
import com.example.hoang.revproject.Model.AlarmModel;
import com.example.hoang.revproject.R;

import java.util.List;

/**
 * Created by hoang on 10/26/2015.
 */
public class ListAlarmAdapter extends BaseAdapter implements View.OnClickListener{

    private Context mContext;
    private List<AlarmModel> list;
    private  AlarmModel model;

    public ListAlarmAdapter(Context context, List<AlarmModel> arr){
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_alarm, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.alarmTime = (TextView) convertView.findViewById(R.id.alarmTime);
            viewHolder.alarmToggle = (ToggleButton) convertView.findViewById(R.id.alarmToggle);
            viewHolder.container = (CardView) convertView.findViewById(R.id.item_container);
            viewHolder.container.setOnClickListener(this);
            viewHolder.alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        model.isEnabled = true;
                    } else {
                        model.isEnabled = false;
                    }
                }
            });
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        model = list.get(position);
        viewHolder.alarmTime.setText(model.timeHour + ":" + model.timeMinute);
        viewHolder.alarmToggle.setChecked(model.isEnabled);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mContext, AlarmDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ID", model.id);
        intent.putExtra("DATA", bundle);
        mContext.startActivity(intent);
    }

    public class ViewHolder{
        private TextView alarmTime;
        private ToggleButton alarmToggle;
        private CardView container;
    }
}
