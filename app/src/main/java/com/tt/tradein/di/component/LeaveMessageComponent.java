package com.tt.tradein.di.component;

import com.tt.tradein.di.modules.LeaveMessageActivityModule;
import com.tt.tradein.mvp.presenter.LeaveMessagePresenter;
import com.tt.tradein.ui.activity.LeaveMessageActivity;

import dagger.Component;

/**
 * The interface Leave message component.
 */
@Component(dependencies = AppComponent.class,modules = LeaveMessageActivityModule.class)
public interface LeaveMessageComponent {
    /**
     * Inject.
     *
     * @param activity the activity
     */
    void inject(LeaveMessageActivity activity);

    /**
     * Gets leave message presenter.
     *
     * @return the leave message presenter
     */
    LeaveMessagePresenter getLeaveMessagePresenter();
}
