package com.example.hoang.revproject.Activity;

/**
 * Created by An on 08/11/2015.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.hoang.revproject.Model.AlarmDBHelper;
import com.example.hoang.revproject.Model.ListeningModel;
import com.example.hoang.revproject.Model.MyVocabularyModel;
import com.example.hoang.revproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class BaiNghe1 extends AppCompatActivity {

    ImageView btn_stop, btn_prev, btn_play, btn_next, img_topic;
    TextView txt_start, txt_end, txt_topic, txt_tran;
    ToggleButton btn_show, btn_like, btn_reapeat;
    SeekBar seekBar;
    MediaPlayer song;
    double Time_start = 0, Time_end = 0, current;
    private Handler Myhandler = new Handler();
    private AlarmDBHelper dbHelper;
    ActionMode.Callback actionModeCallback;
    ActionMode actionMode;
    String s;
    TextToSpeech tts;
    int start;
    int end;
    boolean check, isFavorite = false, isRepeat = false;
    CoordinatorLayout coordinatorLayout;
    ListeningModel model;
    private String prefname = "my_data";
    private static final String KEY_REPEAT = "keyRepeat";
    private static final String KEY_INDEX = "keyIndex";
    private static final String KEY_PLAY = "keyPlay";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_bai_nghe1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setContentView(R.layout.activity_bai_nghe1);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("DATA");
        model = (ListeningModel) bundle.getSerializable("MODEL");


//        mShaker = new ShakeListener(this);
//        mShaker.setOnShakeListener(new ShakeListener.OnShakeListener() {
//            public void onShake() {
//                List<ListeningModel> list = dbHelper.getListListening();
//                int id = model.getId() + 1;
//                if (id > list.size()) { id = 1;}
//                model = dbHelper.getListening(id);
//                Intent intent = new Intent(BaiNghe1.this, BaiNghe1.class);
//                Bundle bundleAnimation = null;
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                    bundleAnimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation1).toBundle();
//                    bundleAnimation.putSerializable("MODEL", model);
//                    intent.putExtra("DATA", bundleAnimation);
//                    song.pause();
//                    startActivity(intent);
//                }
//            }
//        });

        dbHelper = new AlarmDBHelper(this);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.snackbar);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.ENGLISH);
                }
            }
        });
        btn_stop = (ImageView) findViewById(R.id.btn_stop);
        btn_prev = (ImageView) findViewById(R.id.btn_prev);
        btn_play = (ImageView) findViewById(R.id.btn_play);
        btn_next = (ImageView) findViewById(R.id.btn_next);
        btn_reapeat = (ToggleButton) findViewById(R.id.btn_reapeat);
        btn_like = (ToggleButton) findViewById(R.id.btn_like);
        btn_show = (ToggleButton) findViewById(R.id.btn_show);
        img_topic = (ImageView) findViewById(R.id.Image_Topic);
        txt_tran = (TextView) findViewById(R.id.Txt_Transcrip);
        txt_topic = (TextView) findViewById(R.id.Txt_Topic);
        seekBar = (SeekBar) findViewById(R.id.SeekBar);
        txt_topic.setText(model.getTitle());
        String soundName = model.getAudio().toLowerCase();
        int resID = getResources().getIdentifier(soundName, "raw", getPackageName());
        song = MediaPlayer.create(this, resID);
        txt_start = (TextView) findViewById(R.id.TxTStart);
        txt_end = (TextView) findViewById(R.id.TxtEnd);
        txt_topic = (TextView) findViewById(R.id.Txt_Topic);

        int imageResource = this.getResources().getIdentifier(model.getImage(), null, this.getPackageName());
        final Drawable res = this.getResources().getDrawable(imageResource);
        img_topic.setImageDrawable(res);

        if(savedInstanceState != null){
            if (savedInstanceState.getBoolean(KEY_PLAY)){
                Time_end = song.getDuration();
                song.start();
                song.seekTo((int) savedInstanceState.getFloat(KEY_INDEX));

                long PhutKetThuc = TimeUnit.MILLISECONDS.toMinutes((long) Time_end);
                long GiayKetThuc = TimeUnit.MILLISECONDS.toSeconds((long) Time_end) - PhutKetThuc * 60;
                if (GiayKetThuc < 10) {
                    txt_end.setText(String.format("%d:0%d", PhutKetThuc, GiayKetThuc));
                } else {
                    txt_end.setText(String.format("%d:%d", PhutKetThuc, GiayKetThuc));
                }
                btn_reapeat.setChecked(savedInstanceState.getBoolean(KEY_REPEAT));
                seekBar.setProgress((int) savedInstanceState.getFloat(KEY_INDEX));
                btn_play.setImageResource(R.drawable.btn_pause);
                OnProgressChanged(seekBar);
                updateProgressBar();
                song.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if (savedInstanceState.getBoolean(KEY_REPEAT) == false) {
                            song.seekTo(0);
                            song.pause();
                            btn_play.setImageResource(R.drawable.btn_play);
                        } else {
                            song.seekTo(0);
                            song.start();
                        }
                    }
                });
            }else {
                btn_reapeat.setChecked(savedInstanceState.getBoolean(KEY_REPEAT));
                song.seekTo((int) savedInstanceState.getFloat(KEY_INDEX));

                long PhutKetThuc = TimeUnit.MILLISECONDS.toMinutes((long) Time_end);
                long GiayKetThuc = TimeUnit.MILLISECONDS.toSeconds((long) Time_end) - PhutKetThuc * 60;
                if (GiayKetThuc < 10) {
                    txt_end.setText(String.format("%d:0%d", PhutKetThuc, GiayKetThuc));
                } else {
                    txt_end.setText(String.format("%d:%d", PhutKetThuc, GiayKetThuc));
                }
                seekBar.setProgress((int) savedInstanceState.getFloat(KEY_INDEX));
            }
        }


        actionModeCallback = new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.setTitle(s);
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.action_mode, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.listen:
                        tts.speak(s.toString(), TextToSpeech.QUEUE_FLUSH, null);
                        return true;
                    case R.id.add:
                        MyVocabularyModel myVocabulary = new MyVocabularyModel(s, "", "");
                        dbHelper.createMyVocab(myVocabulary);
                        Snackbar snackbar = Snackbar
                                .make(coordinatorLayout, "Add Vocabulary successful", Snackbar.LENGTH_LONG);

                        snackbar.show();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                actionMode = null;
            }
        };

        Myhandler = new Handler();

        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            txt_tran.setText(model.getTranscript(), TextView.BufferType.SPANNABLE);
            txt_tran.setVisibility(View.VISIBLE);
        }

        Time_end = song.getDuration();
        seekBar.setMax((int) Time_end);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long PhutKetThuc = TimeUnit.MILLISECONDS.toMinutes((long) Time_end);
                long GiayKetThuc = TimeUnit.MILLISECONDS.toSeconds((long) Time_end) - PhutKetThuc * 60;
                if (GiayKetThuc < 10) {
                    txt_end.setText(String.format("%d:0%d", PhutKetThuc, GiayKetThuc));
                } else {
                    txt_end.setText(String.format("%d:%d", PhutKetThuc, GiayKetThuc));
                }
                if (song.isPlaying() == false) {
                    song.start();
                    btn_play.setImageResource(R.drawable.btn_pause);
                    OnProgressChanged(seekBar);
                    updateProgressBar();
                    song.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            if (isRepeat == false) {
                                song.seekTo(0);
                                song.pause();
                                btn_play.setImageResource(R.drawable.btn_play);
                            } else {
                                song.seekTo(0);
                                song.start();
                            }
                        }
                    });
                } else {
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

        btn_reapeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isRepeat = true;
                    Toast.makeText(BaiNghe1.this, "Repeat this audio", Toast.LENGTH_SHORT).show();
                } else {
                    isRepeat = false;
                    Toast.makeText(BaiNghe1.this, "Abort repeat this audio", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    txt_tran.setText(model.getTranscript(), TextView.BufferType.SPANNABLE);
                    getEachWord(txt_tran);
                    txt_tran.setMovementMethod(LinkMovementMethod.getInstance());
                    //  txt_tran.setTextAlignment();
                    txt_tran.setVisibility(View.VISIBLE);
                    img_topic.setVisibility(View.INVISIBLE);
                } else {
                    txt_tran.setVisibility(View.INVISIBLE);
                    img_topic.setVisibility(View.VISIBLE);
                }

            }
        });

        btn_like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(BaiNghe1.this, "You have liked this audio", Toast.LENGTH_SHORT).show();
                    isFavorite = true;
                } else {
                    Toast.makeText(BaiNghe1.this, "You have not liked this audio", Toast.LENGTH_SHORT).show();
                    isFavorite = false;
                }
            }
        });

        //x? lí tua bài hát
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

        //x? lí tua lùi bài hát
        btn_prev.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    btn_prev.setImageResource(R.drawable.prev_focused);
                    check = true;
                    song.seekTo((int) song.getCurrentPosition() - 3000);
                    Myhandler.postDelayed(Lui, 500);
                } else if (MotionEvent.ACTION_UP == event.getAction()) {
                    btn_prev.setImageResource(R.drawable.prev_deafult);
                    check = false;
                }
                return true;
            }
        });
    }

//    @Override
//    public void onResume()
//    {
//        mShaker.resume();
//        super.onResume();
//    }

    @Override
    public void onBackPressed() {
        finish();
        song.pause();
    }

    public void updateProgressBar() {
        Myhandler.postDelayed(Capnhat, 100);
    }

    public void OnProgressChanged(final SeekBar s) {
        s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Myhandler.postDelayed(Capnhat, 0);
                int current = s.getProgress();
                song.seekTo(current);
                updateProgressBar();

            }
        });
    }

    private Runnable Capnhat = new Runnable() {
        @Override
        public void run() {
            Time_start = song.getCurrentPosition();
            savingPreference();
            long PhutBatDau = TimeUnit.MILLISECONDS.toMinutes((long) Time_start);
            long GiayBatDau = TimeUnit.MILLISECONDS.toSeconds((long) Time_start) - PhutBatDau * 60;
            if (GiayBatDau < 10) {
                txt_start.setText(String.format("%d:0%d", PhutBatDau, GiayBatDau));
            } else {
                txt_start.setText(String.format("%d:%d", PhutBatDau, GiayBatDau));
            }
            seekBar.setProgress((int) Time_start);
//            savingPreference();
            Myhandler.postDelayed(this, 100);
        }
    };

    private Runnable Tua = new Runnable() {
        @Override
        public void run() {
            if (check == true) {
                song.seekTo((int) song.getCurrentPosition() + 3000);
                Myhandler.postDelayed(this, 500);
            }
        }
    };

    private Runnable Lui = new Runnable() {
        @Override
        public void run() {
            if (check == true) {
                song.seekTo((int) song.getCurrentPosition() - 3000);
                Myhandler.postDelayed(this, 500);
            }
        }
    };

    @Override
    protected void onPause() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
//        mShaker.pause();
        super.onPause();
    }

    public void getEachWord(TextView textView) {
        Spannable spans = (Spannable) textView.getText();
        Integer[] indices = getIndices(
                textView.getText().toString().trim(), ' ');
        int start = 0;
        int end = 0;
        // to cater last/only word loop will run equal to the length of indices.length
        for (int i = 0; i <= indices.length; i++) {
            ClickableSpan clickSpan = getClickableSpan();
            // to cater last/only word
            end = (i < indices.length ? indices[i] : spans.length());
            spans.setSpan(clickSpan, start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = end + 1;
        }

        textView.setHighlightColor(Color.BLUE);
    }

    private ClickableSpan getClickableSpan() {
        return new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                TextView tv = (TextView) widget;
                s = tv
                        .getText()
                        .subSequence(tv.getSelectionStart(),
                                tv.getSelectionEnd()).toString();
                actionMode = BaiNghe1.this.startActionMode(actionModeCallback);

                Log.d("tapped on:", s);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(Color.BLACK);
                ds.setUnderlineText(false);
            }
        };
    }

    public static Integer[] getIndices(String s, char c) {
        int pos = s.indexOf(c, 0);
        List<Integer> indices = new ArrayList<Integer>();
        while (pos != -1) {
            indices.add(pos);
            pos = s.indexOf(c, pos + 1);
        }
        return (Integer[]) indices.toArray(new Integer[0]);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_REPEAT, btn_reapeat.isChecked());
        outState.putBoolean(KEY_PLAY, song.isPlaying());
        outState.putFloat(KEY_INDEX, restoringPreference());
        song.pause();
    }

    public void savingPreference(){
        SharedPreferences sharedPreferences = getSharedPreferences(prefname, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putFloat("index", song.getCurrentPosition());
        Log.d("LISTENING", song.getCurrentPosition()+ "");
        editor.commit();
    }

    public float restoringPreference(){
        SharedPreferences sharedPreferences = getSharedPreferences(prefname, MODE_PRIVATE);

        float index = sharedPreferences.getFloat("index", 0);
        return index;
    }


}


