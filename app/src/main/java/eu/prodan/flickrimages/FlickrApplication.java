package eu.prodan.flickrimages;

import android.app.Application;

/**
 * Created by User on 17.05.2018.
 */

public class FlickrApplication extends Application {
    private static FlickrApplication mInstance;

    public static FlickrApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

}
