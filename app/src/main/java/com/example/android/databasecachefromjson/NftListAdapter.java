package com.example.android.databasecachefromjson;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NftListAdapter extends RecyclerView.Adapter<NftViewHolder>{
    private static final String TAG = NftListAdapter.class.getSimpleName();
    private Cursor mCursor;
    private final Context mContext;
    private final LayoutInflater mInflater;

    public NftListAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(Cursor cursor) {
        mCursor = cursor;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NftViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NftViewHolder holder, int position) {
        if (mCursor != null) {
            if (mCursor.moveToPosition(position)) {
                NftViewHolder.bind(holder, this.mCursor, mContext);
                Log.e(NftListAdapter.TAG, "onBindViewHolder: is on point");
            } else {
                Log.e(NftListAdapter.TAG, "onBindViewHolder: Cursor is null.");
            }
        } else {
            Log.e(NftListAdapter.TAG, "onBindViewHolder: Cursor is null.");
        }
    }

    @Override
    public int getItemCount() {
        if (mCursor != null) {
            return mCursor.getCount();
        } else {
            return -1;
        }
    }
}
