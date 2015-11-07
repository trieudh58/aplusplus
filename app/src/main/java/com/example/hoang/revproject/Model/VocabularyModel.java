package com.example.hoang.revproject.Model;

import java.io.Serializable;

/**
 * Created by hoang on 10/31/2015.
 */
public class VocabularyModel implements Serializable {

    private int id;
    private String Word;
    private String meanOfWord;
    private String Description;
    private String imagePath;
    private String soundPath;
    private int wordToday;

    public VocabularyModel(){}

    public VocabularyModel(String Word, String mean, String Description, String imagePath, String soundPath){
        this.Word = Word;
        this.meanOfWord = mean;
        this.Description = Description;
        this.imagePath = imagePath;
        this.soundPath = soundPath;
    }

    public void setWordToday(int wordToday) {
        this.wordToday = wordToday;
    }

    public int getWordToday() {
        return wordToday;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setMeanOfWord(String meanOfWord) {
        this.meanOfWord = meanOfWord;
    }

    public void setSoundPath(String soundPath) {
        this.soundPath = soundPath;
    }

    public void setWord(String word) {
        Word = word;
    }

    public String getWord() {
        return Word;
    }

    public String getSoundPath() {
        return soundPath;
    }

    public String getMeanOfWord() {
        return meanOfWord;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDescription() {
        return Description;
    }
}
