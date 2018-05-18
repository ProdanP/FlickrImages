package eu.prodan.flickrimages.adapters;

import android.content.Context;
import android.widget.ImageView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import eu.prodan.flickrimages.FlickrApplication;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by User on 17.05.2018.
 */

public class PicassoCache {
    private static PicassoCache mInstance;
    private Cache cache;
    private Picasso picasso;

    public PicassoCache() {
        initializePicassoCache();
    }

    public static PicassoCache getInstance(){
        if(mInstance == null){
            mInstance = new PicassoCache();
        }
        return mInstance;
    }

    public void initializePicassoCache(){
        File httpCacheDirectory = new File(FlickrApplication.getInstance().getCacheDir(), "picasso-cache");
        cache = new Cache(httpCacheDirectory, 15*1024*1024);

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder().cache(cache);
        picasso = new Picasso.Builder(FlickrApplication.getInstance().getApplicationContext()).downloader(new OkHttp3Downloader(okHttpBuilder.build())).build();
    }

    public void deleteCache(){
        try {
            cache.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadImage(String imageUrl, ImageView imageView){
        picasso.load(imageUrl).into(imageView);
    }

}
