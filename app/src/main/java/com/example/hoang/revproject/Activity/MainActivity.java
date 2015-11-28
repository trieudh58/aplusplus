package com.example.hoang.revproject.Activity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.hoang.revproject.Helper.InitFakeData;
import com.example.hoang.revproject.Model.AlarmDBHelper;
import com.example.hoang.revproject.Model.HomeItem;
import com.example.hoang.revproject.Model.MyVocabularyModel;
import com.example.hoang.revproject.Model.VocabularyModel;
import com.example.hoang.revproject.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<HomeItem> arr;
    private HomeAdapter adapter;
    private GridView grid;
    private Toolbar toolbar;
    private AlarmDBHelper dbHelper;
    private List<VocabularyModel> list = new ArrayList<VocabularyModel>();


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
        toolbar.setNavigationIcon(R.drawable.words_icon);
        toolbar.setTitle("Rev");
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
        HomeItem item4 = new HomeItem("Alarms", R.drawable.alarms_icon);

        arr.add(item1);
        arr.add(item2);
        arr.add(item3);
        arr.add(item4);

        adapter.notifyDataSetChanged();

        InitFakeData initFakeData = new InitFakeData(this);
        list = dbHelper.getListVocabs();
        if (list.size() == 0) {
            initFakeData.initData();
        }
    }

    public void addEvents(){
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent0 = new Intent(MainActivity.this, MyVocabulary.class);
                        Bundle bundleAnimation = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            bundleAnimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in_left, R.anim.slide_out_right).toBundle();
                            startActivity(intent0, bundleAnimation);
                        }
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, ListListener.class);
                        Bundle bundleAnimation1 = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            bundleAnimation1 = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in_left, R.anim.slide_out_right).toBundle();
                            startActivity(intent1, bundleAnimation1);
                        }
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, TroChoi.class);
                        Bundle bundleAnimation2 = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            bundleAnimation2 = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in_left, R.anim.slide_out_right).toBundle();
                            startActivity(intent2, bundleAnimation2);
                        }
                        break;
                    case 3:
                        Intent intent3 = new Intent(MainActivity.this, AlarmListActivity.class);
                        Bundle bundleAnimation3 = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            bundleAnimation3 = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in_left, R.anim.slide_out_right).toBundle();
                            startActivity(intent3, bundleAnimation3);
                        }
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
            new AlertDialog.Builder(this)
                    .setTitle("Really Exit?")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.super.onBackPressed();
                        }
                    }).create().show();
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
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
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
            case R.id.nav_history:
                Intent intent1 = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_info:
                Intent intent2 = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent2);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
