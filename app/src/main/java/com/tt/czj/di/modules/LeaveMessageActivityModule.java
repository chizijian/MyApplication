package com.tt.czj.di.modules;

import com.tt.czj.di.scope.UserScope;
import com.tt.czj.mvp.presenter.LeaveMessagePresenter;
import com.tt.czj.mvp.presenter.LeaveMessagePresenterlmpl;
import com.tt.czj.mvp.views.LeaveMessageActivityView;

import dagger.Module;
import dagger.Provides;

/**
 * The type Leave message activity module.
 */
@UserScope
@Module
public class LeaveMessageActivityModule {
    private LeaveMessageActivityView leaveMessageActivityView;

    /**
     * Instantiates a new Leave message activity module.
     *
     * @param leaveMessageActivityView the leave message activity view
     */
    public  LeaveMessageActivityModule(LeaveMessageActivityView leaveMessageActivityView){
        this.leaveMessageActivityView=leaveMessageActivityView;
    }

    /**
     * Provide view leave message activity view.
     *
     * @return the leave message activity view
     */
    @Provides
    public LeaveMessageActivityView provideView() {
        return leaveMessageActivityView;
    }

    /**
     * Provide presenter leave message presenter.
     *
     * @param homeView the home view
     * @return the leave message presenter
     */
    @Provides
    public LeaveMessagePresenter providePresenter(LeaveMessageActivityView homeView) {
        return new LeaveMessagePresenterlmpl(homeView);
    }

}
