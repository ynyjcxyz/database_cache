package com.example.android.databasecachefromjson.data_model;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

@GenerateTypeAdapter
@AutoValue
public abstract class Asset {
    @SerializedName("token_id")
    public abstract String token_id();

    @SerializedName("permalink")
    public abstract String permalink();

    @SerializedName("name")
    public abstract String name();

    @SerializedName("image_url")
    public abstract String image_url();
}
