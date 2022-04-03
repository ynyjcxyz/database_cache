package com.example.android.databasecachefromjson.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NftDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = SQLiteOpenHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "nft.db";
    private static final int DATABASE_VERSION = 1;

    public NftDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_PETS_TABLE =
                "CREATE TABLE " + NftContract.NftEntry.TABLE_NAME +
                        "(" +
                        NftContract.NftEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        NftContract.NftEntry.COLUMN_NFT_TOKEN_ID + " TEXT NOT NULL, " +
                        NftContract.NftEntry.COLUMN_NFT_PERMALINK + " TEXT, " +
                        NftContract.NftEntry.COLUMN_NFT_NAME + " TEXT NOT NULL, " +
                        NftContract.NftEntry.COLUMN_NFT_IMG_URL + " TEXT" +
                        ")";
        sqLiteDatabase.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
}
