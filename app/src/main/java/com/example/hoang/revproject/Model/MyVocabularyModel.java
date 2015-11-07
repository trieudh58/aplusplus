package com.example.hoang.revproject.Model;

/**
 * Created by hoang on 11/6/2015.
 */
public class MyVocabularyModel {

    private String word;
    private String pronoun;
    private String mean;
    private int id;

    public MyVocabularyModel(){}

    public MyVocabularyModel(String word, String pronoun, String mean){
        this.word = word;
        this.pronoun = pronoun;
        this.mean = mean;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getMean() {
        return mean;
    }

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }

    public String getPronoun() {
        return pronoun;
    }
}
