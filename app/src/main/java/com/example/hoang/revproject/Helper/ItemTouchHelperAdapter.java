package com.example.hoang.revproject.Helper;

/**
 * Created by hoang on 11/1/2015.
 */
public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
