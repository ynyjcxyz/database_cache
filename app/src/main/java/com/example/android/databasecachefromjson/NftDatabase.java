package com.example.android.databasecachefromjson;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {NftModel.class}, version = 1)
public abstract class NftDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "NFT_db";
    public abstract NftDao nftDao();
    private static NftDatabase INSTANCE;
    public NftDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (NftDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(
                                    context.getApplicationContext(),
                                    NftDatabase.class,
                                    DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
