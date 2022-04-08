package com.example.android.databasecachefromjson;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.android.databasecachefromjson.data_model.NftModel;

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

    public void bindData(NftModel currentNft) {
        token_id.setText(currentNft.getToken_id());
        name.setText(currentNft.getName());
        permalink.setText(currentNft.getPermalink());
        Glide.with(itemView.getContext()).load(currentNft.getImage_url()).into(imageView);
    }
}
