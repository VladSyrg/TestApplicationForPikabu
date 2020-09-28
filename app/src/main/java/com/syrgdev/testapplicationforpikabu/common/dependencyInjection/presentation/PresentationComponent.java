package com.syrgdev.testapplicationforpikabu.common.dependencyInjection.presentation;

import com.syrgdev.testapplicationforpikabu.common.presenter.MainActivity;
import com.syrgdev.testapplicationforpikabu.saved.presenter.SavedPostsFragment;
import com.syrgdev.testapplicationforpikabu.post.presenter.PostFragment;
import com.syrgdev.testapplicationforpikabu.posts.presenter.PostsFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {PresentationModule.class,UseCasesModule.class})
public interface PresentationComponent {

    void inject(MainActivity mainActivity);

    void inject(PostsFragment postsFragment);

    void inject(PostFragment postFragment);

    void inject(SavedPostsFragment favoritesFragment);
}
