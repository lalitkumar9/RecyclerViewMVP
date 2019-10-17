package com.cs.recyclerviewmvp.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Vijay Patel on 29/11/18.
 */

@Module
public class SharedPreferenceModule {
    private Application mApplication;

    public SharedPreferenceModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
}
