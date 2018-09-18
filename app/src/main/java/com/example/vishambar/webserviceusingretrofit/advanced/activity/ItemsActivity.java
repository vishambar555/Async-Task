package com.example.vishambar.webserviceusingretrofit.advanced.activity;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.vishambar.webserviceusingretrofit.advanced.models.ItemsModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemsActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = ItemsActivity.class.getClass().getSimpleName();
    private RecyclerView recyclerView;
    private Button addItemBtn,getItemsBtn;
    private EditText itemNameEt, placeEt;
    private ItemsAdapter itemsAdapter;
    private List<ItemsModel> mItemsList;

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
        getItemsBtn=findViewById(R.id.btn_get_items);
        recyclerView = findViewById(R.id.recycler_view);
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
        MyApplication.apiServiceProvider.getItemsApi().enqueue(new Callback<List<ItemsModel>>() {
            @Override
            public void onResponse(Call<List<ItemsModel>> call, Response<List<ItemsModel>> response) {
                mItemsList = response.body();
                if (mItemsList != null && !mItemsList.isEmpty()) {
                    Log.d(TAG, mItemsList.toString());

                    setAdapter();
                }
            }

            @Override
            public void onFailure(Call<List<ItemsModel>> call, Throwable t) {
                Log.d(TAG, "failed fetching items in" + ItemsActivity.class.getClass().getSimpleName());
            }
        });
    }

    private void setAdapter() {
        itemsAdapter = new ItemsAdapter(this, mItemsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemsAdapter);
    }

    @Override
    public void setListeners() {
        addItemBtn.setOnClickListener(this);
        getItemsBtn.setOnClickListener(this);
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
        }
    }

    private void addItem() {
        final ItemsModel itemsModel = new ItemsModel(MyApplication.getUser().getAuthorId(), itemNameEt.getText().toString(), MyApplication.getUser().getAuthorEmailId(), placeEt.getText().toString());
        MyApplication.apiServiceProvider.addItem(itemsModel).enqueue(new Callback<ItemsModel>() {
            @Override
            public void onResponse(Call<ItemsModel> call, Response<ItemsModel> response) {
                ItemsModel item = response.body();
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
            public void onFailure(Call<ItemsModel> call, Throwable t) {

            }
        });
    }
}
