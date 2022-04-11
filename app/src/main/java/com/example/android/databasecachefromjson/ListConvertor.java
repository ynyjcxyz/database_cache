package com.example.android.databasecachefromjson;

import com.example.android.databasecachefromjson.data_model.Assets;
import com.example.android.databasecachefromjson.data_model.AssetsBean;
import java.util.List;
import java.util.stream.Collectors;

public class ListConvertor {
    List<Assets> convertor(List<AssetsBean> rawDataList){
        return rawDataList
                .stream()
                .map(assetsBean ->
                        new Assets(assetsBean.id(),
                                assetsBean.token_id(),
                                assetsBean.permalink(),
                                assetsBean.name(),
                                assetsBean.image_url()))
                .collect(Collectors.toList());
    }
}