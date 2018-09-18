package com.example.vishambar.webserviceusingretrofit.advanced.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vishambar.webserviceusingretrofit.R;
import com.example.vishambar.webserviceusingretrofit.advanced.models.ItemsModel;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater layoutInflater;
    private ArrayList<ItemsModel> itemsArrayList;

    public ItemsAdapter(Context context, List<ItemsModel> itemslList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.itemsArrayList = (ArrayList<ItemsModel>) itemslList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(layoutInflater.inflate(R.layout.item_recycler_view, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.itemTv.setText(itemsArrayList.get(position).getTodoString());
    }

    @Override
    public int getItemCount() {
        return itemsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView itemTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemTv = itemView.findViewById(R.id.tv_item);
        }
    }
}
