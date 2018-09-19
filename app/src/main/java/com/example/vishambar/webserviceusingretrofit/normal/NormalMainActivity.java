package com.example.vishambar.webserviceusingretrofit.normal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.vishambar.webserviceusingretrofit.R;
import com.example.vishambar.webserviceusingretrofit.advanced.utils.RequestInterface;
import com.example.vishambar.webserviceusingretrofit.advanced.utils.Constants;
import com.example.vishambar.webserviceusingretrofit.advanced.models.RegisterModel;
import com.example.vishambar.webserviceusingretrofit.advanced.models.UserModel;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NormalMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void hitApi(View view) {
        RegisterModel registerModel =  new RegisterModel("gkgg","hjhk@hbh.com","ygyghgj");

        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient= new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();


        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

         RequestInterface requestInterface =retrofit.create(RequestInterface.class);

        Call<UserModel> userModel = requestInterface.register(registerModel);

        userModel.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                String str=response.body().toString();
                System.out.print("hkf");
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                System.out.print("hkf");
            }
        });
    }
}
