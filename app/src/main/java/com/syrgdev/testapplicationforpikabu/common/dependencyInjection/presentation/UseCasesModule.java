package com.syrgdev.testapplicationforpikabu.common.dependencyInjection.presentation;

import com.syrgdev.testapplicationforpikabu.persistance.PersistenceManager;
import com.syrgdev.testapplicationforpikabu.posts.usecase.FetchPostsUseCase;
import com.syrgdev.testapplicationforpikabu.posts.usecase.UpdatePostUseCase;
import com.syrgdev.testapplicationforpikabu.saved.usecase.FetchSavedPostsUseCase;

import dagger.Module;
import dagger.Provides;


@Module
public class UseCasesModule {

    @Provides
    FetchPostsUseCase getFetchPostsUseCase(PersistenceManager persistenceManager) {
        return new FetchPostsUseCase(persistenceManager);
    }

    @Provides
    UpdatePostUseCase getUpdatePostUseCase( PersistenceManager persistenceManager) {
        return new UpdatePostUseCase(persistenceManager);
    }

    @Provides
    FetchSavedPostsUseCase getFetchSavedPostsUseCase(PersistenceManager persistenceManager) {
        return new FetchSavedPostsUseCase(persistenceManager);
    }
}
