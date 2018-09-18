package com.example.vishambar.webserviceusingretrofit.advanced.models;

public class UserModel {
    private String authorName;
    private String authorEmailId;
    private String authorPassword;
    private int authorId;

    public UserModel(String authorName, String authorEmailId, String authorPassword, int authorId) {
        this.authorEmailId = authorEmailId;
        this.authorName = authorName;
        this.authorPassword = authorPassword;
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorEmailId() {
        return authorEmailId;
    }

    public void setAuthorEmailId(String authorEmailId) {
        this.authorEmailId = authorEmailId;
    }

    public String getAuthorPassword() {
        return authorPassword;
    }

    public void setAuthorPassword(String authorPassword) {
        this.authorPassword = authorPassword;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return authorName + " " + authorEmailId + " " + authorPassword + " " + authorId;

    }


}


//import com.google.gson.annotations.SerializedName;
//
//import java.io.Serializable;
//
//public class UserModel implements Serializable {
//
//    @SerializedName("authorId")
//    private long authorId;
//
//    @SerializedName("authorName")
//    private String authorName;
//
//    @SerializedName("authorEmailId")
//    private String authorEmailId;
//
//    @SerializedName("authorPassword")
//    private String authorPassword;
//
//    public UserModel() {
//        super();
//    }
//
//    public UserModel(UserModel author) {
//        this( author.authorName, author.authorEmailId, author.authorPassword,author.authorId);
//    }
//
//    public UserModel( String authorName, String authorEmailId, String authorPassword,long authorId) {
//        super();
//        this.authorId = authorId;
//        this.authorName = authorName;
//        this.authorEmailId = authorEmailId;
//        this.authorPassword = authorPassword;
//    }
//
//    public long getAuthorId() {
//        return authorId;
//    }
//
//    public void setAuthorId(long authorId) {
//        this.authorId = authorId;
//    }
//
//    public String getAuthorName() {
//        return authorName;
//    }
//    public void setAuthorName(String authorName) {
//        this.authorName = authorName;
//    }
//    public String getAuthorEmailId() {
//        return authorEmailId;
//    }
//    public void setAuthorEmailId(String authorEmailId) {
//        this.authorEmailId = authorEmailId;
//    }
//
//    public void setAuthorPassword(String authorPassword) {
//        this.authorPassword = authorPassword;
//    }
//
//    public String getAuthorPassword() {
//        return authorPassword;
//    }
//
////    @Override
////    public boolean equals(Object obj) {
////        // TODO Auto-generated method stub
////        if(obj instanceof Author) {
////            Author author=(Author) obj;
////            if(this.authorEmailId.equals(author.authorEmailId) && this.authorPassword.equals(author.authorPassword) &&
////                    this.authorId==author.authorId && this.authorName.equals(author.authorName)) {
////                return true;
////            }else {
////                return false;
////            }
////        }else {
////            return false;
////        }
////    }
//
//    @Override
//    public String toString() {
//        // TODO Auto-generated method stub
//        return "("+this.authorEmailId+", "+this.authorPassword+", "+this.authorId+", "+this.authorName+")";
//    }
//}
