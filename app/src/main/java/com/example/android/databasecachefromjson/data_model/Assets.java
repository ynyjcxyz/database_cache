package com.example.android.databasecachefromjson.data_model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "assets")
public class Assets{
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private String id;

    @SerializedName("token_id")
    String token_id;

    @SerializedName("permalink")
    String permalink;

    @SerializedName("name")
    String name;

    @SerializedName("image_url")
    String image_url;

    public Assets(@NonNull String id, String token_id, String permalink, String name, String image_url){
        this.id = id;
        this.token_id = token_id;
        this.permalink = permalink;
        this.name = name;
        this.image_url = image_url;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setToken_id(String token_id) {
        this.token_id = token_id;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

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

    @Override
    public boolean equals(@Nullable Object newItem) {
        if(newItem instanceof Assets) {
            Assets temp = (Assets) newItem;
            return this.id.equals(temp.id) &&
                    this.token_id.equals(temp.token_id) &&
                    this.permalink.equals(temp.permalink) &&
                    this.name.equals(temp.name) &&
                    this.image_url.equals(temp.image_url);
        } else {
            return false;
        }
    }
}
