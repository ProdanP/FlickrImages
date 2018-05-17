package eu.prodan.flickrimages.service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by User on 16.05.2018.
 */

public class RetrofitService {
    public static String BASE_URL = "https://api.flickr.com/";
    private static RetrofitService mInstance;

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    private static Retrofit createService() {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient);

        return retrofitBuilder.build();
    }

    public static IRetrofitMethods RETROFIT_METHODS = createService().create(IRetrofitMethods.class);
}
