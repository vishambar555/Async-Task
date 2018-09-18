package com.example.vishambar.webserviceusingretrofit.advanced.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vishambar.webserviceusingretrofit.BaseActivity;
import com.example.vishambar.webserviceusingretrofit.R;
import com.example.vishambar.webserviceusingretrofit.advanced.MyApplication;
import com.example.vishambar.webserviceusingretrofit.advanced.adapters.ItemsAdapter;
import com.example.vishambar.webserviceusingretrofit.advanced.models.ItemModel;
import com.example.vishambar.webserviceusingretrofit.advanced.models.UserModel;
import com.example.vishambar.webserviceusingretrofit.advanced.utils.ItemClickInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemsActivity extends BaseActivity implements View.OnClickListener, ItemClickInterface {
    private static final String TAG = ItemsActivity.class.getClass().getSimpleName();
    private RecyclerView recyclerView;
    private Button addItemBtn, getItemsBtn, logoutBtn;
    private EditText itemNameEt, placeEt;
    private ItemsAdapter itemsAdapter;
    private List<ItemModel> mItemsList;
    private int clickedItemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
    }

    @Override
    public void getIds() {
        itemNameEt = findViewById(R.id.et_item_name);
        placeEt = findViewById(R.id.et_place);
        addItemBtn = findViewById(R.id.btn_add_items);
        getItemsBtn = findViewById(R.id.btn_get_items);
        recyclerView = findViewById(R.id.recycler_view);
        logoutBtn = findViewById(R.id.btn_logout);
    }

    @Override
    public void setData() {
        getItems();
    }

    private boolean validate() {
        if (itemNameEt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter item name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (placeEt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter place name", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void getItems() {
        MyApplication.apiServiceProvider.getItemsApi().enqueue(new Callback<List<ItemModel>>() {
            @Override
            public void onResponse(Call<List<ItemModel>> call, Response<List<ItemModel>> response) {
                mItemsList = response.body();
                if (mItemsList != null && !mItemsList.isEmpty()) {
                    Log.d(TAG, mItemsList.toString());

                    setAdapter();
                }
            }

            @Override
            public void onFailure(Call<List<ItemModel>> call, Throwable t) {
                Log.d(TAG, "failed fetching items in" + ItemsActivity.class.getClass().getSimpleName());
            }
        });
    }

    private void setAdapter() {
        itemsAdapter = new ItemsAdapter(this, this, mItemsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemsAdapter);
    }

    @Override
    public void setListeners() {
        addItemBtn.setOnClickListener(this);
        getItemsBtn.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_items:
                if (validate()) {
                    addItem();
                }
                break;

            case R.id.btn_get_items:
                getItems();
                break;

            case R.id.btn_logout:
                logout();
                break;

        }
    }

    private void logout() {
        UserModel userModel = MyApplication.getUser();
        userModel.setAuthorPassword("");
        MyApplication.apiServiceProvider.logout(userModel).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                startActivity(new Intent(ItemsActivity.this, LoginActivity.class));
                MyApplication.setLogin(false);
                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    private void addItem() {
        final ItemModel itemModel = new ItemModel(MyApplication.getUser().getAuthorId(), itemNameEt.getText().toString(), MyApplication.getUser().getAuthorEmailId(), placeEt.getText().toString(), new Date().getTime());
        MyApplication.apiServiceProvider.addItem(itemModel).enqueue(new Callback<ItemModel>() {
            @Override
            public void onResponse(Call<ItemModel> call, Response<ItemModel> response) {
                ItemModel item = response.body();
                if (mItemsList == null || mItemsList.isEmpty()) {
                    mItemsList = new ArrayList<>();
                    mItemsList.add(item);
                    setAdapter();
                } else {
                    mItemsList.add(item);
                    itemsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ItemModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void handleClick(int position) {
        clickedItemPosition = position;
        Intent intent = new Intent(this, ItemDetailsActivty.class);
        Bundle bundle = new Bundle();

        bundle.putParcelable("ParcelableObject", mItemsList.get(position));
        intent.putExtras(bundle);
        startActivityForResult(intent, 25);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (data != null && clickedItemPosition != -1) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                ItemModel itemModel = bundle.getParcelable("new_item");
                mItemsList.set(clickedItemPosition, itemModel);
            } else {
                mItemsList.remove(clickedItemPosition);
            }

            itemsAdapter.notifyDataSetChanged();
        }

    }

}
