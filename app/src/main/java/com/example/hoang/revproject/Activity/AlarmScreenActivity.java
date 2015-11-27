package com.example.hoang.revproject.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoang.revproject.R;

import java.io.IOException;
import java.util.Calendar;

public class AlarmScreenActivity extends AppCompatActivity {

    private EditText enterWord;
    private Button wakeUp;
    private TextView showTime, showDate;
    private String vocabulary = "", image;
    private ImageView imageView;
    private PowerManager.WakeLock mWakeLock;
    private MediaPlayer mediaPlayer;
    public final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_screen);
        getControls();
        addEvents();
    }

    public void getControls(){
        enterWord = (EditText) findViewById(R.id.enterWord);
        wakeUp = (Button) findViewById(R.id.alarm_screen_button);
        showTime = (TextView) findViewById(R.id.alarm_screen_time);
        showDate = (TextView) findViewById(R.id.alarm_screen_date);
        imageView = (ImageView) findViewById(R.id.alarm_screen_image);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("DATA");
        showTime.setText(String.format("%02d : %02d", bundle.getInt("timeHour"), bundle.getInt("timeMinute")));
        Calendar calendar = Calendar.getInstance();
        showDate.setText(String.format("%1$tA %1$tb %1$td %1$tY", calendar));
        vocabulary = bundle.getString("vocabulary");
        image = bundle.getString("image");
        String tone = bundle.getString("tone");
        int volume = bundle.getInt("volume");

        int imageResource = this.getResources().getIdentifier(image, null, this.getPackageName());
        Drawable res = this.getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res);

        mediaPlayer = new MediaPlayer();
        try {
            if (tone != null && !tone.equals("")){
                Uri toneUri = Uri.parse(tone);
                if (toneUri != null){
                    mediaPlayer.setDataSource(this, toneUri);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                    mediaPlayer.setVolume(volume, volume);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addEvents(){
        wakeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = enterWord.getText().toString();
                if (word.equalsIgnoreCase(vocabulary)){
                    mediaPlayer.stop();
                    Intent intent = new Intent(AlarmScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        if (mWakeLock == null){
            mWakeLock = pm.newWakeLock((PowerManager.FULL_WAKE_LOCK | PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), TAG);
        }
        if (!mWakeLock.isHeld()){
            mWakeLock.acquire();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mWakeLock != null && mWakeLock.isHeld()) {
            mWakeLock.release();
        }
    }
}
