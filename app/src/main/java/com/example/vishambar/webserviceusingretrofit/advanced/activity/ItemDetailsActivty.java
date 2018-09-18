package com.example.vishambar.webserviceusingretrofit.advanced.activity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vishambar.webserviceusingretrofit.BaseActivity;
import com.example.vishambar.webserviceusingretrofit.R;
import com.example.vishambar.webserviceusingretrofit.advanced.MyApplication;
import com.example.vishambar.webserviceusingretrofit.advanced.models.ItemModel;
import com.example.vishambar.webserviceusingretrofit.advanced.models.ModifyItemInputModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailsActivty extends BaseActivity implements View.OnClickListener {
    private TextView itemNameTv, placeTv;
    private Button addItemBtn;
    private ItemModel itemModel;
    private LinearLayout ll;
    private EditText itemNameEt, placeEt;
    private ItemModel newItemModel;
    private Button deleteBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details_activity);
    }

    @Override
    public void getIds() {
        ll = findViewById(R.id.ll);
        itemNameEt = findViewById(R.id.et_item_name);
        placeEt = findViewById(R.id.et_place);
        deleteBtn = findViewById(R.id.btn_delete_item);

        itemNameTv = findViewById(R.id.tv_item_name);
        placeTv = findViewById(R.id.tv_place);
        addItemBtn = findViewById(R.id.btn_modify_item);
    }

    @Override
    public void setData() {
        Bundle bundle = getIntent().getExtras();
        itemModel = bundle.getParcelable("ParcelableObject");
        if (itemModel != null) {
            itemNameTv.setText(itemModel.getTodoString());
            placeTv.setText(itemModel.getPlace());
        }
        ll.setVisibility(View.GONE);
    }

    @Override
    public void setListeners() {
        addItemBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_modify_item:
                ll.setVisibility(View.VISIBLE);
                if (validate()) {
                    modifyItem();
                }
                break;
            case R.id.btn_delete_item:
                deleteItem();
                break;
        }
    }

    private void deleteItem() {
        String string=new Gson().toJson(itemModel);
        MyApplication.apiServiceProvider.deleteItem(itemModel).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody fd = response.body();
                Intent intent = new Intent();
                setResult(25, intent);
                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.print("");
            }
        });
    }

    private void modifyItem() {
        ItemModel itemModel2 = new ItemModel(itemModel.getId(), itemNameEt.getText().toString(), itemModel.getAuthorEmailId(), placeEt.getText().toString(), new Date().getTime());
        itemModel2.setDate((new Date().getTime()));

        ModifyItemInputModel modifyItemInputModel = new ModifyItemInputModel(itemModel, itemModel2);
        String jsonString = new Gson().toJson(modifyItemInputModel);
        MyApplication.apiServiceProvider.modifyItem(modifyItemInputModel).enqueue(new Callback<ItemModel>() {
            @Override
            public void onResponse(Call<ItemModel> call, Response<ItemModel> response) {
                newItemModel = response.body();
                System.out.print("hi");
                if (newItemModel != null) {
                    itemNameTv.setText(newItemModel.getTodoString());
                    placeTv.setText(newItemModel.getPlace());
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("new_item", newItemModel);
                    intent.putExtras(bundle);
                    setResult(25, intent);
                    finish();
                }

            }

            @Override
            public void onFailure(Call<ItemModel> call, Throwable t) {

            }
        });

    }

    private boolean validate() {
        if (itemNameEt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter item name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (placeEt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter place", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

}
