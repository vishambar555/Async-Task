package com.example.vishambar.webserviceusingretrofit.advanced.models;

import java.util.Date;

public class ItemsModel {
    private long id;
    private String todoString;
    private long date;
    private String place;

    private String authorEmailId;


    public ItemsModel(long id, String todoString, String authorEmailId,String place) {
        super();
        this.id = id;
        this.todoString = todoString;
        this.date = new Date().getTime();
        this.authorEmailId = authorEmailId;
        this.place = place;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTodoString() {
        return todoString;
    }
    public void setTodoString(String todoString) {
        this.todoString = todoString;
    }
    public long getDate() {
        return date;
    }
    public void setDate(long date) {
        this.date = date;
    }

    public String getAuthorEmailId() {
        return authorEmailId;
    }

    public void setAuthorEmailId(String authorEmailId) {
        this.authorEmailId = authorEmailId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }



    @Override
    public String toString() {
        return "("+this.id+", "+this.todoString+", "+this.place+")";
    }

}
