package com.tt.czj.di.component;

import com.tt.czj.di.modules.AllKindModule;
import com.tt.czj.mvp.presenter.KindPresenter;
import com.tt.czj.ui.activity.AllKindActivity;
import com.tt.czj.ui.activity.KindActivity;
import com.tt.czj.ui.activity.SecondKindActivity;

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
