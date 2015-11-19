package com.example.hoang.revproject.Model;

import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.revproject.Activity.AlarmListActivity;
import com.example.hoang.revproject.Activity.ShowWordActivity;
import com.example.hoang.revproject.Adapter.RVNotificationAdapter;
import com.example.hoang.revproject.Helper.SimpleItemTouchHelperCallback;
import com.example.hoang.revproject.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hoang on 10/30/2015.
 */
public class SettingsService extends Service{

    private Notification notification;
    private NotificationManager notificationManager;
    private static int idNotification = 1;
    private View mView;
    private int id = -1;
    private AlarmDBHelper database;
    private ItemTouchHelper mItemTouchHelper;
    private List<VocabularyModel> arrVocabNoti;
    private List<VocabularyModel> arrVocab;
    private VocabularyModel notiModel;
    private List<Integer> listId = new ArrayList<Integer>();
    private  float x1,x2, y1, y2;
    private RVNotificationAdapter adapter;
    LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        database = new AlarmDBHelper(this.getApplicationContext());
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        int strWord = Integer.parseInt(sharedPreferences.getString("repeating_word", "2"));
        String topic = sharedPreferences.getString("topic", "art");

        arrVocab = database.getListVocabs(topic);
        arrVocabNoti = new ArrayList<VocabularyModel>();

        Random rd = new Random();
        for (int i = 0; i < strWord; i++) {
            int id = rd.nextInt(arrVocab.size());
            listId.add(id);
        }

        id = rd.nextInt(listId.size());

        notiModel = arrVocab.get(id);  //database.getVocab(listId.get(id));
        notiModel.setWordToday(1);
        notiModel.setDone(1);

        addView();
        new UpdateLayout().execute();

        return super.onStartCommand(intent, flags, startId);
    }

    private void addNotification(){
        notificationManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);

        Intent activityIntent = new Intent(this.getApplicationContext(), ShowWordActivity.class);

        Bundle bundle1 = new Bundle();
        bundle1.putSerializable("MODEL", notiModel);
        activityIntent.putExtra("DATA", bundle1);
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, activityIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            Notification notification = new Notification.Builder(this).setContentTitle("REV")
                    .setContentText("You have a new word today")
                    .setSmallIcon(R.drawable.alarm)
                    .setContentIntent(pendingIntent).build();
            notification.defaults |= Notification.DEFAULT_VIBRATE;
            notification.defaults |= Notification.DEFAULT_SOUND;
            notificationManager.notify(idNotification, notification);
            idNotification++;
        }
    }

    private void registerBroadcastReceiver() {
        final WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.notification_layout, null);

        arrVocabNoti = database.getListWordsToday(1);
        arrVocabNoti.add(notiModel);
        database.updateVocab(notiModel);
        adapter = new RVNotificationAdapter(this.getApplicationContext(), arrVocabNoti);
        RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

        PowerManager pm = (PowerManager) this
                .getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK
                | PowerManager.ACQUIRE_CAUSES_WAKEUP
                | PowerManager.ON_AFTER_RELEASE, "MyWakeLock");
        wakeLock.acquire();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                0, 0, WindowManager.LayoutParams.TYPE_SYSTEM_ERROR, WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,PixelFormat.RGBA_8888);
        layoutParams.gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;

//        if (adapter.getItemCount() != 0){
        windowManager.addView(mView, layoutParams);
//        }else{
//            windowManager.removeView(mView);
//        }

        mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        y1 = event.getY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        y2 = event.getY();

                        if (x1 < x2 || x1 > x2)
                        {
                            windowManager.removeView(mView);
                        }
                        return true;
                }
                return false;
            }
        });

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VocabularyModel model = arrVocabNoti.get(0);
                Intent intent = new Intent(getApplicationContext(),ShowWordActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle = new Bundle();
                bundle.putSerializable("MODEL", model);
                intent.putExtra("DATA", bundle);
                getApplicationContext().startActivity(intent);
                arrVocabNoti.clear();
                adapter.notifyDataSetChanged();
                windowManager.removeView(mView);
            }
        });
    }

    private void addView(){
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        boolean isScreenOn = powerManager.isScreenOn();
        KeyguardManager myKeyManager = (KeyguardManager)getSystemService(Context.KEYGUARD_SERVICE);
        if (!isScreenOn || myKeyManager.inKeyguardRestrictedInputMode()) {
            registerBroadcastReceiver();
            addNotification();
        }else {
            addNotification();
        }
    }

    private class UpdateLayout extends AsyncTask<Void, Integer, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            int size = adapter.getItemCount();
            publishProgress(size);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int size = values[0];
            if (size == 0){
                WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);
                windowManager.removeView(mView);
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(), "Don't forget to learn your word today!", Toast.LENGTH_SHORT).show();
        }
    }

}
