package com.example.hoang.revproject.Activity;

/**
 * Created by An on 08/11/2015.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.hoang.revproject.Adapter.ListeningAdapter;
import com.example.hoang.revproject.Model.AlarmDBHelper;
import com.example.hoang.revproject.Model.ListeningModel;
import com.example.hoang.revproject.R;

import java.util.List;

public class  ListListener extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListeningAdapter adapter;
    private List<ListeningModel> list;
    private AlarmDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_listener);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getControls();
    }

    public void getControls(){
        dbHelper = new AlarmDBHelper(this);

        list = dbHelper.getListListening();
        recyclerView = (RecyclerView) findViewById(R.id.list_listen);
        adapter = new ListeningAdapter(this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void onBackPressed() {
        Intent intent = new Intent(ListListener.this, MainActivity.class);
        startActivity(intent);
    }

}
