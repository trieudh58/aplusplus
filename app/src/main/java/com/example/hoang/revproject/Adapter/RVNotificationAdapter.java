package com.example.hoang.revproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoang.revproject.Activity.ShowWordActivity;
import com.example.hoang.revproject.Helper.ItemTouchHelperAdapter;
import com.example.hoang.revproject.Helper.ItemTouchHelperViewHolder;
import com.example.hoang.revproject.Model.AlarmDBHelper;
import com.example.hoang.revproject.Model.VocabularyModel;
import com.example.hoang.revproject.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hoang on 10/31/2015.
 */
public class RVNotificationAdapter extends RecyclerView.Adapter<RVNotificationAdapter.VocabRecyclerViewHolder> implements ItemTouchHelperAdapter{

    private List<VocabularyModel> arrVocab = new ArrayList<VocabularyModel>();
    private Context mContext;
    private VocabularyModel vocabularyModel;
    private AlarmDBHelper dbHelper;
    private View mView;
    private WindowManager windowManager;

    public RVNotificationAdapter(Context mContext, List<VocabularyModel> arr){
        this.mContext = mContext;
        this.arrVocab = arr;
        dbHelper = new AlarmDBHelper(mContext);
    }

    @Override
    public VocabRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.notification, parent, false);
        return new VocabRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VocabRecyclerViewHolder holder, int position) {
        vocabularyModel = arrVocab.get(position);
        int imageResource = mContext.getResources().getIdentifier(vocabularyModel.getImagePath(), null, mContext.getPackageName());
        Drawable res = mContext.getResources().getDrawable(imageResource);
        holder.word.setText(vocabularyModel.getWord());
        holder.meanOfWord.setText(vocabularyModel.getMeanOfWord());
        holder.wordImage.setImageDrawable(res);
    }

    @Override
    public int getItemCount() {
        return arrVocab.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(arrVocab, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        arrVocab.get(position).setWordToday(0);
        dbHelper.updateVocab(arrVocab.get(position));
        arrVocab.remove(position);
        notifyItemRemoved(position);
    }

    public class VocabRecyclerViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder{

        private CardView container;
        private TextView word;
        private TextView meanOfWord;
        private ImageView wordImage;

        public VocabRecyclerViewHolder(View view){
            super(view);

            container = (CardView) view.findViewById(R.id.item_container);
            word = (TextView) view.findViewById(R.id.title);
            meanOfWord = (TextView) view.findViewById(R.id.description);
            wordImage = (ImageView) view.findViewById(R.id.imgNoti);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }

}
