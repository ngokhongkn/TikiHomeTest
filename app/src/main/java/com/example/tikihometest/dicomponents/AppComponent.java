package com.example.tikihometest.dicomponents;

import com.example.tikihometest.App;
import com.example.tikihometest.modules.NetModule;
import com.example.tikihometest.ui.home.HomePresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {
    void inject(App app);

    void inject(HomePresenter homePresenter);
}