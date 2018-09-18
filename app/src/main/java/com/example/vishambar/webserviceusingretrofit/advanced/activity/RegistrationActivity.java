package com.example.vishambar.webserviceusingretrofit.advanced.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vishambar.webserviceusingretrofit.BaseActivity;
import com.example.vishambar.webserviceusingretrofit.R;
import com.example.vishambar.webserviceusingretrofit.advanced.MyApplication;
import com.example.vishambar.webserviceusingretrofit.advanced.models.RegisterModel;
import com.example.vishambar.webserviceusingretrofit.advanced.models.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = RegistrationActivity.class.getSimpleName();
    private EditText nameEt, emailIdEt, passwordEt;
    private Button registerBtn,loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_main);
    }

    @Override
    public void getIds() {
        nameEt = findViewById(R.id.et_name);
        emailIdEt = findViewById(R.id.et_email);
        passwordEt = findViewById(R.id.et_password);
        registerBtn = findViewById(R.id.btn_register);
        loginBtn=findViewById(R.id.btn_login);
    }

    @Override
    public void setData() {

    }

    @Override
    public void setListeners() {
        registerBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    private void register() {
        validateRegistration();
        RegisterModel registerModel = new RegisterModel(nameEt.getText().toString(), emailIdEt.getText().toString(), passwordEt.getText().toString());
        MyApplication.apiServiceProvider.getRegisterApi(registerModel).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel userModel = response.body();
                MyApplication.setUser(userModel);
                Log.d(TAG, userModel.toString());
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.d(TAG, call.toString());
            }
        });
    }

    private boolean validateRegistration() {
        if (nameEt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (emailIdEt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (passwordEt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                register();
                break;
            case R.id.btn_login:
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                break;
        }
    }
}
