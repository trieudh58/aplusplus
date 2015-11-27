package com.example.hoang.revproject.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hoang.revproject.Adapter.ListAlarmAdapter;
import com.example.hoang.revproject.Adapter.RVNotificationAdapter;
import com.example.hoang.revproject.Model.AlarmDBHelper;
import com.example.hoang.revproject.Model.VocabularyModel;
import com.example.hoang.revproject.R;

import java.util.ArrayList;
import java.util.List;

public class ChooseWord extends AppCompatActivity {

    private ListView listView;
    private ListAlarmAdapter adapter;
    private List<VocabularyModel> arr;
    public static final int PICK_WORD = 2;
    private AlarmDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_word);
        listView = (ListView) findViewById(R.id.lvAlarmWord);
        dbHelper = new AlarmDBHelper(this);
        arr = dbHelper.getListVocabs();
        adapter = new ListAlarmAdapter(this, arr);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putString("Word", arr.get(position).getWord());
                bundle.putString("Image", arr.get(position).getImagePath());
                intent.putExtra("DATA", bundle);
                setResult(PICK_WORD, intent);
                finish();
            }
        });
    }
}
