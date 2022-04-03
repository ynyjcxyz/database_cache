package com.example.android.databasecachefromjson.data_model;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.List;

@GenerateTypeAdapter
@AutoValue
public abstract class Dto {
    @SerializedName("next")
    public abstract String next();

    @SerializedName("previous")
    public abstract String previous();

    @SerializedName("assets")
    public abstract List<Asset> assets();
}