package com.muzima.view.forms;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.muzima.listeners.EmptyListListener;

public abstract class FormsListAdapter<T> extends ArrayAdapter<T>{
    private EmptyListListener emptyListListener;

    public FormsListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public void setEmptyListListener(EmptyListListener emptyListListener) {
        this.emptyListListener = emptyListListener;
    }

    public void notifyEmptyDataListener(boolean isEmpty) {
        if(emptyListListener != null){
            emptyListListener.listIsEmpty(isEmpty);
        }
    }

    public abstract void reloadData();
}
