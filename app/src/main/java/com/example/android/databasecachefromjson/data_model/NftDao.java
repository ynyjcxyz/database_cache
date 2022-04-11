package com.example.android.databasecachefromjson.data_model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

import io.reactivex.Observable;

@Dao
public interface NftDao {
    @Query("SELECT * FROM assets")
    Observable<List<Assets>> getAllAssets();

    @Query("DELETE FROM assets")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Assets> assets);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAsset(Assets asset);
}
