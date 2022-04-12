package com.example.android.databasecachefromjson;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.databasecachefromjson.data_model.Assets;
import java.util.List;

public class NftListAdapter extends RecyclerView.Adapter<NftViewHolder> {
    private List<Assets> nftList;

    public NftListAdapter(List<Assets> nftList) {
        this.nftList = nftList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Assets> dataList) {
        nftList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NftViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NftViewHolder holder, int position) {
        holder.bindData(nftList.get(position));
    }

    @Override
    public int getItemCount() {
        if (nftList != null) {
            return nftList.size();
        } else {
            return 0;
        }
    }
}
