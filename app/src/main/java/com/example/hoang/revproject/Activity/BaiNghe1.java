package com.example.hoang.revproject.Activity;

/**
 * Created by An on 08/11/2015.
 */

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.hoang.revproject.R;

import java.util.concurrent.TimeUnit;

public class BaiNghe1 extends AppCompatActivity {

    ImageView btn_stop ,btn_prev, btn_play, btn_next, btn_reapeat, btn_like, img_topic;
    TextView txt_start , txt_end, txt_topic, txt_tran;
    ToggleButton btn_show;
    SeekBar seekBar;
    MediaPlayer song;
    double Time_start=0 , Time_end=0;
    private Handler Myhandler = new Handler();
    boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_nghe1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_stop = (ImageView) findViewById(R.id.btn_stop);
        btn_prev = (ImageView) findViewById(R.id.btn_prev);
        btn_play = (ImageView) findViewById(R.id.btn_play);
        btn_next = (ImageView) findViewById(R.id.btn_next);
        btn_reapeat = (ImageView) findViewById(R.id.btn_reapeat);
        btn_like = (ImageView) findViewById(R.id.btn_like);
        btn_show = (ToggleButton) findViewById(R.id.btn_show);
        img_topic = (ImageView) findViewById(R.id.Image_Topic);
        txt_tran = (TextView) findViewById(R.id.Txt_Transcrip);

        txt_start = (TextView) findViewById(R.id.TxTStart);
        txt_end = (TextView) findViewById(R.id.TxtEnd);
        txt_topic = (TextView) findViewById(R.id.Txt_Topic);
        seekBar = (SeekBar) findViewById(R.id.SeekBar);
        song = MediaPlayer.create(this, R.raw.bleedinglove);
        Myhandler = new Handler();

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Time_end = song.getDuration();
                seekBar.setMax((int) Time_end);
                long PhutKetThuc = TimeUnit.MILLISECONDS.toMinutes((long) Time_end);
                long GiayKetThuc = TimeUnit.MILLISECONDS.toSeconds((long) Time_end) - PhutKetThuc * 60;
                if (GiayKetThuc < 10) {
                    txt_end.setText(String.format("%d:0%d", PhutKetThuc, GiayKetThuc));
                } else {
                    txt_end.setText(String.format("%d:%d", PhutKetThuc, GiayKetThuc));
                }
                if(song.isPlaying()==false) {
                    song.start();
                    btn_play.setImageResource(R.drawable.btn_pause);
                    Myhandler.postDelayed(Capnhat, 100);
                }
                else{
                    song.pause();
                    btn_play.setImageResource(R.drawable.btn_play);
                }
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song.seekTo(0);
                song.pause();
                btn_play.setImageResource(R.drawable.btn_play);
            }
        });

        btn_reapeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song.seekTo(0);
                song.start();

            }
        });

        btn_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    txt_tran.setText("Đây là lyric của bài hát nè. Cảm ơn đảng, cảm ơn nhà nước, cảm ơn money để em có thể mua thanh RAM4GB cho hiệu năng cải thiện, e cũng cảm ơn phần mềm Android Studio đã phát triển. Đây là lyric của bài hát nè. Cảm ơn đảng, cảm ơn nhà nước, cảm ơn money để em có thể mua thanh RAM4GB cho hiệu năng cải thiện, e cũng cảm ơn phần mềm Android Studio đã phát triển. Đây là lyric của bài hát nè. Cảm ơn đảng, cảm ơn nhà nước, cảm ơn money để em có thể mua thanh RAM4GB cho hiệu năng cải thiện, e cũng cảm ơn phần mềm Android Studio đã phát triển. Đây là lyric của bài hát nè. Cảm ơn đảng, cảm ơn nhà nước, cảm ơn money để em có thể mua thanh RAM4GB cho hiệu năng cải thiện, e cũng cảm ơn phần mềm Android Studio đã phát triển. Đây là lyric của bài hát nè. Cảm ơn đảng, cảm ơn nhà nước, cảm ơn money để em có thể mua thanh RAM4GB cho hiệu năng cải thiện, e cũng cảm ơn phần mềm Android Studio đã phát triển.");
                    //  txt_tran.setTextAlignment();
                    txt_tran.setVisibility(View.VISIBLE);
                    img_topic.setVisibility(View.INVISIBLE);
                }
                else {
                    txt_tran.setVisibility(View.INVISIBLE);
                    img_topic.setVisibility(View.VISIBLE);
                }

            }
        });

        //xử lí tua bài hát
        btn_next.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    btn_next.setImageResource(R.drawable.next_focused);
                    check = true;
                    song.seekTo((int) song.getCurrentPosition() + 3000);
                    Myhandler.postDelayed(Tua, 500);

                } else if (MotionEvent.ACTION_UP == event.getAction()) {
                    btn_next.setImageResource(R.drawable.next_deafult);
                    check = false;
                }
                return true;
            }
        });


        //xử lí tua lùi bài hát
        btn_prev.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    btn_prev.setImageResource(R.drawable.prev_focused);
                    check=true;
                    song.seekTo((int) song.getCurrentPosition() - 3000);
                    Myhandler.postDelayed(Lui, 500);
                } else if (MotionEvent.ACTION_UP == event.getAction()) {
                    btn_prev.setImageResource(R.drawable.prev_deafult);
                    check=false;
                }
                return true;
            }
        });
    }


    private Runnable Capnhat = new Runnable() {
        @Override
        public void run() {
            Time_start=song.getCurrentPosition();
            long PhutBatDau = TimeUnit.MILLISECONDS.toMinutes((long) Time_start);
            long GiayBatDau = TimeUnit.MILLISECONDS.toSeconds((long) Time_start) - PhutBatDau*60;
            if(GiayBatDau < 10){
                txt_start.setText(String.format("%d:0%d", PhutBatDau, GiayBatDau));
            }
            else {
                txt_start.setText(String.format("%d:%d", PhutBatDau, GiayBatDau));
            }
            seekBar.setProgress((int) Time_start);
            Myhandler.postDelayed(this, 100);
        }
    };

    private Runnable Tua = new Runnable() {
        @Override
        public void run() {
            if(check==true) {
                song.seekTo((int) song.getCurrentPosition() + 3000);
                Myhandler.postDelayed(this, 500);
            }
        }
    };

    private Runnable Lui = new Runnable() {
        @Override
        public void run() {
            if(check==true) {
                song.seekTo((int) song.getCurrentPosition() - 3000);
                Myhandler.postDelayed(this, 500);
            }
        }
    };

}


