package com.tt.czj.di.modules;

import com.tt.czj.di.scope.UserScope;
import com.tt.czj.mvp.presenter.KindPresenter;
import com.tt.czj.mvp.presenter.KindPresenterImpl;
import com.tt.czj.mvp.views.AllKindViews;

import dagger.Module;
import dagger.Provides;

/**
 * The type All kind module.
 */
@UserScope
@Module
public class AllKindModule {
    /**
     * The constant TAG.
     */
    public static final String TAG = "AllKindModule";
    private AllKindViews view;

    /**
     * Instantiates a new All kind module.
     *
     * @param view the view
     */
    public AllKindModule(AllKindViews view) {
        this.view = view;
    }

    /**
     * Provide view all kind views.
     *
     * @return the all kind views
     */
    @Provides
    public AllKindViews provideView() {
        return view;
    }

    /**
     * Provide presenter kind presenter.
     *
     * @param homeView the home view
     * @return the kind presenter
     */
    @Provides
    public KindPresenter providePresenter(AllKindViews homeView) {
        return new KindPresenterImpl(homeView);
    }
}
