package com.example.android.databasecachefromjson;

import com.example.android.databasecachefromjson.data_model.Dto;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
//https://run.mocky.io/v3/5a730ed8-3e23-4b4c-b13a-b5d8c1ece39e
//https://api.opensea.io/api/v1/assets?format=json
//String BASE_URL = "https://api.opensea.io/api/v1/";
//String format = "json";
public interface NftService {
    @GET("5a730ed8-3e23-4b4c-b13a-b5d8c1ece39e")
    Single<Response<Dto>> rxjava2(@Query("format") String format);

    @GET("5a730ed8-3e23-4b4c-b13a-b5d8c1ece39e")
    Call<Dto> dtoRepos(@Query("format") String format);
}
