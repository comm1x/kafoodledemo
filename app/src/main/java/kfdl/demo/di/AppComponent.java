package kfdl.demo.di;

import javax.inject.Singleton;

import dagger.Component;
import kfdl.demo.LoginActivity;
import kfdl.demo.App;
import kfdl.demo.EventActivity;
import kfdl.demo.EventCardActivity;
import kfdl.demo.MainActivity;
import kfdl.demo.MapsActivity;
import kfdl.demo.OfferCardActivity;
import kfdl.demo.model.DataLayer;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(App e);
    void inject(DataLayer e);
    void inject(MainActivity e);
    void inject(LoginActivity e);
    void inject(EventActivity e);
    void inject(EventCardActivity e);
    void inject(OfferCardActivity e);
    void inject(MapsActivity e);
}
