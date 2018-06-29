package uz.nakhmedov.ui.main;

import android.arch.lifecycle.ViewModelProvider;

import dagger.Module;
import dagger.Provides;
import uz.nakhmedov.ParserApplication;
import uz.nakhmedov.data.DataSource;
import uz.nakhmedov.ui.utils.ViewModelProviderFactory;
import uz.nakhmedov.ui.utils.rx.SchedulerProvider;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/25/18
 * Time: 7:15 PM
 * To change this template use File | Settings | File Templates
 */
@Module
public class MainActivityModule {

    @Provides
    ViewModelProvider.Factory mainViewModelProvide(MainViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }

    @Provides
    MainViewModel provideMainViewModel(ParserApplication context, DataSource dataSource, SchedulerProvider schedulerProvider) {
        return new MainViewModel(context, dataSource, schedulerProvider);
    }
}
