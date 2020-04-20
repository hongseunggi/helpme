package com.example.help.models;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Uinfo {

    private String doucumentId;
    private String name;
    private String height;
    private String weight;
    private String birthdate;

    @ServerTimestamp
    private Date date;


    public Uinfo() {
    }

    public Uinfo(String doucumentId, String name, String height, String weight, String birthdate) {
        this.doucumentId = doucumentId;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.birthdate = birthdate;
    }

    public String getDoucumentId() {
        return doucumentId;
    }

    public void setDoucumentId(String doucumentId) {
        this.doucumentId = doucumentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }


    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Uinfo{" +
                "doucumentId='" + doucumentId + '\'' +
                ", name='" + name + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", date=" + date +
                '}';
    }

}
