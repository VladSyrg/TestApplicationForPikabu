package com.syrgdev.testapplicationforpikabu.common.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.syrgdev.testapplicationforpikabu.common.view.base.IView;
import com.syrgdev.testapplicationforpikabu.common.view.main.MainActivityView;
import com.syrgdev.testapplicationforpikabu.common.view.main.MainActivityViewImpl;
import com.syrgdev.testapplicationforpikabu.post.view.PostView;
import com.syrgdev.testapplicationforpikabu.post.view.PostViewImpl;
import com.syrgdev.testapplicationforpikabu.posts.view.postsitem.PostsItemView;
import com.syrgdev.testapplicationforpikabu.posts.view.postsitem.PostsItemViewImpl;
import com.syrgdev.testapplicationforpikabu.posts.view.PostsView;
import com.syrgdev.testapplicationforpikabu.posts.view.PostsViewImpl;

public class ViewFactory {

    private final LayoutInflater mLayoutInflater;

    public ViewFactory(LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
    }

    public <T extends IView> T newViewInstance(Class<T> viewClass, @Nullable ViewGroup container) {

        IView view;

        if (viewClass.isAssignableFrom(PostsView.class)) {
            view = new PostsViewImpl(mLayoutInflater, container, this);
        } else if (viewClass.isAssignableFrom(MainActivityView.class)) {
            view = new MainActivityViewImpl(mLayoutInflater, container);
        } else if (viewClass.isAssignableFrom(PostView.class)) {
            view = new PostViewImpl(mLayoutInflater, container);
        } else if (viewClass.isAssignableFrom(PostsItemView.class)) {
            view = new PostsItemViewImpl(mLayoutInflater, container);
        } else if (viewClass.isAssignableFrom(SavedPostsView.class)) {
            view = new SavedPostsViewImpl(mLayoutInflater, container);
        } else {
            throw new IllegalArgumentException("unsupported view class: " + viewClass);
        }

        //noinspection unchecked
        return (T) view;
    }

}
