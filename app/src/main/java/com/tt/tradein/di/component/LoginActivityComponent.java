package com.tt.tradein.di.component;

import com.tt.tradein.di.modules.LoginActivityModule;
import com.tt.tradein.mvp.presenter.LoginActivityPresenter;
import com.tt.tradein.ui.activity.LoginActivity;

import dagger.Component;

/**
 * The interface Login activity component.
 */
@Component(dependencies = AppComponent.class,modules = LoginActivityModule.class)
public interface LoginActivityComponent {
    /**
     * Inject.
     *
     * @param activity the activity
     */
    void inject(LoginActivity activity);

    /**
     * Gets login presenter.
     *
     * @return the login presenter
     */
    LoginActivityPresenter getLoginPresenter();
}