package com.syrgdev.testapplicationforpikabu.common.dependencyInjection.application;

import com.syrgdev.testapplicationforpikabu.common.dependencyInjection.presentation.PresentationComponent;
import com.syrgdev.testapplicationforpikabu.common.dependencyInjection.presentation.PresentationModule;
import com.syrgdev.testapplicationforpikabu.common.dependencyInjection.presentation.UseCasesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    PresentationComponent newPresentationComponent(PresentationModule presentationModule,
                                                   UseCasesModule useCasesModule);

}
