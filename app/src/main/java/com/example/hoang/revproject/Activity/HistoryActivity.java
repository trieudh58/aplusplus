package com.example.hoang.revproject.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.hoang.revproject.Adapter.RVNotificationAdapter;
import com.example.hoang.revproject.Model.AlarmDBHelper;
import com.example.hoang.revproject.Model.VocabularyModel;
import com.example.hoang.revproject.R;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private AlarmDBHelper dbHelper;
    private RVNotificationAdapter adapter;
    private List<VocabularyModel> list;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getControls();

    }

    public void getControls(){
        dbHelper = new AlarmDBHelper(this);
        list = dbHelper.getListVocabDone();
        adapter = new RVNotificationAdapter(this, list);
        recyclerView = (RecyclerView) findViewById(R.id.historylist);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

}
