package com.example.hoang.revproject.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.hoang.revproject.Model.AlarmDBHelper;
import com.example.hoang.revproject.Model.VocabularyModel;
import com.example.hoang.revproject.R;

import java.io.IOException;
import java.util.List;

public class ShowWordActivity extends AppCompatActivity {

    private TextView wordName, wordPronoun, wordDescrip;
    private ImageView wordImage;
    private ToggleButton wordSound;
    private MediaPlayer mediaPlayer;
    private AlarmDBHelper dbHelper;
    private List<VocabularyModel> arrVocab;
    private VocabularyModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_word);
        getControls();
        addEvents();
    }

    public void getControls(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbHelper = new AlarmDBHelper(this);
        arrVocab = dbHelper.getListVocabs();
        wordName = (TextView) findViewById(R.id.wordName);
        wordPronoun = (TextView) findViewById(R.id.wordPronoun);
        wordDescrip = (TextView) findViewById(R.id.wordDescrip);
        wordImage = (ImageView) findViewById(R.id.wordImage);
        wordSound = (ToggleButton) findViewById(R.id.wordSound);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("DATA");
        model = (VocabularyModel) bundle.getSerializable("MODEL");
        wordName.setText(model.getWord());
        wordPronoun.setText(model.getMeanOfWord());
        wordDescrip.setText(model.getDescription());
        int imageResource = this.getResources().getIdentifier(model.getImagePath(), null, this.getPackageName());
        Drawable res = this.getResources().getDrawable(imageResource);
        wordImage.setImageDrawable(res);
    }

    public void addEvents(){
        wordSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String soundName = model.getSoundPath().toLowerCase();
                    int resID = getResources().getIdentifier(soundName, "raw", getPackageName());
                    mediaPlayer = MediaPlayer.create(ShowWordActivity.this, resID);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            wordSound.setChecked(false);
                        }
                    });
                    int MAX_VOLUME = 100;
                    final float volume = (float) (1 - (Math.log(MAX_VOLUME - MAX_VOLUME) / Math.log(MAX_VOLUME)));
                    mediaPlayer.setVolume(volume,volume);
                    mediaPlayer.start();
                }
            }
        });

    }
}
