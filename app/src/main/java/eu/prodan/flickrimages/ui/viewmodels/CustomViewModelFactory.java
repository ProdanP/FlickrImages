package eu.prodan.flickrimages.ui.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * Created by User on 17.05.2018.
 */

public class CustomViewModelFactory implements ViewModelProvider.Factory{
    private final String searchedTag;

    public CustomViewModelFactory(String searchedTag) {
        this.searchedTag = searchedTag;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if(modelClass.isAssignableFrom(FlickrFeedViewModel.class)){
            return (T) new FlickrFeedViewModel(searchedTag);
        }

        return null;
    }
}
