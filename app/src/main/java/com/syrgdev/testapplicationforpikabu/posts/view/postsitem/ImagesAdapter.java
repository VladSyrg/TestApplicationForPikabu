package com.syrgdev.testapplicationforpikabu.posts.view.postsitem;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.syrgdev.testapplicationforpikabu.R;

public class ImagesAdapter
        extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    String[] mImages = new String[0];

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_image, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mImages[position]);
    }

    @Override
    public int getItemCount() {
        return mImages.length;
    }

    public void setImages(String[] images) {
        mImages = new String[images.length];
        System.arraycopy(images, 0, mImages, 0, images.length);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final String TAG = getClass().getSimpleName();
        ImageView mImageView;
        ProgressBar mProgressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_image_view);
            mProgressBar = itemView.findViewById(R.id.progress_bar);
        }

        private void bind(String imageURL) {
            Log.d(TAG, "bind: imageURL: " + imageURL);
            mImageView.setImageURI(null);
            mImageView.setImageDrawable(null);

            mProgressBar.setVisibility(View.VISIBLE);
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.ic_image_placeholder)
                    .error(R.drawable.ic_image_download_failed)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .priority(Priority.HIGH);

            //mImageView.setImageDrawable (null);
            Glide.with(itemView.getContext())
                    .load(imageURL)
                    .apply(options)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            mProgressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            mProgressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(mImageView);
        }
    }
}
