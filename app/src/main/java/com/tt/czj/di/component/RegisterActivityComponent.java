package com.tt.czj.di.component;

import com.tt.czj.di.modules.RegisterActivityModule;
import com.tt.czj.mvp.presenter.RegisterActivityPresenter;
import com.tt.czj.ui.activity.RegisterActivity;

import dagger.Component;

/**
 * The interface Register activity component.
 */
@Component(dependencies = AppComponent.class,modules = RegisterActivityModule.class)
public interface RegisterActivityComponent {
    /**
     * Inject.
     *
     * @param activity the activity
     */
    void inject(RegisterActivity activity);

    /**
     * Gets register presenter.
     *
     * @return the register presenter
     */
    RegisterActivityPresenter getRegisterPresenter();
}
