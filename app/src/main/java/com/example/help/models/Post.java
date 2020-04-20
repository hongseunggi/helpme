package com.example.help.models;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Post {

    private String doucumentId;
    private String title;
    private String contents;
    @ServerTimestamp
    private Date date;


    public Post() {
    }

    public Post(String doucumentId, String title, String contents) {
        this.doucumentId = doucumentId;
        this.title = title;
        this.contents = contents;
    }

    public String getDoucumentId() {
        return doucumentId;
    }

    public void setDoucumentId(String doucumentId) {
        this.doucumentId = doucumentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Post(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Post{" +
                "doucumentId='" + doucumentId + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", date=" + date +
                '}';
    }
}
