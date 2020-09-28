package com.syrgdev.testapplicationforpikabu.posts.view;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.syrgdev.testapplicationforpikabu.common.PostData;
import com.syrgdev.testapplicationforpikabu.common.view.ViewFactory;
import com.syrgdev.testapplicationforpikabu.posts.view.postsitem.PostsItemView;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>
        implements PostsItemView.Observer {


    public interface Observer {
        void onItemClicked(PostData postData);

        void onSaveClicked(PostData postData);
    }

    List<PostData> mPosts = new ArrayList<>();

    private ViewFactory mViewFactory;
    private Observer mObserver;

    public PostsAdapter(ViewFactory viewFactory, Observer observer) {
        this.mViewFactory = viewFactory;
        this.mObserver = observer;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PostsItemView postsItemView = mViewFactory.newViewInstance(PostsItemView.class, parent);
        postsItemView.registerObserver(this);
        return new ViewHolder(postsItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostData postData = mPosts.get(position);
        holder.mPostsItemView.bind(postData);
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public void setPosts(List<PostData> postDataList) {
        mPosts = new ArrayList<>(postDataList);
        notifyDataSetChanged();
    }

    @Override
    public void onItemClicked(PostData postData) {
        mObserver.onItemClicked(postData);
    }

    @Override
    public void onSavedClicked(PostData postData) {
        mObserver.onSaveClicked(postData);
    }

    public void onPostUpdated(PostData postData) {

        for (PostData post : mPosts) {
            if (post.getId().equals(postData.getId())) {
                int index = mPosts.indexOf(post);
                mPosts.remove(index);
                mPosts.add(index, postData);
                notifyItemChanged(index);
                break;
            }
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public PostsItemView mPostsItemView;

        public ViewHolder(PostsItemView postsItemView) {
            super(postsItemView.getRootView());
            mPostsItemView = postsItemView;
        }
    }
}
