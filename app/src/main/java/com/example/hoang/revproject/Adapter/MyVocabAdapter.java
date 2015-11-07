package com.example.hoang.revproject.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoang.revproject.Model.AlarmDBHelper;
import com.example.hoang.revproject.Model.MyVocabularyModel;
import com.example.hoang.revproject.R;

import java.util.List;

/**
 * Created by hoang on 11/6/2015.
 */
public class MyVocabAdapter extends BaseAdapter {

    private List<MyVocabularyModel> arr;
    private Context mContext;
    private AlarmDBHelper dbHelper;

    public MyVocabAdapter(Context context, List<MyVocabularyModel> arr){
        this.mContext = context;
        this.arr = arr;
        dbHelper = new AlarmDBHelper(mContext);
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.custom_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.front = (CardView) convertView.findViewById(R.id.front);
            viewHolder.word = (TextView) convertView.findViewById(R.id.word);
            viewHolder.pronoun = (TextView) convertView.findViewById(R.id.pronoun);
            viewHolder.mean = (TextView) convertView.findViewById(R.id.mean);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final MyVocabularyModel model = arr.get(position);
        viewHolder.word.setText(model.getWord());
        viewHolder.mean.setText(model.getMean());
        viewHolder.pronoun.setText(model.getPronoun());

//        viewHolder.front.setCardBackgroundColor(Color.RED);

        return convertView;
    }

    public class ViewHolder{
        private CardView front;
        private TextView word;
        private TextView pronoun;
        private TextView mean;
    }
}
