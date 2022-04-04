package com.example.android.databasecachefromjson;

import com.example.android.databasecachefromjson.data_model.Dto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

//https://api.opensea.io/api/v1/assets?format=json
//String BASE_URL = "https://api.opensea.io/api/v1/";
//String format = "json";
public interface NftService {
    @GET("v3/{uuid}")
    Call<Dto> dtoRepos(@Path("uuid") String parameterUuid);
}
