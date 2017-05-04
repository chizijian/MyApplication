package com.tt.tradein.di.component;

import com.tt.tradein.di.modules.ForgetActivityModule;
import com.tt.tradein.mvp.presenter.ForgetPassPresenter;
import com.tt.tradein.ui.activity.ForgetPassActivity;

import dagger.Component;

/**
 * The interface Forget activity component.
 */
@Component(dependencies = AppComponent.class,modules = ForgetActivityModule.class)
public interface ForgetActivityComponent {
    /**
     * Inject.
     *
     * @param activity the activity
     */
    void inject(ForgetPassActivity activity);

    /**
     * Gets forget pass presenter.
     *
     * @return the forget pass presenter
     */
    ForgetPassPresenter getForgetPassPresenter();
}
