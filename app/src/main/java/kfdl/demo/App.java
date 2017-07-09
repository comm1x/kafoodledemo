package kfdl.demo;

import android.app.Application;

import kfdl.demo.di.AppComponent;
import kfdl.demo.di.AppModule;
import kfdl.demo.di.DaggerAppComponent;

public class App extends Application {
    private static AppComponent component;
    private static App appInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static AppComponent getComponent() {
        return component;
    }

    public static App instance() {
        return appInstance;
    }
}
