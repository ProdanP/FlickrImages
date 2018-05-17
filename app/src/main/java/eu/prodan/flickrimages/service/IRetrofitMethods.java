package eu.prodan.flickrimages.service;

import java.util.Map;

import eu.prodan.flickrimages.datamodel.FlickrResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by User on 16.05.2018.
 */

public interface IRetrofitMethods {
    @GET("services/rest/")
    Call<FlickrResponse> getFlickrSearch(@QueryMap Map<String, String> queryParams);
}
