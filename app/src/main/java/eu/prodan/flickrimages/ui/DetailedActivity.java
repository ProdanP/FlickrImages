package eu.prodan.flickrimages.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import eu.prodan.flickrimages.R;
import eu.prodan.flickrimages.adapters.PicassoCache;

/**
 * Created by User on 17.05.2018.
 */

public class DetailedActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        imageView = findViewById(R.id.imageView);

        String imageUrl = getIntent().getStringExtra("image_url");

        PicassoCache.getInstance().loadImage(imageUrl, imageView);
    }
}
