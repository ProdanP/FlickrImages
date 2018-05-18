package eu.prodan.flickrimages.ui.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import eu.prodan.flickrimages.datamodel.FlickrResponse;
import eu.prodan.flickrimages.service.FlickrSearchService;

/**
 * Created by User on 17.05.2018.
 */

public class FlickrFeedViewModel extends ViewModel {
    private final MediatorLiveData<FlickrResponse> itemsFeedListObservable = new MediatorLiveData<>();
    public FlickrFeedViewModel() {
    }

    public void searchByTag(String searchedTag){
        final LiveData <FlickrResponse> flickerLiveData = new FlickrSearchService().getFlicker(searchedTag);
        itemsFeedListObservable.addSource(flickerLiveData, new Observer<FlickrResponse>() {
            @Override
            public void onChanged(@Nullable FlickrResponse flickrResponse) {
                itemsFeedListObservable.postValue(flickrResponse);
                itemsFeedListObservable.removeSource(flickerLiveData);
            }
        });
    }

    public LiveData<FlickrResponse> getItemsFeedListObservable(){
        return itemsFeedListObservable;
    }
}
