package com.example.vishambar.webserviceusingretrofit.advanced.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vishambar.webserviceusingretrofit.BaseActivity;
import com.example.vishambar.webserviceusingretrofit.R;
import com.example.vishambar.webserviceusingretrofit.advanced.MyApplication;
import com.example.vishambar.webserviceusingretrofit.advanced.models.TokenModel;
import com.example.vishambar.webserviceusingretrofit.advanced.models.UserModel;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = RegistrationActivity.class.getSimpleName();
    private EditText nameEt, emailIdEt, passwordEt;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void getIds() {
        nameEt = findViewById(R.id.et_name);
        emailIdEt = findViewById(R.id.et_email);
        passwordEt = findViewById(R.id.et_password);
        loginBtn = findViewById(R.id.btn_login);
    }

    @Override
    public void setData() {

    }

    @Override
    public void setListeners() {
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                UserModel userModel = new UserModel(nameEt.getText().toString(), emailIdEt.getText().toString(), passwordEt.getText().toString(), MyApplication.getUser().getAuthorId());
                String json = new Gson().toJson(userModel);
                MyApplication.apiServiceProvider.getLoginApi(userModel).enqueue(new Callback<TokenModel>() {
                    @Override
                    public void onResponse(Call<TokenModel> call, Response<TokenModel> response) {
                        TokenModel tokenModel = response.body();
                        MyApplication.setToken(tokenModel.getToken());
                        System.out.print(tokenModel);
                        MyApplication.setLogin(true);
                    }

                    @Override
                    public void onFailure(Call<TokenModel> call, Throwable t) {

                    }
                });
                break;
        }
    }
}
