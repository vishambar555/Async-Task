package com.example.vishambar.webserviceusingretrofit.advanced;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.vishambar.webserviceusingretrofit.advanced.models.UserModel;
import com.example.vishambar.webserviceusingretrofit.advanced.utils.APIServiceProvider;
import com.example.vishambar.webserviceusingretrofit.advanced.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.logging.HttpLoggingInterceptor;

public class MyApplication extends Application {
    private static final String FILE_NAME = "retrofit_file";
    private static final String USER = "USER";
    private static final String TOKEN = "token";
    private static final String IS_LOGIN = "is_login";
    public static APIServiceProvider apiServiceProvider;
    public static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        apiServiceProvider = APIServiceProvider.getAPIServiceProvider(Constants.baseUrl, 5, 10, HttpLoggingInterceptor.Level.BODY);
        sharedPreferences = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public static void setLogin(boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_LOGIN, value);
        editor.commit();
    }

    public static boolean isLogin() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public static void setUser(UserModel userModel) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER, new Gson().toJson(userModel).toString());
        editor.commit();
    }

    public static UserModel getUser() {
        Type type = new TypeToken<UserModel>() {
        }.getType();

        String jsonInString = sharedPreferences.getString(USER, "");
        UserModel userModel = new Gson().fromJson(jsonInString, type);
        return userModel;
    }

    public static void setToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN, token);
        editor.commit();
    }

    public static String getToken() {
        return sharedPreferences.getString(TOKEN, "");
    }
}
