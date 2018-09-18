package com.example.vishambar.webserviceusingretrofit.advanced.utils;

import com.example.vishambar.webserviceusingretrofit.advanced.models.ItemsModel;
import com.example.vishambar.webserviceusingretrofit.advanced.models.TokenModel;
import com.example.vishambar.webserviceusingretrofit.advanced.models.RegisterModel;
import com.example.vishambar.webserviceusingretrofit.advanced.models.SignoutModel;
import com.example.vishambar.webserviceusingretrofit.advanced.models.ToDoItem;
import com.example.vishambar.webserviceusingretrofit.advanced.models.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {
    @POST(Constants.register)
    Call<UserModel> register(@Body RegisterModel registerModel);

    @POST(Constants.login)
    Call<TokenModel> login(@Body UserModel userModel);


    @POST(Constants.logout)
    Call<SignoutModel> logout(@Body SignoutModel signoutModel);

    @POST(Constants.addToDoItem)
    Call<ItemsModel> addToDoItem(@Header(value = "token") String token, @Body ItemsModel itemsModel);

//    @GET(Constants.getToDoItem+"{authorEmailId}/")
//    Call<List<ToDoItem>> getToDoList(@Path(value = "authorEmailId") String authorEmailId, @Header(value = "token") String token);

    @GET(Constants.addToDoItem+"{authorEmailId}/")
    Call<List<ItemsModel>> getItems(@Path(value = "authorEmailId") String email,@Header(value = "token") String token);

//    @HTTP(method = "DELETE", path = ToDoAppRestAPI.deleteToDo, hasBody = true)
//    Call<ResponseBody> deleteToDo(@Header(value = "token") String token, @Body ToDoItem toDoItem);
//
//    @PUT(Constants.modifyToDoUrl)
//    Call<ToDoItem> modifyToDoItem(@Header(value = "token") String token, @Body ModifyToDoPayloadBean modifyToDoPayloadBean);

}
