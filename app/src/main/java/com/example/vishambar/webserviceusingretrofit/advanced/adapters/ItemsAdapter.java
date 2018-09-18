package com.example.vishambar.webserviceusingretrofit.advanced.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vishambar.webserviceusingretrofit.R;
import com.example.vishambar.webserviceusingretrofit.advanced.models.ItemModel;
import com.example.vishambar.webserviceusingretrofit.advanced.utils.ItemClickInterface;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater layoutInflater;
    private ArrayList<ItemModel> itemsArrayList;
    private ItemClickInterface itemClickInterface;

    public ItemsAdapter(Context context, ItemClickInterface itemClickInterface, List<ItemModel> itemslList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.itemsArrayList = (ArrayList<ItemModel>) itemslList;
        this.itemClickInterface = itemClickInterface;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(layoutInflater.inflate(R.layout.item_recycler_view, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.itemTv.setText(itemsArrayList.get(position).getTodoString());
        myViewHolder.itemTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickInterface.handleClick(position);
            }
        });
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
