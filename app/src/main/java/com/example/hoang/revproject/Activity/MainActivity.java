package com.example.hoang.revproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.hoang.revproject.Adapter.HomeAdapter;
import com.example.hoang.revproject.Model.AlarmDBHelper;
import com.example.hoang.revproject.Model.HomeItem;
import com.example.hoang.revproject.Model.VocabularyModel;
import com.example.hoang.revproject.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<HomeItem> arr;
    private HomeAdapter adapter;
    private GridView grid;
    private Toolbar toolbar;
    private AlarmDBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getControls();
        initial();
        addEvents();
    }

    public void getControls(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        arr = new ArrayList<HomeItem>();
        adapter = new HomeAdapter(MainActivity.this, arr);
        grid = (GridView) findViewById(R.id.gv);
        grid.setAdapter(adapter);
        dbHelper = new AlarmDBHelper(this);
    }

    public void initial(){
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initData();

    }

    public void initData(){
        HomeItem item1 = new HomeItem("My Vocabulary", R.drawable.words_icon);
        HomeItem item2 = new HomeItem("Listening", R.drawable.listen_icon);
        HomeItem item3 = new HomeItem("Games", R.drawable.games_icon);
        HomeItem item4 = new HomeItem("Alarm", R.drawable.alarms_icon);

        arr.add(item1);
        arr.add(item2);
        arr.add(item3);
        arr.add(item4);

        adapter.notifyDataSetChanged();
        VocabularyModel model3 = new VocabularyModel("Music", "/'mju:zik/", "Danh từ \n" +
                "Nhạc, âm nhạc\n" +
                "âm nhạc dân tộc\n" +
                "o have an ear for music\n" +
                "Có năng khiếu về âm nhạc\n" +
                "to set a poem to music\n" +
                "phổ nhạc một bài thơ", "@drawable/alarm","music");
        VocabularyModel model4 = new VocabularyModel("Friend", "/frend/", "Danh từ \n" +
                "Người bạn\n" +
                "Người quen sơ, ông bạn\n" +
                "Người ủng hộ, người giúp đỡ\n" +
                "Cái giúp ích", "@drawable/alarm1","friend");
        dbHelper.createVocab(model3);
        dbHelper.createVocab(model4);
    }

    public void addEvents(){
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent0 = new Intent(MainActivity.this, MyVocabulary.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, ListListener.class);
                        startActivity(intent1);
                        break;
                    case 3:
                        Intent intent3 = new Intent(MainActivity.this, AlarmListActivity.class);
                        startActivity(intent3);
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()){
            case R.id.nav_settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
