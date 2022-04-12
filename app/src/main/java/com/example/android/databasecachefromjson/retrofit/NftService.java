package com.example.android.databasecachefromjson.retrofit;

import com.example.android.databasecachefromjson.data_model.Dto;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

//https://api.opensea.io/api/v1/assets?format=json
//String BASE_URL = "https://api.opensea.io/api/v1/";
//String format = "json";
public interface NftService {
    @GET("v3/{uuid}")
    Observable<Dto> dtoRepos(@Path("uuid") String parameterUuid);
}
