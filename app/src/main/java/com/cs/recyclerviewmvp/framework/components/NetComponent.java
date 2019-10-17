package com.cs.recyclerviewmvp.framework.components;

import com.cs.recyclerviewmvp.activities.MainActivity;
import com.cs.recyclerviewmvp.modules.AppModule;
import com.cs.recyclerviewmvp.modules.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Vijay Patel on 29/11/18.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
}