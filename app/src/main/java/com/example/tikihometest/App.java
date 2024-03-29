package com.example.tikihometest;

import android.app.Application;

import com.example.tikihometest.dicomponents.AppComponent;
import com.example.tikihometest.dicomponents.AppModule;
import com.example.tikihometest.dicomponents.DaggerAppComponent;
import com.example.tikihometest.modules.NetModule;

public class App extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        /*Init Dagger DI*/
        appComponent = DaggerAppComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)).netModule(new NetModule())
                .build();

        getAppComponent().inject(this);

    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
