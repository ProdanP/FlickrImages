package eu.prodan.flickrimages.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import eu.prodan.flickrimages.R;
import eu.prodan.flickrimages.datamodel.FlickrResponse;
import eu.prodan.flickrimages.ui.viewmodels.FlickrFeedViewModel;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callForFlickr();
    }

    public void callForFlickr() {
        FlickrFeedViewModel viewModel = ViewModelProviders.of(this).get(FlickrFeedViewModel.class);

        viewModel.getItemsFeedListObservable().observe(this, new Observer<FlickrResponse>() {
            @Override
            public void onChanged(@Nullable FlickrResponse flickrResponse) {
                Log.d("mainActivity", "onChanged: " + flickrResponse.getPhotosList().getPhotoInfoList().get(0).getTitle());
            }
        });

    }


}
