package com.example.vishambar.webserviceusingretrofit.advanced.models;

public class RegisterModel {
    private String authorName;
    private String authorEmailId;
    private String authorPassword;



    public RegisterModel(String authorName, String authorEmailId, String authorPassword) {
        this.authorEmailId = authorEmailId;
        this.authorName = authorName;
        this.authorPassword = authorPassword;

    }


    @Override
    public String toString() {
        return authorName+" "+authorEmailId+" "+" "+authorPassword;

    }
}
