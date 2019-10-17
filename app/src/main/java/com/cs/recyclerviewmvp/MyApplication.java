package com.cs.recyclerviewmvp;

import android.app.Application;

import com.cs.recyclerviewmvp.framework.components.DaggerNetComponent;
import com.cs.recyclerviewmvp.framework.components.NetComponent;
import com.cs.recyclerviewmvp.modules.AppModule;
import com.cs.recyclerviewmvp.modules.NetModule;
import com.cs.recyclerviewmvp.utils.Constants;

/**
 * Created by Vijay Patel on 29/11/18.
 */

public class MyApplication extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(Constants.EVENT_FEED_URL))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

}