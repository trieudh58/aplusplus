package com.example.hoang.revproject.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hoang.revproject.Model.AlarmDBHelper;
import com.example.hoang.revproject.Model.AlarmModel;
import com.example.hoang.revproject.Model.MyVocabularyModel;
import com.example.hoang.revproject.R;

import org.w3c.dom.Text;

public class AddMyVocabActivity extends AppCompatActivity {

    EditText editword, editmean, editpronoun;
    Button btnsave, btncancel;
    AlarmDBHelper dbHelper;
    int id;
    MyVocabularyModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_my_vocab);
        dbHelper = new AlarmDBHelper(this);

        editword = (EditText) findViewById(R.id.editWord);
        editmean = (EditText) findViewById(R.id.editMean);
        editpronoun = (EditText) findViewById(R.id.editPronoun);

        btnsave = (Button) findViewById(R.id.btnSave);
        btncancel = (Button) findViewById(R.id.btnCancel);

        Bundle bundle = getIntent().getBundleExtra("DATA");
        id = bundle.getInt("ID");
        if (id == -1){
            model  = new MyVocabularyModel();
        }else {
            model = dbHelper.getMyVocab(id);
            editword.setText(model.getWord());
            editmean.setText(model.getMean());
            editpronoun.setText(model.getPronoun());
        }

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = editword.getText().toString();
                String mean = editmean.getText().toString();
                String pronoun = editpronoun.getText().toString();

                MyVocabularyModel model = new MyVocabularyModel();
                model.setWord(word);
                model.setMean(mean);
                model.setPronoun(pronoun);

                if (id < 0){
                    dbHelper.createMyVocab(model);
                }else {
                    dbHelper.updateMyVocab(model);
                }
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
