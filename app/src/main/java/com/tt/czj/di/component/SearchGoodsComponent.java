package com.tt.czj.di.component;

import com.tt.czj.di.modules.SearchGoodsModule;
import com.tt.czj.mvp.presenter.SearchGoodsPresenter;
import com.tt.czj.ui.activity.SearchActivity;
import com.tt.czj.ui.activity.SearchResultActivity;

import dagger.Component;

/**
 * The interface Search goods component.
 */
@Component(dependencies = AppComponent.class,modules = SearchGoodsModule.class)
public interface SearchGoodsComponent {
    /**
     * The constant TAG.
     */
    public static final String TAG = "SearchGoodsComponent";

    /**
     * Inject.
     *
     * @param searchResultActivity the search result activity
     */
    void inject(SearchResultActivity searchResultActivity);

    /**
     * Inject.
     *
     * @param searchActivity the search activity
     */
    void inject(SearchActivity searchActivity);

    /**
     * Gets presenter.
     *
     * @return the presenter
     */
    SearchGoodsPresenter getPresenter();
}
