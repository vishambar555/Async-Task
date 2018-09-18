package com.example.vishambar.webserviceusingretrofit.advanced.utils;

import com.example.vishambar.webserviceusingretrofit.advanced.models.ItemModel;
import com.example.vishambar.webserviceusingretrofit.advanced.models.ModifyItemInputModel;
import com.example.vishambar.webserviceusingretrofit.advanced.models.TokenModel;
import com.example.vishambar.webserviceusingretrofit.advanced.models.RegisterModel;
import com.example.vishambar.webserviceusingretrofit.advanced.models.SignoutModel;
import com.example.vishambar.webserviceusingretrofit.advanced.models.ToDoItem;
import com.example.vishambar.webserviceusingretrofit.advanced.models.UserModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIInterface {
    @POST(Constants.register)
    Call<UserModel> register(@Body RegisterModel registerModel);

    @POST(Constants.login)
    Call<TokenModel> login(@Body UserModel userModel);


    @POST(Constants.logout)
    Call<SignoutModel> logout(@Body SignoutModel signoutModel);

    @POST(Constants.addToDoItem)
    Call<ItemModel> addToDoItem(@Header(value = "token") String token, @Body ItemModel itemModel);


    @GET(Constants.addToDoItem + "{authorEmailId}/")
    Call<List<ItemModel>> getItems(@Path(value = "authorEmailId") String email, @Header(value = "token") String token);

    @PUT(Constants.modifyToDoUrl)
    Call<ItemModel> modifyToDoItem(@Header(value = "token") String token, @Body ModifyItemInputModel modifyItemInputModel);

    @HTTP(method = "DELETE", path = Constants.deleteToDo, hasBody = true)
    Call<ResponseBody> deleteItem(@Header(value = "token") String token, @Body ItemModel itemModel);
//


}
