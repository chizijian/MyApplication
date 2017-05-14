package com.tt.czj.di.component;

import com.tt.czj.di.modules.MainActivityModule;
import com.tt.czj.mvp.presenter.HomeViewPresenter;
import com.tt.czj.mvp.presenter.KindSortPresenter;
import com.tt.czj.mvp.presenter.NearByPresenter;
import com.tt.czj.mvp.presenter.PersonCenterFragmentPresenter;
import com.tt.czj.ui.fragment.KindSortFragment;
import com.tt.czj.ui.fragment.PersonCenterFragment;
import com.tt.czj.ui.fragment.base.BaseHomeFragment;
import com.tt.czj.ui.fragment.base.BaseNearByFragment;
import com.tt.czj.ui.fragment.home_fragment.HomeFragment;
import com.tt.czj.ui.fragment.nearby_fragment.NearBySecondHandFrangment;

import dagger.Component;

/**
 * The interface Main activity component.
 */
@Component(dependencies = AppComponent.class,modules = MainActivityModule.class)
public interface MainActivityComponent {
    /**
     * Inject.
     *
     * @param homeFragment the home fragment
     */
    void inject(HomeFragment homeFragment);

    void inject(BaseHomeFragment baseHomeFragment);

    /**
     * Inject.
     *
     * @param personCenterFragment the person center fragment
     */
    void inject(PersonCenterFragment personCenterFragment);

    /**
     * Inject.
     *
     * @param kindSortFragment the kind sort fragment
     */
    void inject(KindSortFragment kindSortFragment);


    /**
     * Inject.
     *
     * @param nearBySecondHandFrangment the near by second hand frangment
     */
    void inject(NearBySecondHandFrangment nearBySecondHandFrangment);

    /**
     * Inject.
     *
     * @param baseFragment the base fragment
     */
    void inject(BaseNearByFragment baseFragment);

    /**
     * Gets kind sort presenter.
     *
     * @return the kind sort presenter
     */
    KindSortPresenter getKindSortPresenter();

    /**
     * Gets main presenter.
     *
     * @return the main presenter
     */
    HomeViewPresenter getMainPresenter();

    /**
     * Gets person center presenter.
     *
     * @return the person center presenter
     */
    PersonCenterFragmentPresenter getPersonCenterPresenter();

    /**
     * Gets near by presenter.
     *
     * @return the near by presenter
     */
    NearByPresenter getNearByPresenter();
}
