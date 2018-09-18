package com.example.vishambar.webserviceusingretrofit.advanced.models;

public class SignoutModel {

    private String authorName;
    private String authorEmailId;
    private int authorId;


    public SignoutModel(String authorName, String authorEmailId, int authorId) {
        this.authorEmailId = authorEmailId;
        this.authorName = authorName;

        this.authorId=authorId;
    }

}
