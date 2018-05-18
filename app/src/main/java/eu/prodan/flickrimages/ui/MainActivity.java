package eu.prodan.flickrimages.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;

import eu.prodan.flickrimages.R;
import eu.prodan.flickrimages.adapters.PicassoCache;
import eu.prodan.flickrimages.datamodel.FlickrResponse;
import eu.prodan.flickrimages.ui.viewmodels.FlickrFeedViewModel;
import eu.prodan.flickrimages.ui.views.CustomGridLayoutManager;
import eu.prodan.flickrimages.ui.views.DynamicToolbar;
import eu.prodan.flickrimages.ui.views.RecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DynamicToolbar toolbar;
    FlickrFeedViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(FlickrFeedViewModel.class);

        recyclerView = findViewById(R.id.images_recycler_view);
        recyclerView.setLayoutManager(new CustomGridLayoutManager(this, 500));

        toolbar = findViewById(R.id.dynamic_toolbar);
        toolbar.setOnSearchIconClickListner(onSearchClickListener);
        toolbar.setOnEditTextKeyListener(onKeyListener);
        setSupportActionBar(toolbar);

        final RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        viewModel.getItemsFeedListObservable().observe(this, new Observer<FlickrResponse>() {
            @Override
            public void onChanged(@Nullable FlickrResponse flickrResponse) {
                if (flickrResponse != null)
                    adapter.setRepos(flickrResponse.getPhotosList().getPhotoInfoList());
            }
        });

    }

    public void callForFlickr() {
        viewModel.searchByTag(toolbar.getTextFromInput());
    }

    View.OnClickListener onSearchClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            callForFlickr();
        }
    };

    View.OnKeyListener onKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if ((event.getAction() == KeyEvent.ACTION_DOWN) && keyCode == KeyEvent.KEYCODE_ENTER) {
                callForFlickr();
                return true;
            }

            return false;
        }
    };

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            callForFlickr();
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PicassoCache.getInstance().deleteCache();
    }
}
