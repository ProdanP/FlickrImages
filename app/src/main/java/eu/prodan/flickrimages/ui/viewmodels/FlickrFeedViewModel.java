package eu.prodan.flickrimages.ui.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import eu.prodan.flickrimages.datamodel.FlickrResponse;
import eu.prodan.flickrimages.service.FlickrSearchService;

/**
 * Created by User on 17.05.2018.
 */

public class FlickrFeedViewModel extends ViewModel {
    private final LiveData<FlickrResponse> itemsFeedListObservable;
    public FlickrFeedViewModel() {
        itemsFeedListObservable = new FlickrSearchService().getFlicker("Dog");
    }

    public LiveData<FlickrResponse> getItemsFeedListObservable(){
        return itemsFeedListObservable;
    }


}
