package com.example.android.databasecachefromjson;

import static com.example.android.databasecachefromjson.GetObservableDto.getDto;
import static com.uber.autodispose.AutoDispose.autoDisposable;
import static com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider.from;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.android.databasecachefromjson.data_model.Assets;
import com.example.android.databasecachefromjson.data_model.AssetsBean;
import com.example.android.databasecachefromjson.data_model.Dto;
import com.example.android.databasecachefromjson.data_model.NftDao;
import com.example.android.databasecachefromjson.data_model.NftDatabase;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public final String TAG = MainActivity.class.getName();
    private List<Assets> nftList;
    private List<AssetsBean> rawDataList;
    private NftDao nftDao;
    private NftDatabase nftDatabase;
    private RecyclerView nftRecyclerView;
    private NftListAdapter nftAdapter;
    static final String UUID = "d3267819-ea0b-4209-b521-b7b2d887c6c1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nftDao = NftDatabase.getInstance(this).nftDao();

        bindData(UUID);

        nftRecyclerView = findViewById(R.id.nft_recycler);
        nftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        nftAdapter = new NftListAdapter(nftList);
        nftRecyclerView.setAdapter(nftAdapter);
    }

    public void bindData(String uuid) {
        getDto(uuid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(from(this)))
                .subscribe(this::onSuccess,this::onError);
    }

    private void onError(Throwable throwable) {
        System.out.println(throwable);
        throwable.printStackTrace();
        throw new RuntimeException(throwable);
    }

    private void onSuccess(Dto dto) {
        rawDataList = dto.assets();

        nftDao.insert();
        nftAdapter.setData(nftDao.getAll());
    }
}
