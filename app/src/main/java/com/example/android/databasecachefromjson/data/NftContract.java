package com.example.android.databasecachefromjson.data;

import android.net.Uri;
import android.provider.BaseColumns;

public final class NftContract {
    public final static String CONTENT_AUTHORITY = "com.example.android.databasecachefromjson";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_NFTS = "nfts";

    private NftContract() {}

    public static final class NftEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NFTS);
        public final static String TABLE_NAME = "nfts";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NFT_TOKEN_ID = "token_id";
        public final static String COLUMN_NFT_PERMALINK = "permalink";
        public final static String COLUMN_NFT_NAME = "name";
        public final static String COLUMN_NFT_IMG_URL = "img_url";
    }
}
