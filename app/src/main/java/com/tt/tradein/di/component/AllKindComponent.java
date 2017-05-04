package com.tt.tradein.di.component;

import com.tt.tradein.di.modules.AllKindModule;
import com.tt.tradein.mvp.presenter.KindPresenter;
import com.tt.tradein.ui.activity.AllKindActivity;
import com.tt.tradein.ui.activity.KindActivity;
import com.tt.tradein.ui.activity.SecondKindActivity;

import dagger.Component;

/**
 * The interface All kind component.
 */
@Component(dependencies = AppComponent.class,modules = AllKindModule.class)
public interface AllKindComponent {
    /**
     * The constant TAG.
     */
    public static final String TAG = "AllKindComponent";

    /**
     * Inject.
     *
     * @param allKindActivity the all kind activity
     */
    void inject(AllKindActivity allKindActivity);

    /**
     * Inject.
     *
     * @param kindActivity the kind activity
     */
    void inject(KindActivity kindActivity);

    /**
     * Inject.
     *
     * @param secondKindActivity the second kind activity
     */
    void inject(SecondKindActivity secondKindActivity);

    /**
     * Gets kind presenter.
     *
     * @return the kind presenter
     */
    KindPresenter getKindPresenter();
}
