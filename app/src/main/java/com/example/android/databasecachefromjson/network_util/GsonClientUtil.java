package com.example.android.databasecachefromjson.network_util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

public class GsonClientUtil {
    public static Gson createGson(){
        return new GsonBuilder()
                .registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY)
                .create();
    }
}
