package eu.prodan.flickrimages.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

import eu.prodan.flickrimages.ApiConstants;
import eu.prodan.flickrimages.datamodel.FlickrResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 16.05.2018.
 */

public class FlickrSearchService {
    public LiveData<FlickrResponse> getFlicker(String searchedTag){
        String method = "flickr.photos.search";
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("api_key", ApiConstants.API_KEY);
        queryParams.put("method", method);
        queryParams.put("tags", searchedTag);

        final MutableLiveData<FlickrResponse> data = new MutableLiveData<>();

        RetrofitCall.getInstance().getFlicker(queryParams).enqueue(new Callback<FlickrResponse>() {
            @Override
            public void onResponse(Call<FlickrResponse> call, Response<FlickrResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<FlickrResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
