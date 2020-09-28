package com.syrgdev.testapplicationforpikabu.common.presenter.base;


import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;

import com.syrgdev.testapplicationforpikabu.PikabuTestApp;
import com.syrgdev.testapplicationforpikabu.common.dependencyInjection.application.ApplicationComponent;
import com.syrgdev.testapplicationforpikabu.common.dependencyInjection.presentation.PresentationComponent;
import com.syrgdev.testapplicationforpikabu.common.dependencyInjection.presentation.PresentationModule;
import com.syrgdev.testapplicationforpikabu.common.dependencyInjection.presentation.UseCasesModule;


public abstract class BaseFragment extends Fragment {

    private boolean isInjectorUsed;

    @UiThread
    protected PresentationComponent getPresentationComponent() {

        if (isInjectorUsed) {
            throw new RuntimeException("there is no need to use injector more than once");
        }

        isInjectorUsed = true;

        return getApplicationComponent().newPresentationComponent(
                new PresentationModule(requireActivity()),
                new UseCasesModule());

    }

    private ApplicationComponent getApplicationComponent() {
        return ((PikabuTestApp) requireActivity().getApplication()).getApplicationComponent();
    }
}
