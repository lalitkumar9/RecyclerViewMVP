package com.cs.recyclerviewmvp.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Vijay Patel on 29/11/18.
 */

@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }
}
