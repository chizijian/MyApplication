package com.tt.tradein.di.component;

import com.tt.tradein.di.modules.MainActivityModule;
import com.tt.tradein.mvp.presenter.HomeViewPresenter;
import com.tt.tradein.mvp.presenter.KindSortPresenter;
import com.tt.tradein.mvp.presenter.NearByPresenter;
import com.tt.tradein.mvp.presenter.PersonCenterFragmentPresenter;
import com.tt.tradein.ui.fragment.home_fragment.HomeFragment;
import com.tt.tradein.ui.fragment.KindSortFragment;
import com.tt.tradein.ui.fragment.PersonCenterFragment;
import com.tt.tradein.ui.fragment.base.BaseHomeFragment;
import com.tt.tradein.ui.fragment.base.BaseNearByFragment;
import com.tt.tradein.ui.fragment.nearby_fragment.NearBySecondHandFrangment;

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
