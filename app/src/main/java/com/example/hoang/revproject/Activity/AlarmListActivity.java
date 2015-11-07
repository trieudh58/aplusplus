package com.example.hoang.revproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.hoang.revproject.Helper.OnStartDragListener;
import com.example.hoang.revproject.Helper.SimpleItemTouchHelperCallback;
import com.example.hoang.revproject.Model.AlarmDBHelper;
import com.example.hoang.revproject.Model.AlarmModel;
import com.example.hoang.revproject.R;
import com.example.hoang.revproject.Adapter.RVAlarmAdapter;

import java.util.ArrayList;
import java.util.List;

public class AlarmListActivity extends AppCompatActivity implements View.OnClickListener, OnStartDragListener{

    private FloatingActionButton fab;
    private ItemTouchHelper mItemTouchHelper;
    RecyclerView recyclerView;
    RVAlarmAdapter alarmAdapter;
    List<AlarmModel> list;
    public static final int CREATE_ALARM = 1;
    public static final int EDIT_ALARM = 2;
    AlarmDBHelper database;
    ListView listView;
    int itemIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getControls();
    }

    public void getControls(){
        database = new AlarmDBHelper(this);
        fab = (FloatingActionButton) findViewById(R.id.fab);
//        listView = (ListView) findViewById(R.id.listView);
//        listView.setAdapter(alarmAdapter);
        list = new ArrayList<AlarmModel>();
        list = database.getListAlarms();
        alarmAdapter = new RVAlarmAdapter(this, list, this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(alarmAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(alarmAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent newAlarm = new Intent(AlarmListActivity.this, AlarmDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ID", -1);
        newAlarm.putExtra("DATA", bundle);
        startActivityForResult(newAlarm, CREATE_ALARM);
    }

    public void updateRecyclerView(){
        list.clear();
        List<AlarmModel> newList = database.getListAlarms();
        list.addAll(newList);
        alarmAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            switch (requestCode) {
                case CREATE_ALARM:
                    updateRecyclerView();
                    break;
                case EDIT_ALARM:
                    updateRecyclerView();
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRecyclerView();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {

    }
}
