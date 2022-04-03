package com.example.android.databasecachefromjson;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
}
