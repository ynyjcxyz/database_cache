package com.example.android.databasecachefromjson.data_model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Assets.class}, version = 2, exportSchema = false)
public abstract class NftDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "NFT_db";
    private static volatile NftDatabase INSTANCE;
    public abstract NftDao nftDao();

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static NftDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (NftDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(),
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
