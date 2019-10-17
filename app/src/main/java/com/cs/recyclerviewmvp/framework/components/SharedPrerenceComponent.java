package com.cs.recyclerviewmvp.framework.components;

import android.content.SharedPreferences;

import com.cs.recyclerviewmvp.modules.SharedPreferenceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Vijay Patel on 29/11/18.
 */

@Singleton
@Component(modules = {SharedPreferenceModule.class})
public interface SharedPrerenceComponent {
    SharedPreferences getSharedPreference();
}
