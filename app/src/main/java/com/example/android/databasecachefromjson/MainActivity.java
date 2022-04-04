package com.example.android.databasecachefromjson;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.databasecachefromjson.data.NftContract;
import com.example.android.databasecachefromjson.data_model.Dto;

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

    public void bindData(String uuid) {
        NftRepository
                .fetchClient()
                .create(NftService.class)
                .dtoRepos(uuid)
                .enqueue(new Callback<Dto>() {
                    @Override
                    public void onResponse(Call<Dto> call, Response<Dto> response) {
                        if (nftAdapter.getItemCount() == 0) {
                            StaticDataHolder.insertDataToDatabase(StaticDataHolder.removeNull(response.body().assets()), MainActivity.this.getContentResolver());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Dto> call, @NonNull Throwable t) {
                        Log.d("TAG", "Failed!" + "Response = " + t);
                    }
                });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new CursorLoader(this,
                NftContract.NftEntry.CONTENT_URI,
                StaticDataHolder.PROJECTION,
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