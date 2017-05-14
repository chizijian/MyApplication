package com.tt.czj.di.modules;

import com.tt.czj.di.scope.UserScope;
import com.tt.czj.domain.RestApiAdapter;
import com.tt.czj.domain.service.WeatherApiService;
import com.tt.czj.mvp.presenter.HomeViewPresenter;
import com.tt.czj.mvp.presenter.HomeViewPresenterImpl;
import com.tt.czj.mvp.presenter.KindSortPresenter;
import com.tt.czj.mvp.presenter.KindSortPresenterImpl;
import com.tt.czj.mvp.presenter.NearByPresenter;
import com.tt.czj.mvp.presenter.NearByPresenterImpl;
import com.tt.czj.mvp.presenter.PersonCenterFragmentPresenter;
import com.tt.czj.mvp.presenter.PersonCenterFragmentPresenterImpl;
import com.tt.czj.mvp.views.HomeView;
import com.tt.czj.mvp.views.KindSortView;
import com.tt.czj.mvp.views.NearByView;
import com.tt.czj.mvp.views.PersonCenterView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * The type Main activity module.
 */
@UserScope
@Module
public class MainActivityModule {
    private HomeView view;
    private PersonCenterView personCenterView;
    private NearByView nearByView;
    private KindSortView kindSortView;

    /**
     * Instantiates a new Main activity module.
     *
     * @param view the view
     */
    public MainActivityModule(HomeView view) {
        this.view = view;
    }

    /**
     * Instantiates a new Main activity module.
     *
     * @param personCenterView the person center view
     */
    public MainActivityModule(PersonCenterView personCenterView){
        this.personCenterView = personCenterView;
    }

    /**
     * Instantiates a new Main activity module.
     *
     * @param nearByView the near by view
     */
    public MainActivityModule(NearByView nearByView){
        this.nearByView = nearByView;
    }

    /**
     * Instantiates a new Main activity module.
     *
     * @param kindSortView the kind sort view
     */
    public MainActivityModule(KindSortView kindSortView){
        this.kindSortView = kindSortView;
    }

    /**
     * Provide kind sort view kind sort view.
     *
     * @return the kind sort view
     */
    @Provides
    public KindSortView provideKindSortView(){
        return kindSortView;
    }

    /**
     * Privide person center view person center view.
     *
     * @return the person center view
     */
    @Provides
    public PersonCenterView prividePersonCenterView(){
        return personCenterView;
    }

    /**
     * Provide view home view.
     *
     * @return the home view
     */
    @Provides
    public HomeView provideView() {
        return view;
    }

    /**
     * Provide near by view near by view.
     *
     * @return the near by view
     */
    @Provides
    public NearByView provideNearByView(){
        return nearByView;
    }

    /**
     * Provide rest adapter retrofit.
     *
     * @return the retrofit
     */
    @Provides
    public Retrofit provideRestAdapter() {
        return RestApiAdapter.getInstance();
    }

    /**
     * Provide home api weather api service.
     *
     * @param restAdapter the rest adapter
     * @return the weather api service
     */
    @Provides
    public WeatherApiService provideHomeApi(Retrofit restAdapter) {
        return restAdapter.create(WeatherApiService.class);
    }

    /**
     * Provide presenter home view presenter.
     *
     * @param homeView          the home view
     * @param weatherApiService the weather api service
     * @return the home view presenter
     */
    @Provides
    public HomeViewPresenter providePresenter(HomeView homeView, WeatherApiService weatherApiService) {
        return new HomeViewPresenterImpl(homeView,weatherApiService);
    }

    /**
     * Privide person center presenter person center fragment presenter.
     *
     * @param personCenterView1 the person center view 1
     * @return the person center fragment presenter
     */
    @Provides
    public PersonCenterFragmentPresenter prividePersonCenterPresenter(PersonCenterView personCenterView1){
        return new PersonCenterFragmentPresenterImpl(personCenterView1);
    }

    /**
     * Provide near by presenter near by presenter.
     *
     * @param nearByView the near by view
     * @return the near by presenter
     */
    @Provides
    public NearByPresenter provideNearByPresenter(NearByView nearByView){
        return new NearByPresenterImpl(nearByView);
    }

    /**
     * Provide kind sort presenter kind sort presenter.
     *
     * @param kindSortView the kind sort view
     * @return the kind sort presenter
     */
    @Provides
    public KindSortPresenter provideKindSortPresenter(KindSortView kindSortView){
        return new KindSortPresenterImpl(kindSortView);
    }
}
