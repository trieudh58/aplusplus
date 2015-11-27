package com.example.hoang.revproject.Activity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hoang.revproject.Model.AlarmDBHelper;
import com.example.hoang.revproject.Model.AlarmManagerHelper;
import com.example.hoang.revproject.Model.AlarmModel;
import com.example.hoang.revproject.R;

public class AlarmDetailActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int CHOOSE_WORD = 1;
    public static final int PICK_RINGTONE = 2;
    public static int mondayCount = 0;
    public static int tuesdayCount = 0;
    public static int wednesdayCount = 0;
    public static int thursdayCount = 0;
    public static int fridayCount = 0;
    public static int saturdayCount = 0;
    public static int sundayCount = 0;
    private EditText word;
    int id = -1;
    Button Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;
    Button btnChoose;
    FloatingActionButton fbt;
    private TimePicker timePicker;
    private SeekBar seekBar = null;
    private AudioManager audioManager = null;
    private MediaPlayer mediaPlayer;
    TextView alarmToneSelection;
    LinearLayout alarmRingToneContainer;
    AlarmModel alarmDetails;
    AlarmDBHelper alarmDBHelper = new AlarmDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getControls();
    }

    public void getControls(){
        word = (EditText) findViewById(R.id.alarmWord);
        btnChoose = (Button) findViewById(R.id.btnChooseWord);
        Monday = (Button) findViewById(R.id.Monday);
        Tuesday = (Button) findViewById(R.id.Tuesday);
        Wednesday = (Button) findViewById(R.id.Wednesday);
        Thursday = (Button) findViewById(R.id.Thursday);
        Friday = (Button) findViewById(R.id.Friday);
        Saturday = (Button) findViewById(R.id.Saturday);
        Sunday = (Button) findViewById(R.id.Sunday);
        fbt = (FloatingActionButton) findViewById(R.id.fab);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        seekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM));
        seekBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_ALARM));
        setVolumeControlStream(AudioManager.STREAM_ALARM);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                alarmDetails.volume = progress;
                audioManager.setStreamVolume(AudioManager.STREAM_ALARM, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        alarmRingToneContainer = (LinearLayout) findViewById(R.id.alarm_ringtone_container);
        timePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmToneSelection = (TextView) findViewById(R.id.alarm_label_tone_selection);

        btnChoose.setOnClickListener(this);
        Monday.setOnClickListener(this);
        Tuesday.setOnClickListener(this);
        Wednesday.setOnClickListener(this);
        Thursday.setOnClickListener(this);
        Friday.setOnClickListener(this);
        Saturday.setOnClickListener(this);
        Sunday.setOnClickListener(this);
        fbt.setOnClickListener(this);
        alarmRingToneContainer.setOnClickListener(this);

        Bundle bundle = getIntent().getBundleExtra("DATA");
        id = bundle.getInt("ID");
        if (id == -1){
            alarmDetails = new AlarmModel();
        }else {
            alarmDetails = alarmDBHelper.getAlarm(id);

            timePicker.setCurrentMinute(alarmDetails.timeMinute);
            timePicker.setCurrentHour(alarmDetails.timeHour);

            seekBar.setProgress(alarmDetails.volume);

            word.setText(alarmDetails.Vocabulary);
            alarmToneSelection.setText(RingtoneManager.getRingtone(this, alarmDetails.alarmTone).getTitle(this));
            for (int i = 0; i < 7; i++){
                if (alarmDetails.getRepeatingDay(i) == true){
                    switch (i){
                        case 0:
                            Sunday.setSelected(true);
                            Sunday.setPressed(true);
                            sundayCount = 1;
                            break;
                        case 1:
                            Monday.setSelected(true);
                            Monday.setPressed(true);
                            mondayCount = 1;
                            break;
                        case 2:
                            Tuesday.setSelected(true);
                            Tuesday.setPressed(true);
                            tuesdayCount = 1;
                            break;
                        case 3:
                            Wednesday.setSelected(true);
                            Wednesday.setPressed(true);
                            wednesdayCount = 1;
                            break;
                        case 4:
                            Thursday.setSelected(true);
                            Thursday.setPressed(true);
                            thursdayCount = 1;
                            break;
                        case 5:
                            Friday.setSelected(true);
                            Friday.setPressed(true);
                            fridayCount = 1;
                            break;
                        case 6:
                            Saturday.setSelected(true);
                            Saturday.setPressed(true);
                            saturdayCount = 1;
                            break;
                    }
                }
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2){
            if (requestCode == CHOOSE_WORD){
                Bundle bundle = data.getBundleExtra("DATA");
                word.setText(bundle.getString("Word"));
                alarmDetails.image = bundle.getString("Image");
            }
        }
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PICK_RINGTONE: {
                    alarmDetails.alarmTone = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
                    alarmToneSelection.setText(RingtoneManager.getRingtone(this, alarmDetails.alarmTone).getTitle(this));
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnChooseWord:
                Intent pickAWord = new Intent(AlarmDetailActivity.this, ChooseWord.class);
                startActivityForResult(pickAWord, CHOOSE_WORD);
                break;
            case R.id.Monday:
                mondayCount++;
                if (mondayCount % 2 == 1){
                    alarmDetails.setRepeatingDay(AlarmModel.MONDAY, true);
                    Monday.setSelected(true);
                    Monday.setPressed(true);
                    Toast.makeText(AlarmDetailActivity.this, String.valueOf(alarmDetails.getRepeatingDay(AlarmModel.MONDAY)), Toast.LENGTH_SHORT).show();
                }else {
                    alarmDetails.setRepeatingDay(AlarmModel.MONDAY, false);
                    Toast.makeText(AlarmDetailActivity.this, String.valueOf(alarmDetails.getRepeatingDay(AlarmModel.MONDAY)), Toast.LENGTH_SHORT).show();
                    Monday.setSelected(false);
                    Monday.setPressed(false);
                }
                break;
            case R.id.Tuesday:
                tuesdayCount++;
                if (tuesdayCount % 2 == 1){
                    alarmDetails.setRepeatingDay(AlarmModel.TUESDAY, true);
                    Tuesday.setSelected(true);
                    Tuesday.setPressed(true);
                    Toast.makeText(AlarmDetailActivity.this, String.valueOf(alarmDetails.getRepeatingDay(AlarmModel.TUESDAY)), Toast.LENGTH_SHORT).show();
                }else {
                    alarmDetails.setRepeatingDay(AlarmModel.TUESDAY, false);
                    Toast.makeText(AlarmDetailActivity.this, String.valueOf(alarmDetails.getRepeatingDay(AlarmModel.TUESDAY)), Toast.LENGTH_SHORT).show();
                    Tuesday.setSelected(false);
                    Tuesday.setPressed(false);
                }
                break;
            case R.id.Wednesday:
                wednesdayCount++;
                if (wednesdayCount % 2 == 1){
                    alarmDetails.setRepeatingDay(AlarmModel.WEDNESDAY, true);
                    Wednesday.setSelected(true);
                    Wednesday.setPressed(true);
                    Toast.makeText(AlarmDetailActivity.this, String.valueOf(alarmDetails.getRepeatingDay(AlarmModel.WEDNESDAY)), Toast.LENGTH_SHORT).show();
                }else {
                    alarmDetails.setRepeatingDay(AlarmModel.WEDNESDAY, false);
                    Toast.makeText(AlarmDetailActivity.this, String.valueOf(alarmDetails.getRepeatingDay(AlarmModel.WEDNESDAY)), Toast.LENGTH_SHORT).show();
                    Wednesday.setSelected(false);
                    Wednesday.setPressed(false);
                }
                break;
            case R.id.Thursday:
                thursdayCount++;
                if (thursdayCount % 2 == 1){
                    alarmDetails.setRepeatingDay(AlarmModel.THUSDAY, true);
                    Thursday.setSelected(true);
                    Thursday.setPressed(true);
                    Toast.makeText(AlarmDetailActivity.this, String.valueOf(alarmDetails.getRepeatingDay(AlarmModel.THUSDAY)), Toast.LENGTH_SHORT).show();
                }else {
                    alarmDetails.setRepeatingDay(AlarmModel.THUSDAY, false);
                    Toast.makeText(AlarmDetailActivity.this, String.valueOf(alarmDetails.getRepeatingDay(AlarmModel.THUSDAY)), Toast.LENGTH_SHORT).show();
                    Thursday.setSelected(false);
                    Thursday.setPressed(false);
                }
                break;
            case R.id.Friday:
                fridayCount++;
                if (fridayCount % 2 == 1){
                    alarmDetails.setRepeatingDay(AlarmModel.FRIDAY, true);
                    Friday.setSelected(true);
                    Friday.setPressed(true);
                    Toast.makeText(AlarmDetailActivity.this, String.valueOf(alarmDetails.getRepeatingDay(AlarmModel.FRIDAY)), Toast.LENGTH_SHORT).show();
                }else {
                    alarmDetails.setRepeatingDay(AlarmModel.FRIDAY, false);
                    Toast.makeText(AlarmDetailActivity.this, String.valueOf(alarmDetails.getRepeatingDay(AlarmModel.FRIDAY)), Toast.LENGTH_SHORT).show();
                    Friday.setSelected(false);
                    Friday.setPressed(false);
                }
                break;
            case R.id.Saturday:
                saturdayCount++;
                if (saturdayCount % 2 == 1){
                    alarmDetails.setRepeatingDay(AlarmModel.SATURDAY, true);
                    Saturday.setSelected(true);
                    Saturday.setPressed(true);
                    Toast.makeText(AlarmDetailActivity.this, String.valueOf(alarmDetails.getRepeatingDay(AlarmModel.SATURDAY)), Toast.LENGTH_SHORT).show();
                }else {
                    alarmDetails.setRepeatingDay(AlarmModel.SATURDAY, false);
                    Toast.makeText(AlarmDetailActivity.this, String.valueOf(alarmDetails.getRepeatingDay(AlarmModel.SATURDAY)), Toast.LENGTH_SHORT).show();
                    Saturday.setSelected(false);
                    Saturday.setPressed(false);
                }
                break;
            case R.id.Sunday:
                sundayCount++;
                if (sundayCount % 2 == 1){
                    alarmDetails.setRepeatingDay(AlarmModel.SUNDAY, true);
                    Sunday.setSelected(true);
                    Sunday.setPressed(true);
                    Toast.makeText(AlarmDetailActivity.this, String.valueOf(alarmDetails.getRepeatingDay(AlarmModel.SUNDAY)), Toast.LENGTH_SHORT).show();
                }else {
                    alarmDetails.setRepeatingDay(AlarmModel.SUNDAY, false);
                    Toast.makeText(AlarmDetailActivity.this, String.valueOf(alarmDetails.getRepeatingDay(AlarmModel.SUNDAY)), Toast.LENGTH_SHORT).show();
                    Sunday.setSelected(false);
                    Sunday.setPressed(false);
                }
                break;
            case R.id.fab:
                saveAlarm();

                if (id < 0) {
                    alarmDBHelper.createAlarm(alarmDetails);
                } else {
                    alarmDBHelper.updateAlarm(alarmDetails);
                }

                AlarmManagerHelper.setAlarm(this);
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.alarm_ringtone_container:
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                startActivityForResult(intent, PICK_RINGTONE);
                break;
        }
    }

    public void saveAlarm(){
        alarmDetails.timeMinute = timePicker.getCurrentMinute().intValue();
        alarmDetails.timeHour = timePicker.getCurrentHour().intValue();
        alarmDetails.Vocabulary = word.getText().toString();
        alarmDetails.isEnabled = true;
    }
}
