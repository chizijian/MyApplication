package com.tt.tradein.di.modules;

import com.tt.tradein.di.scope.UserScope;
import com.tt.tradein.mvp.presenter.RegisterActivityPresenter;
import com.tt.tradein.mvp.presenter.RegisterActivityPresenterImpl;
import com.tt.tradein.mvp.views.RegisterView;

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
