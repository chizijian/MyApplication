package com.tt.tradein.di.component;

import com.tt.tradein.app.MyApp;
import com.tt.tradein.di.modules.AppModule;

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

