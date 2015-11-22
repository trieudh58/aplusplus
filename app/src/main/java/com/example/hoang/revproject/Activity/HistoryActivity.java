package com.example.hoang.revproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.hoang.revproject.Adapter.HistoryAdapter;
import com.example.hoang.revproject.Model.AlarmDBHelper;
import com.example.hoang.revproject.Model.AlarmModel;
import com.example.hoang.revproject.Model.VocabularyModel;
import com.example.hoang.revproject.R;

import java.util.List;

public class HistoryActivity extends AppCompatActivity{

    private AlarmDBHelper dbHelper;
    private HistoryAdapter adapter;
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
        recyclerView = (RecyclerView) findViewById(R.id.historylist);
        adapter = new HistoryAdapter(this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        adapter.onActivityResult(requestCode, RESULT_OK, data);
        updateRecyclerView();
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void updateRecyclerView(){
        list.clear();
        List<VocabularyModel> newList = dbHelper.getListVocabDone();
        list.addAll(newList);
        adapter.notifyDataSetChanged();
    }

}

