package com.example.android.databasecachefromjson.retrofit;

import static com.example.android.databasecachefromjson.network_util.GsonClientUtil.createGson;
import static com.example.android.databasecachefromjson.network_util.OkHttpClientUtil.buildOkHttpClient;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetRetrofitObject {
    public static NftService retrofitService() {
        return new Retrofit
                .Builder()
                .baseUrl("https://run.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(buildOkHttpClient())
                .build()
                .create(NftService.class);
    }
}
//                .create(NftService.class)
//                .dtoRepos(String format)
//                .execute()
//                .body();