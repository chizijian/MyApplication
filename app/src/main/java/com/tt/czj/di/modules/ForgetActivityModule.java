package com.tt.czj.di.modules;

import com.tt.czj.di.scope.UserScope;
import com.tt.czj.mvp.presenter.ForcegetPassPresenterImpl;
import com.tt.czj.mvp.presenter.ForgetPassPresenter;
import com.tt.czj.mvp.views.ForgetPassView;

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
