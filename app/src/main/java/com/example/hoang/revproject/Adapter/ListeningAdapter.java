package com.example.hoang.revproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoang.revproject.Activity.BaiNghe1;
import com.example.hoang.revproject.Activity.ShowWordActivity;
import com.example.hoang.revproject.Helper.ItemTouchHelperViewHolder;
import com.example.hoang.revproject.Model.AlarmDBHelper;
import com.example.hoang.revproject.Model.ListeningModel;
import com.example.hoang.revproject.Model.VocabularyModel;
import com.example.hoang.revproject.R;

import java.util.List;

/**
 * Created by hoang on 11/23/2015.
 */
public class ListeningAdapter extends RecyclerView.Adapter<ListeningAdapter.ListeningViewHolder>{

    private Context mContext;
    private List<ListeningModel> listeningModels;
    private AlarmDBHelper dbHelper;

    public ListeningAdapter(Context mContext, List<ListeningModel> arr){
        this.mContext = mContext;
        this.listeningModels = arr;
        dbHelper = new AlarmDBHelper(mContext);
    }

    @Override
    public ListeningViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.listening_item, parent, false);

        return new ListeningViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListeningViewHolder holder, int position) {
        ListeningModel model = listeningModels.get(position);
        int imageResource = mContext.getResources().getIdentifier(model.getImage(), null, mContext.getPackageName());
        Drawable res = mContext.getResources().getDrawable(imageResource);
        holder.listeningImage.setImageDrawable(res);
        holder.title.setText(model.getTitle());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ListeningViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

        private CardView container;
        private TextView title;
        private ImageView listeningImage;

        public ListeningViewHolder(View view){
            super(view);

            container = (CardView) view.findViewById(R.id.item_container);
            title = (TextView) view.findViewById(R.id.title);
            listeningImage = (ImageView) view.findViewById(R.id.imgItem);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, BaiNghe1.class);
                    ListeningModel model = listeningModels.get(getAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("MODEL", model);
                    intent.putExtra("DATA", bundle);
                    mContext.startActivity(intent);
                }
            });
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