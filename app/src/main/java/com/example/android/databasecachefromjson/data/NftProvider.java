package com.example.android.databasecachefromjson.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class NftProvider extends ContentProvider {
    public static final String LOG_TAG = NftProvider.class.getSimpleName();
    private NftDbHelper nftDbHelper;
    private static final int NFTS = 100;
    private static final int NFT_ID = 101;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static{
        sUriMatcher.addURI(NftContract.CONTENT_AUTHORITY, NftContract.PATH_NFTS, NFTS);
        sUriMatcher.addURI(NftContract.CONTENT_AUTHORITY, NftContract.PATH_NFTS + "/#", NFT_ID);
    }

    @Override
    public boolean onCreate() {
        nftDbHelper = new NftDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri,
                        String[] projection,
                        String selection,
                        String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase database = nftDbHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match){
            case NFTS:
                cursor = database.query(NftContract.NftEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case NFT_ID:
                selection = NftContract.NftEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                cursor = database.query(NftContract.NftEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        if(match == NFTS){
            return insertNft(uri,contentValues);
        }
        throw new IllegalArgumentException("Insertion is not supported for " + uri);
    }

    private Uri insertNft(Uri uri, ContentValues contentValues) {
        String token_id = contentValues.getAsString(NftContract.NftEntry.COLUMN_NFT_TOKEN_ID);
        if (token_id == null) {
            throw new IllegalArgumentException("TOKEN_ID is null");
        }
        String name = contentValues.getAsString(NftContract.NftEntry.COLUMN_NFT_NAME);
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }
        SQLiteDatabase database = nftDbHelper.getWritableDatabase();

        long id = database.insert(NftContract.NftEntry.TABLE_NAME, null, contentValues);

        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri,
                      String selection,
                      String[] selectionArgs) {
        SQLiteDatabase database = nftDbHelper.getWritableDatabase();
        int rowsDeleted;
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case NFTS:
                rowsDeleted = database.delete(NftContract.NftEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case NFT_ID:
                selection = NftContract.NftEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                rowsDeleted = database.delete(NftContract.NftEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
        if (rowsDeleted!=0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri,
                      ContentValues contentValues,
                      String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case NFTS:
                return updateNft(uri, contentValues, selection, selectionArgs);
            case NFT_ID:
                selection = NftContract.NftEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updateNft(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int updateNft(Uri uri,
                          ContentValues contentValues,
                          String selection,
                          String[] selectionArgs) {
        if (contentValues.containsKey(NftContract.NftEntry.COLUMN_NFT_TOKEN_ID)) {
            String token_id = contentValues.getAsString(NftContract.NftEntry.COLUMN_NFT_TOKEN_ID);
            if (token_id == null) {
                throw new IllegalArgumentException("token_id is null");
            }
        }

        if (contentValues.containsKey(NftContract.NftEntry.COLUMN_NFT_NAME)) {
            String name = contentValues.getAsString(NftContract.NftEntry.COLUMN_NFT_NAME);
            if (name == null) {
                throw new IllegalArgumentException("name is null");
            }
        }

        SQLiteDatabase database = nftDbHelper.getWritableDatabase();
        int rowsUpdated = database.update(NftContract.NftEntry.TABLE_NAME, contentValues, selection, selectionArgs);
        if (rowsUpdated!=0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }
}
