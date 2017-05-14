package com.tt.czj.di.modules;

import com.tt.czj.di.scope.UserScope;
import com.tt.czj.mvp.presenter.RegisterActivityPresenter;
import com.tt.czj.mvp.presenter.RegisterActivityPresenterImpl;
import com.tt.czj.mvp.views.RegisterView;

import dagger.Module;
import dagger.Provides;

/**
 * The type Register activity module.
 */
@UserScope
@Module
public class RegisterActivityModule {
    private RegisterView view;

    /**
     * Instantiates a new Register activity module.
     *
     * @param view the view
     */
    public RegisterActivityModule(RegisterView view) {
        this.view = view;
    }

    /**
     * Provide view register view.
     *
     * @return the register view
     */
    @Provides
    public RegisterView provideView() {
        return view;
    }

    /**
     * Provide presenter register activity presenter.
     *
     * @param homeView the home view
     * @return the register activity presenter
     */
    @Provides
    public RegisterActivityPresenter providePresenter(RegisterView homeView) {
        return new RegisterActivityPresenterImpl(homeView);
    }
}
