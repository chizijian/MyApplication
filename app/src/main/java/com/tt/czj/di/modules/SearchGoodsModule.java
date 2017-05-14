package com.tt.czj.di.modules;

import com.tt.czj.di.scope.UserScope;
import com.tt.czj.mvp.presenter.SearchGoodsPresenter;
import com.tt.czj.mvp.presenter.SearchGoodsPresenterImpl;
import com.tt.czj.mvp.views.SearchGoodsView;

import dagger.Module;
import dagger.Provides;

/**
 * The type Search goods module.
 */
@UserScope
@Module
public class SearchGoodsModule {
    /**
     * The constant TAG.
     */
    public static final String TAG = "SearchGoodsModule";
    private SearchGoodsView searchGoodsView;

    /**
     * Instantiates a new Search goods module.
     *
     * @param searchGoodsView the search goods view
     */
    public SearchGoodsModule(SearchGoodsView searchGoodsView){
        this.searchGoodsView = searchGoodsView;
    }

    /**
     * Provide view search goods view.
     *
     * @return the search goods view
     */
    @Provides
    public SearchGoodsView provideView(){
        return searchGoodsView;
    }

    /**
     * Provide presenter search goods presenter.
     *
     * @param searchGoodsView the search goods view
     * @return the search goods presenter
     */
    @Provides
    public SearchGoodsPresenter providePresenter(SearchGoodsView searchGoodsView){
        return new SearchGoodsPresenterImpl(searchGoodsView);
    }
}
