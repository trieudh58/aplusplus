package com.example.hoang.revproject.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.example.hoang.revproject.Model.AlarmModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoang on 10/25/2015.
 */
public class AlarmDBHelper extends SQLiteOpenHelper {

    public static final String TAG = "ALARM";

    // alarm Table
    public static final String TABLE_NAME = "alarm";
    public static final String COLUMN_ALARM_HOUR = "hour";
    public static final String COLUMN_ALARM_MINUTE = "minute";
    public static final String COLUMN_ALARM_DAYS = "days";
    public static final String COLUMN_ALARM_VOLUME = "volume";
    public static final String COLUMN_ALARM_ID = "id";
    public static final String COLUMN_ALARM_RING_TONE = "tone";
    public static final String COLUMN_ALARM_VOCABULARY = "vocabulary";
    public static final String COLUMN_ALARM_ENABLED = "isEnabled";


    // vocabulary table
    public static final String TABLE_NAME1 = "vocabulary";
    public static final String COLUMN_WORD_NAME = "word";
    public static final String COLUMN_WORD_ID = "id";
    public static final String COLUMN_WORD_MEAN = "meanOfWord";
    public static final String COLUMN_WORD_DESCRIPTION = "description";
    public static final String COLUMN_WORD_IMAGE = "image";
    public static final String COLUMN_WORD_SOUND = "sound";
    public static final String COLUMN_WORD_TODAY = "wordToday";
    public static final String COLUMN_WORD_TOPIC = "topic";
    public static final String COLUMN_WORD_DONE = "done";
    public static final String COLUMN_WORD_PASS = "pass";

    // listening table
    public static final String TABLE_NAME3 = "listening";
    public static final String COLUMN_LISTENING_ID = "id";
    public static final String COLUMN_LISTENING_TITLE = "title";
    public static final String COLUMN_LISTENING_TRANS = "transcription";
    public static final String COLUMN_LISTENING_FAVORITE = "isFavorite";
    public static final String COLUMN_LISTENING_AUDIO = "audio";
    public static final String COLUMN_LISTENING_IMAGE = "image";

    // myvocabulary table
    public static final String TABLE_NAME2 = "myvocabulary";
    public static final String COLUMN_MY_WORD_ID = "id";
    public static final String COLUMN_MY_WORD_NAME = "word";
    public static final String COLUMN_MY_WORD_PRONOUN = "pronoun";
    public static final String COLUMN_MY_WORD_MEAN = "mean";


    public static final String DATABASE_NAME = "alarmclock.db";
    public static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ALARM = "create table " + TABLE_NAME + " ( "
                                                    + COLUMN_ALARM_ID + " integer primary key autoincrement, "
                                                    + COLUMN_ALARM_HOUR + " integer, "
                                                    + COLUMN_ALARM_MINUTE + " integer, "
                                                    + COLUMN_ALARM_RING_TONE + " text, "
                                                    + COLUMN_ALARM_VOCABULARY + " text, "
                                                    + COLUMN_ALARM_DAYS + " text, "
                                                    + COLUMN_ALARM_VOLUME + " integer, "
                                                    + COLUMN_ALARM_ENABLED + " boolean" + ")";

    private static final String SQL_CREATE_VOCABULARY = "create table " + TABLE_NAME1 + " ( "
                                                        + COLUMN_WORD_ID + " integer primary key autoincrement, "
                                                        + COLUMN_WORD_NAME + " text, "
                                                        + COLUMN_WORD_MEAN + " text, "
                                                        + COLUMN_WORD_DESCRIPTION + " text, "
                                                        + COLUMN_WORD_IMAGE + " text, "
                                                        + COLUMN_WORD_SOUND + " text, "
                                                        + COLUMN_WORD_TOPIC + " text, "
                                                        + COLUMN_WORD_DONE + " integer default 0, "
                                                        + COLUMN_WORD_PASS + " integer default 0, "
                                                        + COLUMN_WORD_TODAY + " integer default 0 " + ")";

    private static final String SQL_CREATE_LISTENING = "create table " + TABLE_NAME3 + " ( "
                                                        + COLUMN_LISTENING_ID + " integer primary key autoincrement, "
                                                        + COLUMN_LISTENING_TITLE + " text, "
                                                        + COLUMN_LISTENING_TRANS + " text, "
                                                        + COLUMN_LISTENING_FAVORITE + " integer default 0, "
                                                        + COLUMN_LISTENING_AUDIO + " text, "
                                                        + COLUMN_LISTENING_IMAGE + " text " + ")";

    private static final String SQL_CREATE_MY_VOCABULARY = "create table " + TABLE_NAME2 + " ( "
                                                        + COLUMN_MY_WORD_ID + " integer primary key autoincrement, "
                                                        + COLUMN_MY_WORD_MEAN + " text, "
                                                        + COLUMN_MY_WORD_NAME + " text, "
                                                        + COLUMN_MY_WORD_PRONOUN + " text" + ")";

    private static final String SQL_DELETE_ALARM = "drop table if exists " + TABLE_NAME;
    private static final String SQL_DELETE_VOCABULARY = "drop table if exists " + TABLE_NAME1;
    private static final String SQL_DELETE_MY_VOCABULARY = "drop table if exists " + TABLE_NAME2;
    private static final String SQL_DELETE_LISTENING = "drop table if exists " + TABLE_NAME3;

    public AlarmDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ALARM);
        db.execSQL(SQL_CREATE_VOCABULARY);
        db.execSQL(SQL_CREATE_MY_VOCABULARY);
        db.execSQL(SQL_CREATE_LISTENING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ALARM);
        db.execSQL(SQL_DELETE_VOCABULARY);
        db.execSQL(SQL_DELETE_MY_VOCABULARY);
        db.execSQL(SQL_DELETE_LISTENING);
        onCreate(db);
    }

    private AlarmModel getModel(Cursor c){
        AlarmModel model = new AlarmModel();
        model.id = c.getInt(c.getColumnIndex(COLUMN_ALARM_ID));
        model.timeHour = c.getInt(c.getColumnIndex(COLUMN_ALARM_HOUR));
        model.timeMinute = c.getInt(c.getColumnIndex(COLUMN_ALARM_MINUTE));
        model.isEnabled = c.getInt(c.getColumnIndex(COLUMN_ALARM_ENABLED)) == 0? false : true;
        model.alarmTone = c.getString(c.getColumnIndex(COLUMN_ALARM_RING_TONE)) != ""? Uri.parse(c.getString(c.getColumnIndex(COLUMN_ALARM_RING_TONE))) : null;
        model.Vocabulary = c.getString(c.getColumnIndex(COLUMN_ALARM_VOCABULARY));
        model.volume = c.getInt(c.getColumnIndex(COLUMN_ALARM_VOLUME));
        String[] repeatDays = c.getString(c.getColumnIndex(COLUMN_ALARM_DAYS)).split(",");
        for (int i = 0; i < repeatDays.length; i++){
            model.setRepeatingDay(i, repeatDays[i].equals("false")? false : true);
        }
        return model;
    }

    private ListeningModel getListeningModel(Cursor c){
        ListeningModel model = new ListeningModel();
        model.setId(c.getInt(c.getColumnIndex(COLUMN_LISTENING_ID)));
        model.setTitle(c.getString(c.getColumnIndex(COLUMN_LISTENING_TITLE)));
        model.setTranscript(c.getString(c.getColumnIndex(COLUMN_LISTENING_TRANS)));
        model.setIsFavorite(c.getInt(c.getColumnIndex(COLUMN_LISTENING_FAVORITE)));
        model.setAudio(c.getString(c.getColumnIndex(COLUMN_LISTENING_AUDIO)));
        model.setImage(c.getString(c.getColumnIndex(COLUMN_LISTENING_IMAGE)));

        return model;
    }

    private VocabularyModel getVocabModel(Cursor c){
        VocabularyModel model = new VocabularyModel();
        model.setId(c.getInt(c.getColumnIndex(COLUMN_WORD_ID)));
        model.setWord(c.getString(c.getColumnIndex(COLUMN_WORD_NAME)));
        model.setMeanOfWord(c.getString(c.getColumnIndex(COLUMN_WORD_MEAN)));
        model.setDescription(c.getString(c.getColumnIndex(COLUMN_WORD_DESCRIPTION)));
        model.setImagePath(c.getString(c.getColumnIndex(COLUMN_WORD_IMAGE)));
        model.setSoundPath(c.getString(c.getColumnIndex(COLUMN_WORD_SOUND)));
        model.setWordToday(c.getInt(c.getColumnIndex(COLUMN_WORD_TODAY)));
        model.setTopic(c.getString(c.getColumnIndex(COLUMN_WORD_TOPIC)));
        model.setDone(c.getInt(c.getColumnIndex(COLUMN_WORD_DONE)));
        model.setPass(c.getInt(c.getColumnIndex(COLUMN_WORD_PASS)));
        return model;
    }

    private MyVocabularyModel getMyVocabModel(Cursor c){
        MyVocabularyModel model = new MyVocabularyModel();
        model.setId(c.getInt(c.getColumnIndex(COLUMN_MY_WORD_ID)));
        model.setWord(c.getString(c.getColumnIndex(COLUMN_MY_WORD_NAME)));
        model.setMean(c.getString(c.getColumnIndex(COLUMN_MY_WORD_MEAN)));
        model.setPronoun(c.getString(c.getColumnIndex(COLUMN_MY_WORD_PRONOUN)));
        return model;
    }

    private ContentValues getVocabContent(VocabularyModel model){
        ContentValues values = new ContentValues();
        values.put(COLUMN_WORD_NAME, model.getWord());
        values.put(COLUMN_WORD_MEAN, model.getMeanOfWord());
        values.put(COLUMN_WORD_DESCRIPTION, model.getDescription());
        values.put(COLUMN_WORD_IMAGE, model.getImagePath());
        values.put(COLUMN_WORD_SOUND, model.getSoundPath());
        values.put(COLUMN_WORD_TOPIC, model.getTopic());
        return values;
    }

    private ContentValues getListeningContent(ListeningModel model){
        ContentValues values = new ContentValues();
        values.put(COLUMN_LISTENING_TITLE, model.getTitle());
        values.put(COLUMN_LISTENING_TRANS, model.getTranscript());
        values.put(COLUMN_LISTENING_IMAGE, model.getImage());
        values.put(COLUMN_LISTENING_AUDIO, model.getAudio());

        return values;
    }

    private ContentValues getMyVocabContent(MyVocabularyModel model){
        ContentValues values = new ContentValues();
        values.put(COLUMN_MY_WORD_NAME, model.getWord());
        values.put(COLUMN_MY_WORD_MEAN, model.getMean());
        values.put(COLUMN_MY_WORD_PRONOUN, model.getPronoun());
        return values;
    }

    private ContentValues getContent(AlarmModel model){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ALARM_ENABLED, model.isEnabled);
        values.put(COLUMN_ALARM_HOUR, model.timeHour);
        values.put(COLUMN_ALARM_MINUTE, model.timeMinute);
        values.put(COLUMN_ALARM_VOLUME, model.volume);
        values.put(COLUMN_ALARM_RING_TONE, model.alarmTone != null? model.alarmTone.toString() : "");
        String repeat = "";
        for (int i = 0; i < 7; i++){
            repeat += model.getRepeatingDay(i) + ",";
        }
        values.put(COLUMN_ALARM_DAYS, repeat);
        values.put(COLUMN_ALARM_VOCABULARY, model.Vocabulary);
        return values;
    }

    public void createAlarm(AlarmModel model){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = getContent(model);
        long n = database.insert(TABLE_NAME, null, values);
        if(n < 0){
            Log.d(TAG, "Create Alarm failed");
        }else {
            Log.d(TAG, "Create Alarm successful");
        }
    }

    public void createListening(ListeningModel model){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = getListeningContent(model);
        long n = database.insert(TABLE_NAME3, null, values);
        if(n < 0){
            Log.d(TAG, "Create Listening failed");
        }else {
            Log.d(TAG, "Create Listening successful");
        }
    }

    public void createVocab(VocabularyModel model){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = getVocabContent(model);
        long n = database.insert(TABLE_NAME1, null, values);
        if(n < 0){
            Log.d(TAG, "Create Vocab failed");
        }else {
            Log.d(TAG, "Create Vocab successful");
        }
    }

    public void createMyVocab(MyVocabularyModel model){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = getMyVocabContent(model);
        long n = database.insert(TABLE_NAME2, null, values);
        if(n < 0){
            Log.d(TAG, "Create MyVocab failed");
        }else {
            Log.d(TAG, "Create MyVocab successful");
        }
    }

    public void updateVocab(VocabularyModel model){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = getVocabContent(model);
        values.put(COLUMN_WORD_TODAY, model.getWordToday());
        values.put(COLUMN_WORD_PASS, model.getPass());
        values.put(COLUMN_WORD_DONE, model.getDone());
        long n = database.update(TABLE_NAME1, values, COLUMN_WORD_ID + " = ?", new String[]{String.valueOf(model.getId())});
        if(n < 0){
            Log.d(TAG, "Updated Vocab failed");
        }else {
            Log.d(TAG, "Updated Vocab successful");
        }
    }

    public void updateListening(ListeningModel model){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = getListeningContent(model);
        values.put(COLUMN_LISTENING_FAVORITE, model.getIsFavorite());
        long n = database.update(TABLE_NAME3, values, COLUMN_LISTENING_ID + " = ?", new String[]{String.valueOf(model.getId())});
        if(n < 0){
            Log.d(TAG, "Updated Listening failed");
        }else {
            Log.d(TAG, "Updated Listening successful");
        }
    }

    public void updateAlarm(AlarmModel model){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = getContent(model);
        long n = database.update(TABLE_NAME, values, COLUMN_ALARM_ID + " = ?", new String[]{String.valueOf(model.id)});
        if(n < 0){
            Log.d(TAG, "Updated Alarm failed");
        }else {
            Log.d(TAG, "Updated Alarm successful");
        }
    }

    public void updateMyVocab(MyVocabularyModel model){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = getMyVocabContent(model);
        long n = database.update(TABLE_NAME2, values, COLUMN_MY_WORD_ID + " = ?", new String[]{String .valueOf(model.getId())});
        if(n < 0){
            Log.d(TAG, "Updated MyVocab failed");
        }else {
            Log.d(TAG, "Updated MyVocab successful");
        }
    }

    public AlarmModel getAlarm(int id){
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + TABLE_NAME + " where " + COLUMN_ALARM_ID + " = " + id;
        Cursor c = database.rawQuery(query, null);
        if (c.moveToNext()){
            return getModel(c);
        }
        return null;
    }

    public ListeningModel getListening(int id){
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + TABLE_NAME3 + " where " + COLUMN_LISTENING_ID + " = " + id;
        Cursor c = database.rawQuery(query, null);
        if (c.moveToNext()){
            return getListeningModel(c);
        }
        return null;
    }

    public VocabularyModel getVocab(int id){
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + TABLE_NAME1 + " where " + COLUMN_WORD_ID + " = " + id;
        Cursor c = database.rawQuery(query, null);
        if (c.moveToNext()){
            return getVocabModel(c);
        }
        return null;
    }

    public MyVocabularyModel getMyVocab(int id){
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + TABLE_NAME2 + " where " + COLUMN_MY_WORD_ID + " = " + id;
        Cursor c = database.rawQuery(query, null);
        if (c.moveToNext()){
            return getMyVocabModel(c);
        }
        return null;
    }

    public int deleteAlarm(int id){
        return getWritableDatabase().delete(TABLE_NAME, COLUMN_ALARM_ID + " = ?", new String[] { String.valueOf(id) });
    }

    public int deleteVocab(int id){
        return getWritableDatabase().delete(TABLE_NAME1, COLUMN_WORD_ID + " = ?", new String[] { String.valueOf(id) });
    }

    public int deleteMyVocab(int id){
        return getWritableDatabase().delete(TABLE_NAME2, COLUMN_MY_WORD_ID + " = ?", new String[] { String.valueOf(id) });
    }

    public List<AlarmModel> getListAlarms(){
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + TABLE_NAME;
        Cursor c = database.rawQuery(query, null);
        List<AlarmModel> alarmModels = new ArrayList<AlarmModel>();
        c.moveToFirst();
        while (!c.isAfterLast()){
            alarmModels.add(getModel(c));
            c.moveToNext();
        }
        database.close();
        return alarmModels;
    }

    public List<ListeningModel> getListListening(){
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + TABLE_NAME3;
        Cursor c = database.rawQuery(query, null);
        List<ListeningModel> listeningModels = new ArrayList<ListeningModel>();
        c.moveToFirst();
        while (!c.isAfterLast()){
            listeningModels.add(getListeningModel(c));
            c.moveToNext();
        }
        database.close();
        return listeningModels;
    }

    public List<VocabularyModel> getListVocabs(){
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + TABLE_NAME1;
        Cursor c = database.rawQuery(query, null);
        List<VocabularyModel> vocabModel = new ArrayList<VocabularyModel>();
        c.moveToFirst();
        while (!c.isAfterLast()){
            vocabModel.add(getVocabModel(c));
            c.moveToNext();
        }
        database.close();
        return vocabModel;
    }

    public List<VocabularyModel> getListVocabs(String topic){
        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery("select * from " + TABLE_NAME1 + " where " + COLUMN_WORD_TOPIC + " = " + "?", new String[]{topic});
        List<VocabularyModel> vocabModel = new ArrayList<VocabularyModel>();
        c.moveToFirst();
        while (!c.isAfterLast()){
            vocabModel.add(getVocabModel(c));
            c.moveToNext();
        }
        database.close();
        return vocabModel;
    }

    public List<MyVocabularyModel> getListMyVocab(){
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + TABLE_NAME2;
        Cursor c = database.rawQuery(query, null);
        List<MyVocabularyModel> myVocab = new ArrayList<MyVocabularyModel>();
        c.moveToFirst();
        while (!c.isAfterLast()){
            myVocab.add(getMyVocabModel(c));
            c.moveToNext();
        }
        database.close();
        return myVocab;
    }

    public List<VocabularyModel> getListVocabDone(){
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + TABLE_NAME1 + " where " + COLUMN_WORD_DONE + " = 1";
        Cursor c = database.rawQuery(query, null);
        List<VocabularyModel> myVocab = new ArrayList<VocabularyModel>();
        c.moveToFirst();
        while (!c.isAfterLast()){
            myVocab.add(getVocabModel(c));
            c.moveToNext();
        }
        database.close();
        return myVocab;
    }

    public List<VocabularyModel> getListWordsToday(int value){
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + TABLE_NAME1 + " where " + COLUMN_WORD_TODAY + " = " + value;
        Cursor c = database.rawQuery(query, null);
        List<VocabularyModel> vocabularyModels = new ArrayList<VocabularyModel>();
        c.moveToFirst();
        while (!c.isAfterLast()){
            vocabularyModels.add(getVocabModel(c));
            c.moveToNext();
        }
        database.close();
        return vocabularyModels;
    }
}
