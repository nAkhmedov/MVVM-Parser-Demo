package uz.nakhmedov.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import uz.nakhmedov.ParserApplication;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/25/18
 * Time: 7:09 PM
 * To change this template use File | Settings | File Templates
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class})
public interface AppComponent extends AndroidInjector<ParserApplication> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<ParserApplication> {}
}