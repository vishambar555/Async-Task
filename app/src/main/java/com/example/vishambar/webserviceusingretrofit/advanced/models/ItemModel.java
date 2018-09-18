package com.example.vishambar.webserviceusingretrofit.advanced.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class ItemModel implements Parcelable{
    private long id;
    private String todoString;
    private long date;
    private String place;

    private String authorEmailId;


    public ItemModel(long id, String todoString, String authorEmailId, String place,long date) {
        super();
        this.id = id;
        this.todoString = todoString;
        this.date = new Date().getTime();
        this.authorEmailId = authorEmailId;
        this.place = place;
        this.date=date;
    }

    protected ItemModel(Parcel in) {
        id = in.readLong();
        todoString = in.readString();
        date = in.readLong();
        place = in.readString();
        authorEmailId = in.readString();
    }

    public static final Creator<ItemModel> CREATOR = new Creator<ItemModel>() {
        @Override
        public ItemModel createFromParcel(Parcel in) {
            return new ItemModel(in);
        }

        @Override
        public ItemModel[] newArray(int size) {
            return new ItemModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(todoString);
        parcel.writeLong(date);
        parcel.writeString(place);
        parcel.writeString(authorEmailId);
    }
}
