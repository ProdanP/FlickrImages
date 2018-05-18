package eu.prodan.flickrimages.ui.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eu.prodan.flickrimages.R;
import eu.prodan.flickrimages.adapters.PicassoCache;
import eu.prodan.flickrimages.datamodel.FlickrPage;
import eu.prodan.flickrimages.datamodel.PhotoInfo;
import eu.prodan.flickrimages.ui.DetailedActivity;

/**
 * Created by User on 17.05.2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<PhotoInfo> mPhotoInfoList = new ArrayList<>();


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mPhotoInfoList.get(position).getImageUrl(), mPhotoInfoList.get(position).getTitle());
    }

    public void setRepos(List<PhotoInfo> repos){
        this.mPhotoInfoList.clear();
        this.mPhotoInfoList.addAll(repos);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mPhotoInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemTitle;
        public ImageView itemImage;

        public ViewHolder(View itemView) {
            super(itemView);

            itemTitle = itemView.findViewById(R.id.item_title);
            itemImage = itemView.findViewById(R.id.flickr_imageview);
        }

        public void setData(final String imageUrl, String imageTitle) {
            itemTitle.setText(imageTitle);

            PicassoCache.getInstance().loadImage(imageUrl, itemImage);

            itemImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), DetailedActivity.class);
                    intent.putExtra("image_url", imageUrl);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
