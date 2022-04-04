package com.example.android.databasecachefromjson;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.os.RemoteException;

import com.example.android.databasecachefromjson.data.NftContract;
import com.example.android.databasecachefromjson.data_model.Asset;

import java.util.ArrayList;
import java.util.List;

public class StaticDataHolder {
    public static String[] projection = {
            NftContract.NftEntry._ID,
            NftContract.NftEntry.COLUMN_NFT_TOKEN_ID,
            NftContract.NftEntry.COLUMN_NFT_PERMALINK,
            NftContract.NftEntry.COLUMN_NFT_NAME,
            NftContract.NftEntry.COLUMN_NFT_IMG_URL
    };

    public static List<Asset> removeNull(List<Asset> input) {
        //remove null from list
        List<Asset> outputList = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            Asset current = input.get(i);
            String token_id = current.token_id();
            String permalink = current.permalink();
            if(token_id != null && permalink != null) {
                outputList.add(current);
            }
        }
        return outputList;
    }

    static void insertDataToDatabase(List<Asset> listFromRetrofit, ContentResolver contentResolver) {
        ArrayList<ContentProviderOperation> batch = new ArrayList<>();
        for (int i = 0; i < listFromRetrofit.size(); i++) {
            Asset asset = listFromRetrofit.get(i);
            ContentValues values = new ContentValues();
            values.put(NftContract.NftEntry.COLUMN_NFT_TOKEN_ID, asset.token_id());
            values.put(NftContract.NftEntry.COLUMN_NFT_PERMALINK, asset.permalink());
            values.put(NftContract.NftEntry.COLUMN_NFT_NAME, asset.name());
            values.put(NftContract.NftEntry.COLUMN_NFT_IMG_URL, asset.image_url());
            batch.add(ContentProviderOperation
                    .newInsert(NftContract.NftEntry.CONTENT_URI)
                    .withValues(values)
                    .build());
        }
        try {
            contentResolver.applyBatch(NftContract.CONTENT_AUTHORITY, batch);
        } catch (OperationApplicationException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
