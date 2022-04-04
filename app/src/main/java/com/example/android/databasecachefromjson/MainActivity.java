package com.example.android.databasecachefromjson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.LoaderManager;
import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.example.android.databasecachefromjson.data.NftContract;
import com.example.android.databasecachefromjson.data_model.Asset;
import com.example.android.databasecachefromjson.data_model.Dto;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int NFT_LOADER_ID = 0;
    private RecyclerView nftRecyclerView;
    private NftListAdapter nftAdapter;
    static final String UUID = "d3267819-ea0b-4209-b521-b7b2d887c6c1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindData(UUID);

        getLoaderManager().initLoader(NFT_LOADER_ID, null, this);

        nftRecyclerView = findViewById(R.id.nft_recycler);
        nftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        nftAdapter = new NftListAdapter(this);
        nftRecyclerView.setAdapter(nftAdapter);
    }

    private void insertDataToDatabase(List<Asset> listFromRetrofit) {
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
            getContentResolver().applyBatch(NftContract.CONTENT_AUTHORITY, batch);
        } catch (OperationApplicationException | RemoteException e) {
            e.printStackTrace();
        }
    }

    public void bindData(String uuid) {
        NftRepository
                .fetchClient()
                .create(NftService.class)
                .dtoRepos(uuid)
                .enqueue(new Callback<Dto>() {
                    @Override
                    public void onResponse(Call<Dto> call, Response<Dto> response) {
                        if(nftAdapter.getItemCount() == 0) {
                            insertDataToDatabase(removeNull(response.body().assets()));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Dto> call, @NonNull Throwable t) {
                        Log.d("TAG", "Failed!" + "Response = " + t);
                    }
                });
    }

    public List<Asset> removeNull(List<Asset> input) {
        List<Asset> newFilteredList = new ArrayList<>();
        //remove null from list
        for (int i = 0; i < input.size(); i++) {
            Asset current = input.get(i);
            if (current.token_id() == null) {
                continue;
             }
            if (current.permalink() == null) {
                continue;
            }
            newFilteredList.add(current);
        }
        return newFilteredList;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                NftContract.NftEntry._ID,
                NftContract.NftEntry.COLUMN_NFT_TOKEN_ID,
                NftContract.NftEntry.COLUMN_NFT_PERMALINK,
                NftContract.NftEntry.COLUMN_NFT_NAME,
                NftContract.NftEntry.COLUMN_NFT_IMG_URL
        };

        return new CursorLoader(this,
                NftContract.NftEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        nftAdapter.setData(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        nftAdapter.setData(null);
    }
}