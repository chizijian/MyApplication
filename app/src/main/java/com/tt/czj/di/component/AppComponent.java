package com.tt.czj.di.component;

import com.tt.czj.app.MyApp;
import com.tt.czj.di.modules.AppModule;

import dagger.Component;

/**
 * The interface App component.
 */
@Component(
        modules = {
                AppModule.class,
        }
)
public interface AppComponent {
    /**
     * Inject my app.
     *
     * @param rxRetrofitApplication the rx retrofit application
     * @return the my app
     */
    MyApp inject(MyApp rxRetrofitApplication);
}

