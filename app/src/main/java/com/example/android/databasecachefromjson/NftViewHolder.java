package com.example.android.databasecachefromjson;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.databasecachefromjson.data.NftContract;

public class NftViewHolder extends RecyclerView.ViewHolder {

    LinearLayout parent_layout;
    ImageView imageView;
    TextView name, token_id, permalink;

    public NftViewHolder(@NonNull View itemView) {
        super(itemView);
        parent_layout = itemView.findViewById(R.id.parent_layout);
        imageView = itemView.findViewById(R.id.imageView);
        name = itemView.findViewById(R.id.name);
        token_id = itemView.findViewById(R.id.token_id);
        permalink = itemView.findViewById(R.id.permalink);
    }

    public static void bind(@NonNull NftViewHolder holder, Cursor movedCursor, Context context) {
        int token_id = movedCursor.getColumnIndex(NftContract.NftEntry.COLUMN_NFT_TOKEN_ID);
        int permalink = movedCursor.getColumnIndex(NftContract.NftEntry.COLUMN_NFT_PERMALINK);
        int name = movedCursor.getColumnIndex(NftContract.NftEntry.COLUMN_NFT_NAME);
        int img_url = movedCursor.getColumnIndex(NftContract.NftEntry.COLUMN_NFT_IMG_URL);
        holder.token_id.setText(movedCursor.getString(token_id));
        holder.permalink.setText(movedCursor.getString(permalink));
        holder.name.setText(movedCursor.getString(name));
        Glide.with(context).load(movedCursor.getString(img_url)).into(holder.imageView);
    }
}
