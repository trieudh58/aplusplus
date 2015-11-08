package com.example.hoang.revproject.Activity;

/**
 * Created by An on 08/11/2015.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hoang.revproject.R;

import java.util.ArrayList;

public class ListListener extends AppCompatActivity {

    ListView listListen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_listener);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listListen = (ListView) findViewById(R.id.list_listen);

        ArrayList<String> mangBaiNghe = new ArrayList<String>();
        mangBaiNghe.add("Bài 1: Family");
        mangBaiNghe.add("Bài 2: Brother Zone");
        mangBaiNghe.add("Bài 3: Super Man Ichi");
        mangBaiNghe.add("Bài 4: Environment");
        mangBaiNghe.add("Bài 5: Weather");
        mangBaiNghe.add("Bài 6: Music");
        mangBaiNghe.add("Bài 7: Personality");

        ArrayAdapter adapter = new ArrayAdapter( this, android.R.layout.simple_list_item_1, mangBaiNghe);
        listListen.setAdapter(adapter);

        listListen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    Intent bai1 = new Intent(ListListener.this, BaiNghe1.class);
                    startActivity(bai1);
                }
            }
        });
    }

}
