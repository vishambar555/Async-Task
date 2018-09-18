package com.example.vishambar.webserviceusingretrofit.advanced.models;

import android.content.ClipData;

public class ModifyItemInputModel {
    private ItemModel currentToDoItem;
    private ItemModel proposedToDoItem;

    public ModifyItemInputModel(ItemModel currentToDoItem, ItemModel proposedToDoItem) {
        this.currentToDoItem = currentToDoItem;
        this.proposedToDoItem = proposedToDoItem;
    }

    public ItemModel getCurrentToDoItem() {
        return currentToDoItem;
    }

    public void setCurrentToDoItem(ItemModel currentToDoItem) {
        this.currentToDoItem = currentToDoItem;
    }

    public ItemModel getProposedToDoItem() {
        return proposedToDoItem;
    }

    public void setProposedToDoItem(ItemModel proposedToDoItem) {
        this.proposedToDoItem = proposedToDoItem;
    }
}

