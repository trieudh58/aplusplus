package com.example.hoang.revproject.Adapter;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.revproject.Activity.ShowWordActivity;
import com.example.hoang.revproject.Helper.ItemTouchHelperAdapter;
import com.example.hoang.revproject.Helper.ItemTouchHelperViewHolder;
import com.example.hoang.revproject.Model.AlarmDBHelper;
import com.example.hoang.revproject.Model.VocabularyModel;
import com.example.hoang.revproject.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Created by hoang on 11/22/2015.
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> implements ItemTouchHelperAdapter {

    private List<VocabularyModel> arrVocab = new ArrayList<VocabularyModel>();
    private Context mContext;
    private VocabularyModel vocabularyModel;
    private AlarmDBHelper dbHelper;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    HistoryViewHolder holder;
    static String word1 = "";
    private int index;

    public HistoryAdapter(Context mContext, List<VocabularyModel> arr){
        this.mContext = mContext;
        this.arrVocab = arr;
        dbHelper = new AlarmDBHelper(mContext);
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.history_item, parent, false);
        holder = new HistoryViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        vocabularyModel = arrVocab.get(position);
        int imageResource = mContext.getResources().getIdentifier(vocabularyModel.getImagePath(), null, mContext.getPackageName());
        Drawable res = mContext.getResources().getDrawable(imageResource);
        holder.word.setText(vocabularyModel.getWord());
        holder.meanOfWord.setText(vocabularyModel.getMeanOfWord());
        holder.wordImage.setImageDrawable(res);
        holder.icon1.setImageResource(R.drawable.mic);
        if (vocabularyModel.getPass() == 1) {
            holder.icon2.setImageResource(R.drawable.tick);
        } else {
            holder.icon2.setImageResource(R.drawable.wrong);
        }
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

    public class HistoryViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder{

        private CardView container;
        private TextView word;
        private TextView meanOfWord;
        private ImageView wordImage, icon1, icon2;

        public HistoryViewHolder(View view) {
            super(view);

            container = (CardView) view.findViewById(R.id.item_container);
            word = (TextView) view.findViewById(R.id.title);
            meanOfWord = (TextView) view.findViewById(R.id.description);
            wordImage = (ImageView) view.findViewById(R.id.imgNoti);
            icon1 = (ImageView) view.findViewById(R.id.Icon1);
            icon2 = (ImageView) view.findViewById(R.id.Icon2);
            icon1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    promptSpeechInput();
                    index = getAdapterPosition();
                }
            });

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ShowWordActivity.class);
                    Bundle bundle = new Bundle();
                    VocabularyModel model = arrVocab.get(getAdapterPosition());
                    bundle.putSerializable("MODEL", model);
                    intent.putExtra("DATA", bundle);
                    mContext.startActivity(intent);
                }
            });
        }

        private void promptSpeechInput(){
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say Something");

            try {
                ((Activity) mContext).startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
            } catch (ActivityNotFoundException a) {
                Toast.makeText(mContext,
                        "Speech to text not suppoted",
                        Toast.LENGTH_SHORT).show();
            }
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
    public String onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("MyAdapter", "onActivityResult");
        VocabularyModel model = arrVocab.get(index);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT:
                if (resultCode == resultCode && null != data) {
                    ArrayList<String> list = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    word1 = list.get(0);
                    if (model.getWord().equalsIgnoreCase(word1)){
                        model.setPass(1);
                        dbHelper.updateVocab(model);
                    }
                }
                break;
        }
        Toast.makeText(mContext, "You just said " + word1, Toast.LENGTH_SHORT).show();
        return word1;
    }

}