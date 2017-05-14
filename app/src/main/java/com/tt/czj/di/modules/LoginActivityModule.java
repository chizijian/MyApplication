package com.tt.czj.di.modules;

import com.tt.czj.di.scope.UserScope;
import com.tt.czj.mvp.presenter.LoginActivityPresenter;
import com.tt.czj.mvp.presenter.LoginActivityPresenterImpl;
import com.tt.czj.mvp.views.LoginActivityView;

import dagger.Module;
import dagger.Provides;

/**
 * The type Login activity module.
 */
@UserScope
@Module
public class LoginActivityModule {
    private LoginActivityView view;

    /**
     * Instantiates a new Login activity module.
     *
     * @param view the view
     */
    public LoginActivityModule(LoginActivityView view) {
        this.view = view;
    }

    /**
     * Provide view login activity view.
     *
     * @return the login activity view
     */
    @Provides
    public LoginActivityView provideView() {
        return view;
    }

    /**
     * Provide presenter login activity presenter.
     *
     * @param homeView the home view
     * @return the login activity presenter
     */
    @Provides
    public LoginActivityPresenter providePresenter(LoginActivityView homeView) {
        return new LoginActivityPresenterImpl(homeView);
    }
}
