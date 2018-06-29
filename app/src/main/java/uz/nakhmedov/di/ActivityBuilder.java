package uz.nakhmedov.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import uz.nakhmedov.ui.main.MainActivity;
import uz.nakhmedov.ui.main.MainActivityModule;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/25/18
 * Time: 7:13 PM
 * To change this template use File | Settings | File Templates
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

}
