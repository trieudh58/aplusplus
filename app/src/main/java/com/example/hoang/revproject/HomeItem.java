package com.example.hoang.revproject;

/**
 * Created by hoang on 10/24/2015.
 */
public class HomeItem {
    private String itemName;
    private int itemImage;

    public HomeItem(){}

    public HomeItem(String itemName, int itemImage){
        this.itemName = itemName;
        this.itemImage = itemImage;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }

    public int getItemImage() {
        return itemImage;
    }

    public String getItemName() {
        return itemName;
    }
}
