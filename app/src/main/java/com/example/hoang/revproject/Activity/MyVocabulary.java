package com.example.hoang.revproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.hoang.revproject.Adapter.MyVocabAdapter;
import com.example.hoang.revproject.Model.AlarmDBHelper;
import com.example.hoang.revproject.Model.MyVocabularyModel;
import com.example.hoang.revproject.R;
import com.fortysevendeg.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.swipelistview.SwipeListView;

import java.util.ArrayList;
import java.util.List;

public class MyVocabulary extends AppCompatActivity {

    private SwipeListView swipeListView;
    private MyVocabAdapter adapter;
    private List<MyVocabularyModel> arr;
    private AlarmDBHelper dbHelper;
    public static final int ADD_MY_VOCAB = 10;
    private ArrayList<Integer> listId = new ArrayList<Integer>();
    public static final int EDIT_MY_VOCAB = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vocabulary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getControls();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyVocabulary.this, AddMyVocabActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ID", -1);
                intent.putExtra("DATA", bundle);
                startActivityForResult(intent, ADD_MY_VOCAB);
            }
        });
    }

    public void getControls(){
        dbHelper = new AlarmDBHelper(this);
        swipeListView = (SwipeListView) findViewById(R.id.swipe_lv_list);
        arr = dbHelper.getListMyVocab();
        adapter = new MyVocabAdapter(this, arr);

        swipeListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        swipeListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                mode.setTitle("Selected (" + swipeListView.getCountSelected() + ")");
                listId.add(position);
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.choice_mode, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delete:
                        swipeListView.dismissSelected();
                        mode.finish();
                        return true;
                    case R.id.edit:
                        Intent intent = new Intent(MyVocabulary.this, AddMyVocabActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("ID", arr.get(listId.get(0)).getId());
                        intent.putExtra("DATA", bundle);
                        startActivityForResult(intent, EDIT_MY_VOCAB);
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                swipeListView.unselectedChoiceStates();
            }
        });

        swipeListView.setSwipeListViewListener(new BaseSwipeListViewListener() {
            @Override
            public void onOpened(int position, boolean toRight) {
                super.onOpened(position, toRight);
            }

            @Override
            public void onClosed(int position, boolean fromRight) {
                super.onClosed(position, fromRight);
            }

            @Override
            public void onListChanged() {
                super.onListChanged();
            }

            @Override
            public void onMove(int position, float x) {
                super.onMove(position, x);
            }

            @Override
            public void onStartOpen(int position, int action, boolean right) {
                Log.d("swipe", String.format("onStartOpen %d - action %d", position, action));
            }

            @Override
            public void onStartClose(int position, boolean right) {
                Log.d("swipe", String.format("onCloseOpen %d ", position));
            }

            @Override
            public void onClickFrontView(int position) {
                swipeListView.openAnimate(position);
            }

            @Override
            public void onClickBackView(int position) {
                swipeListView.closeAnimate(position);
            }

            @Override
            public void onDismiss(int[] reverseSortedPositions) {
                super.onDismiss(reverseSortedPositions);
                for (int position : reverseSortedPositions) {
                    dbHelper.deleteMyVocab(arr.get(position).getId());
                }
                updateMyVocab();
            }
        });

        swipeListView.setAdapter(adapter);

        reload();
    }

    private void reload() {
        swipeListView.setSwipeMode(SwipeListView.SWIPE_MODE_BOTH);
        swipeListView.setSwipeActionLeft(SwipeListView.SWIPE_ACTION_CHOICE);
        swipeListView.setSwipeActionRight(SwipeListView.SWIPE_ACTION_REVEAL);
        swipeListView.setOffsetRight(convertDpToPixel(0f));
        swipeListView.setAnimationTime(50);
//        swipeListView.setSwipeOpenOnLongPress(true);
    }

    public int convertDpToPixel(float dp) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
    }

    public void updateMyVocab(){
        arr.clear();
        List<MyVocabularyModel> newList = dbHelper.getListMyVocab();
        arr.addAll(newList);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            switch (requestCode) {
                case ADD_MY_VOCAB:
                    updateMyVocab();
                    break;
                case EDIT_MY_VOCAB:
                    updateMyVocab();
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateMyVocab();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_vocab, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    adapter.getFilter().filter("");
                } else {
                    adapter.getFilter().filter(newText.toString());
                }
                return true;
            }
        });
        return true;
    }
}
