package com.tt.tradein.di.modules;

import com.tt.tradein.di.scope.UserScope;
import com.tt.tradein.mvp.presenter.ForcegetPassPresenterImpl;
import com.tt.tradein.mvp.presenter.ForgetPassPresenter;
import com.tt.tradein.mvp.views.ForgetPassView;

import dagger.Module;
import dagger.Provides;

/**
 * The type Forget activity module.
 */
@UserScope
@Module
public class ForgetActivityModule {
    private ForgetPassView view;

    /**
     * Instantiates a new Forget activity module.
     *
     * @param view the view
     */
    public ForgetActivityModule(ForgetPassView view) {
        this.view = view;
    }

    /**
     * Provide view forget pass view.
     *
     * @return the forget pass view
     */
    @Provides
    public ForgetPassView provideView() {
        return view;
    }

    /**
     * Provide presenter forget pass presenter.
     *
     * @param homeView the home view
     * @return the forget pass presenter
     */
    @Provides
    public ForgetPassPresenter providePresenter(ForgetPassView homeView) {
        return new ForcegetPassPresenterImpl(homeView);
    }
}
