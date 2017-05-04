package com.tt.tradein.di.modules;

import com.tt.tradein.di.scope.UserScope;
import com.tt.tradein.mvp.presenter.KindPresenter;
import com.tt.tradein.mvp.presenter.KindPresenterImpl;
import com.tt.tradein.mvp.views.AllKindViews;

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
