package com.example.android.databasecachefromjson.data_model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "nft_table",indices = @Index(value = {"id"},unique = true))
public class NftModel {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @ColumnInfo(name = "id")
    String id;

    @SerializedName("token_id")
    @ColumnInfo(name = "token_id")
    String token_id;

    @SerializedName("permalink")
    @ColumnInfo(name = "permalink")
    String permalink;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    String name;

    @SerializedName("image_url")
    @ColumnInfo(name = "image_url")
    String image_url;

    @NonNull
    public String getId() { return id; }
    @NonNull
    public String getToken_id() {
        return token_id;
    }
    @NonNull
    public String getPermalink() {
        return permalink;
    }
    @NonNull
    public String getName() {
        return name;
    }
    @NonNull
    public String getImage_url() {
        return image_url;
    }

    public NftModel(String token_id,String permalink,String name,String image_url){
        this.token_id = token_id;
        this.permalink = permalink;
        this.name = name;
        this.image_url = image_url;
    }
}
