package com.example.hoang.revproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlarmDetailActivity extends AppCompatActivity {

    public static final int CHOOSE_WORD = 1;
    private EditText word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnChoose = (Button) findViewById(R.id.btnChooseWord);
        word = (EditText) findViewById(R.id.alarmWord);
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickAWord = new Intent(AlarmDetailActivity.this, ChooseWord.class);
                startActivityForResult(pickAWord, CHOOSE_WORD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 2){
            if (requestCode == CHOOSE_WORD){
                Bundle bundle = data.getBundleExtra("DATA");
                word.setText(bundle.getString("Word"));
            }
        }
    }
}
