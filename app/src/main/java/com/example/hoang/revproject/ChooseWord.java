package com.example.hoang.revproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ChooseWord extends AppCompatActivity {

    private ListView lvAlarmWord;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arr;
    public static final int PICK_WORD = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_word);
        lvAlarmWord = (ListView) findViewById(R.id.lvAlarmWord);
        arr = new ArrayList<String>();
        arr.add("Family");
        arr.add("Friends");
        arr.add("Love");
        arr.add("People");
        arr.add("Temple");
        adapter = new ArrayAdapter<String>(ChooseWord.this, android.R.layout.simple_list_item_1, arr);
        lvAlarmWord.setAdapter(adapter);

        lvAlarmWord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putString("Word", arr.get(position));
                intent.putExtra("DATA", bundle);
                setResult(PICK_WORD, intent);
                finish();
            }
        });
    }
}
