package com.example.hoang.revproject.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.hoang.revproject.Activity.AlarmDetailActivity;
import com.example.hoang.revproject.Helper.ItemTouchHelperAdapter;
import com.example.hoang.revproject.Helper.OnStartDragListener;
import com.example.hoang.revproject.Model.AlarmDBHelper;
import com.example.hoang.revproject.Model.AlarmManagerHelper;
import com.example.hoang.revproject.Model.AlarmModel;
import com.example.hoang.revproject.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hoang on 10/25/2015.
 */
public class RVAlarmAdapter extends RecyclerView.Adapter<RVAlarmAdapter.RecyclerViewHolder> implements ItemTouchHelperAdapter{

    private Context mContext;
    private List<AlarmModel> list;
    private AlarmModel model;
    private AlarmDBHelper dbHelper;
    private final OnStartDragListener onStartDragListener;

    public RVAlarmAdapter(Context context, List<AlarmModel> list, OnStartDragListener onStartDragListener){
        this.mContext = context;
        this.list = list;
        this.onStartDragListener = onStartDragListener;
        dbHelper = new AlarmDBHelper(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_alarm, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        model = list.get(position);
        holder.alarmTime.setText(String.format("%02d : %02d", model.timeHour, model.timeMinute));
        holder.alarmToggle.setChecked(model.isEnabled);
        for (int i = 0; i < 7; i++){
            if (model.getRepeatingDay(i) == true){
                switch (i){
                    case 0:
                        holder.su.setTextColor(Color.BLUE);
                        break;
                    case 1:
                        holder.mo.setTextColor(Color.BLUE);
                        break;
                    case 2: holder.tu.setTextColor(Color.BLUE);
                        break;
                    case 3: holder.we.setTextColor(Color.BLUE);
                        break;
                    case 4: holder.th.setTextColor(Color.BLUE);
                        break;
                    case 5:
                        holder.fr.setTextColor(Color.BLUE);
                        break;
                    case 6:
                        holder.sa.setTextColor(Color.BLUE);
                        break;
                }
            }else {
                switch (i){
                    case 0:
                        holder.su.setTextColor(Color.BLACK);
                        break;
                    case 1:
                        holder.mo.setTextColor(Color.BLACK);
                        break;
                    case 2: holder.tu.setTextColor(Color.BLACK);
                        break;
                    case 3: holder.we.setTextColor(Color.BLACK);
                        break;
                    case 4: holder.th.setTextColor(Color.BLACK);
                        break;
                    case 5:
                        holder.fr.setTextColor(Color.BLACK);
                        break;
                    case 6:
                        holder.sa.setTextColor(Color.BLACK);
                        break;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(list, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("Do you want to remove this Alarm");
        builder.setTitle("Remove");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                List<AlarmModel> arr = new ArrayList<AlarmModel>();
                arr = dbHelper.getListAlarms();
                list.clear();
                list.addAll(arr);
                notifyDataSetChanged();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.deleteAlarm(list.get(position).id);
                list.remove(position);
                notifyItemRemoved(position);
            }
        });
        builder.show();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView container;
        private TextView alarmTime;
        private ToggleButton alarmToggle;
        private TextView mo, tu, we, th, fr, sa, su;

        public RecyclerViewHolder(View view){
            super(view);

            container = (CardView) view.findViewById(R.id.item_container);
            alarmTime = (TextView) view.findViewById(R.id.alarmTime);
            alarmToggle = (ToggleButton) view.findViewById(R.id.alarmToggle);
            mo = (TextView) view.findViewById(R.id.txtMo);
            tu = (TextView) view.findViewById(R.id.txtTu);
            we = (TextView) view.findViewById(R.id.txtWe);
            th = (TextView) view.findViewById(R.id.txtTh);
            fr = (TextView) view.findViewById(R.id.txtFi);
            sa = (TextView) view.findViewById(R.id.txtSa);
            su = (TextView) view.findViewById(R.id.txtSu);

            container.setOnClickListener(this);
            alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        AlarmModel alarmModel = dbHelper.getAlarm(list.get(getAdapterPosition()).id);
                        alarmModel.isEnabled = true;
                        dbHelper.updateAlarm(alarmModel);
                        AlarmManagerHelper.setAlarm(mContext);
                    }else {
                        AlarmModel alarmModel = dbHelper.getAlarm(list.get(getAdapterPosition()).id);
                        alarmModel.isEnabled = false;
                        dbHelper.updateAlarm(alarmModel);
                        AlarmManagerHelper.setAlarm(mContext);
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            AlarmModel alarmModel = list.get(getAdapterPosition());
            Intent intent = new Intent(mContext, AlarmDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("ID", alarmModel.id);
            intent.putExtra("DATA", bundle);
            mContext.startActivity(intent);
        }
    }
}
