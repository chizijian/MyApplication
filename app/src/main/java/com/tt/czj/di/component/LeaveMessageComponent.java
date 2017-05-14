package com.tt.czj.di.component;

import com.tt.czj.di.modules.LeaveMessageActivityModule;
import com.tt.czj.mvp.presenter.LeaveMessagePresenter;
import com.tt.czj.ui.activity.LeaveMessageActivity;

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
