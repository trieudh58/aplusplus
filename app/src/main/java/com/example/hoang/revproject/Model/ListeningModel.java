package com.example.hoang.revproject.Model;

import java.io.Serializable;
/**
 * Created by dangt on 11/23/2015.
 */
public class ListeningModel implements Serializable {

    public int id;
    private String title;
    private String transcript;
    private boolean isFavorite;
    private String image;
    private String audio;

    public ListeningModel() {}

    public ListeningModel(String title, String transcript, boolean isFavorite, String image, String audio) {
        this.title = title;
        this.transcript = transcript;
        this.isFavorite = isFavorite;
        this.image = image;
        this.audio = audio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public String getTranscript() {
        return this.transcript;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public boolean getIsFavorite() {
        return this.isFavorite;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return this.image;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
