package com.tt.czj.di.component;

import com.tt.czj.di.modules.LoginActivityModule;
import com.tt.czj.mvp.presenter.LoginActivityPresenter;
import com.tt.czj.ui.activity.LoginActivity;

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