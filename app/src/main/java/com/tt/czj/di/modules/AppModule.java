package com.tt.czj.di.modules;

import android.content.Context;

import com.tt.czj.app.MyApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * The type App module.
 */
@Module
public class AppModule {
    /**
     * The constant TAG.
     */
    public static final String TAG = "AppModule";
    private final MyApp application;

    /**
     * Instantiates a new App module.
     *
     * @param application the application
     */
    public AppModule(MyApp application) {
        this.application = application;
    }

    /**
     * Provide application context context.
     *
     * @return the context
     */
    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }
}
