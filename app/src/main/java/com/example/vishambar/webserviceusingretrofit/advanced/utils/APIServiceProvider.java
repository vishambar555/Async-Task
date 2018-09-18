package com.example.vishambar.webserviceusingretrofit.advanced.utils;

import com.example.vishambar.webserviceusingretrofit.advanced.MyApplication;
import com.example.vishambar.webserviceusingretrofit.advanced.models.ItemModel;
import com.example.vishambar.webserviceusingretrofit.advanced.models.ModifyItemInputModel;
import com.example.vishambar.webserviceusingretrofit.advanced.models.TokenModel;
import com.example.vishambar.webserviceusingretrofit.advanced.models.RegisterModel;
import com.example.vishambar.webserviceusingretrofit.advanced.models.UserModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIServiceProvider {
    public static APIServiceProvider apiServiceProvider;
    private HttpLoggingInterceptor httpLoggingInterceptor;
    private OkHttpClient okHttpClient;
    private APIInterface apiInterface;

    private APIServiceProvider(String baseUrl, long readTimeout, long connectTimeout, HttpLoggingInterceptor.Level logLevel) {
        httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addNetworkInterceptor(httpLoggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();


        apiInterface = retrofit.create(APIInterface.class);

    }

    public static APIServiceProvider getAPIServiceProvider(String baseUrl, long readTimeout, long connectTimeout, HttpLoggingInterceptor.Level logLevel) {
        if (apiServiceProvider == null) {
            apiServiceProvider = new APIServiceProvider(baseUrl, readTimeout, connectTimeout, logLevel);
        }

        return apiServiceProvider;

    }

    public Call<UserModel> getRegisterApi(RegisterModel registerModel) {
        return apiInterface.register(registerModel);
    }

    public Call<TokenModel> getLoginApi(UserModel userModel) {
        return apiInterface.login(userModel);
    }

    public Call<List<ItemModel>> getItemsApi() {
        String emailId = MyApplication.getUser().getAuthorEmailId();
        String token = MyApplication.getToken();
        return apiInterface.getItems(MyApplication.getUser().getAuthorEmailId(), MyApplication.getToken());
    }


    public Call<ItemModel> addItem(ItemModel itemModel) {
        return apiInterface.addToDoItem(MyApplication.getToken(), itemModel);
    }

    public Call<ItemModel> modifyItem(ModifyItemInputModel modifyItemInputModel) {
        String token=MyApplication.getToken();
        return apiInterface.modifyToDoItem(MyApplication.getToken(),modifyItemInputModel);
    }

    public Call<ResponseBody> deleteItem(ItemModel itemModel) {
        String token=MyApplication.getToken();
        return  apiInterface.deleteItem(MyApplication.getToken(),itemModel);
    }
}
