package com.tt.tradein.di.component;

import com.tt.tradein.di.modules.PublishActivityModule;
import com.tt.tradein.mvp.presenter.PublishActivityPresenter;
import com.tt.tradein.ui.activity.PublishGoodsActivity;

import dagger.Component;

/**
 * The interface Publish activity component.
 */
@Component(dependencies = AppComponent.class,modules = PublishActivityModule.class)
public interface PublishActivityComponent {
    /**
     * Inject.
     *
     * @param activity the activity
     */
    void inject(PublishGoodsActivity activity);

    /**
     * Gets main presenter.
     *
     * @return the main presenter
     */
    PublishActivityPresenter getMainPresenter();
}
