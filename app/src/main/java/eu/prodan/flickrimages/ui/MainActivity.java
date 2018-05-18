package eu.prodan.flickrimages.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import eu.prodan.flickrimages.R;
import eu.prodan.flickrimages.adapters.PicassoCache;
import eu.prodan.flickrimages.datamodel.FlickrResponse;
import eu.prodan.flickrimages.ui.viewmodels.CustomViewModelFactory;
import eu.prodan.flickrimages.ui.viewmodels.FlickrFeedViewModel;
import eu.prodan.flickrimages.ui.views.CustomGridLayoutManager;
import eu.prodan.flickrimages.ui.views.DynamicToolbar;
import eu.prodan.flickrimages.ui.views.RecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DynamicToolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.images_recycler_view);
        recyclerView.setLayoutManager(new CustomGridLayoutManager(this, 500));

        toolbar = findViewById(R.id.dynamic_toolbar);
        toolbar.setOnSearchIconClickListner(onSearchClickListener);
        toolbar.setOnEditTextKeyListener(onKeyListener);

        setSupportActionBar(toolbar);
    }

    public void callForFlickr() {
        CustomViewModelFactory factory = new CustomViewModelFactory(toolbar.getTextFromInput());

        FlickrFeedViewModel viewModel = ViewModelProviders.of(this, factory).get(FlickrFeedViewModel.class);
        viewModel.getItemsFeedListObservable().observe(this, new Observer<FlickrResponse>() {
            @Override
            public void onChanged(@Nullable FlickrResponse flickrResponse) {
                if(flickrResponse != null){
                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(MainActivity.this, flickrResponse.getPhotosList().getPhotoInfoList());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        });

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
            if((event.getAction() == KeyEvent.ACTION_DOWN)&&keyCode == KeyEvent.KEYCODE_ENTER){
                callForFlickr();
                return true;
            }

            return false;
        }
    };

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
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
