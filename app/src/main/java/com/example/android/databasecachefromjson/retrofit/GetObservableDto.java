package com.example.android.databasecachefromjson.retrofit;

import static com.example.android.databasecachefromjson.retrofit.GetRetrofitObject.retrofitService;

import com.example.android.databasecachefromjson.data_model.Dto;
import io.reactivex.Observable;

public class GetObservableDto {
    public static Observable<Dto> getDto(String parameterUuid) {
        return retrofitService().dtoRepos(parameterUuid);
    }
}
