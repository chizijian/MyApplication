package com.tt.czj.di.modules;

import com.tt.czj.di.scope.UserScope;
import com.tt.czj.mvp.presenter.PublishActivityPresenter;
import com.tt.czj.mvp.presenter.PublishActivityPresenterImpl;
import com.tt.czj.mvp.views.PublishView;

import dagger.Module;
import dagger.Provides;

/**
 * The type Publish activity module.
 */
@UserScope
@Module
public class PublishActivityModule {
    private PublishView view;

    /**
     * Instantiates a new Publish activity module.
     *
     * @param view the view
     */
    public PublishActivityModule(PublishView view) {
        this.view = view;
    }

    /**
     * Provide view publish view.
     *
     * @return the publish view
     */
    @Provides
    public PublishView provideView() {
        return view;
    }

    /**
     * Provide presenter publish activity presenter.
     *
     * @param homeView the home view
     * @return the publish activity presenter
     */
    @Provides
    public PublishActivityPresenter providePresenter(PublishView homeView) {
        return new PublishActivityPresenterImpl(homeView);
    }

}
