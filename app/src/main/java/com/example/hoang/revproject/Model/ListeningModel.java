package com.example.hoang.revproject.Model;

import java.io.Serializable;
/**
 * Created by dangt on 11/23/2015.
 */
public class ListeningModel implements Serializable {

    public int id;
    private String title;
    private String transcript;
    private int isFavorite;
    private String image;
    private String audio;

    public ListeningModel() {}

    public ListeningModel(String title, String transcript, String image, String audio) {
        this.title = title;
        this.transcript = transcript;
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

    public int getIsFavorite() {
        return isFavorite;
    }

    public String getAudio() {
        return audio;
    }

    public String getImage() {
        return image;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }
}
