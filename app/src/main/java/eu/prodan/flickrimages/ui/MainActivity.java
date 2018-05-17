package eu.prodan.flickrimages.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import eu.prodan.flickrimages.R;
import eu.prodan.flickrimages.adapters.PicassoCache;
import eu.prodan.flickrimages.datamodel.FlickrResponse;
import eu.prodan.flickrimages.ui.viewmodels.FlickrFeedViewModel;
import eu.prodan.flickrimages.ui.views.CustomGridLayoutManager;
import eu.prodan.flickrimages.ui.views.DynamicToolbar;
import eu.prodan.flickrimages.ui.views.RecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DynamicToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.images_recycler_view);
        recyclerView.setLayoutManager(new CustomGridLayoutManager(this, 500));

        toolbar = findViewById(R.id.dynamic_toolbar);
        setSupportActionBar(toolbar);

        callForFlickr();
    }

    public void callForFlickr() {
        FlickrFeedViewModel viewModel = ViewModelProviders.of(this).get(FlickrFeedViewModel.class);

        viewModel.getItemsFeedListObservable().observe(this, new Observer<FlickrResponse>() {
            @Override
            public void onChanged(@Nullable FlickrResponse flickrResponse) {
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(MainActivity.this, flickrResponse.getPhotosList().getPhotoInfoList());
                recyclerView.setAdapter(adapter);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PicassoCache.getInstance().deleteCache();
    }
}
