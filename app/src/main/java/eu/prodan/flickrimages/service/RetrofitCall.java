package eu.prodan.flickrimages.service;

import java.util.Map;

import eu.prodan.flickrimages.datamodel.FlickrResponse;
import retrofit2.Call;

/**
 * Created by User on 16.05.2018.
 */

public class RetrofitCall {
    private static RetrofitCall mInstance;

    public static RetrofitCall getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitCall();
        }

        return mInstance;
    }

    public Call<FlickrResponse> getFlicker(Map<String, String> queryParams) {
        return RetrofitService.RETROFIT_METHODS.getFlickrSearch(queryParams);
    }
}
