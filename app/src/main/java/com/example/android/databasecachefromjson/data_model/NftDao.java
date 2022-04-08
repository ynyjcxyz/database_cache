package com.example.android.databasecachefromjson.data_model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.android.databasecachefromjson.data_model.NftModel;

import java.util.List;

@Dao
public interface NftDao {
    @Query("SELECT * FROM nft_table")
    List<NftModel> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<NftModel> nft);

    @Query("DELETE FROM nft_table")
    void deleteAll();
}