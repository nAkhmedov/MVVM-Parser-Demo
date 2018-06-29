package uz.nakhmedov;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import uz.nakhmedov.di.DaggerAppComponent;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/25/18
 * Time: 7:06 PM
 * To change this template use File | Settings | File Templates
 */
public class ParserApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
