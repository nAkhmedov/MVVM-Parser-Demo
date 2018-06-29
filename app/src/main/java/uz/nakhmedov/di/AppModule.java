package uz.nakhmedov.di;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uz.nakhmedov.ParserApplication;
import uz.nakhmedov.data.DataManager;
import uz.nakhmedov.data.DataSource;
import uz.nakhmedov.data.model.deserializer.TypeEnumDeserializer;
import uz.nakhmedov.data.model.enums.TypeEnum;
import uz.nakhmedov.ui.utils.rx.AppSchedulerProvider;
import uz.nakhmedov.ui.utils.rx.SchedulerProvider;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/25/18
 * Time: 7:13 PM
 * To change this template use File | Settings | File Templates
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(ParserApplication application) {
        return application;
    }

    @Provides
    @Singleton
    DataSource provideDataManager(DataManager dataManager) {
        return dataManager;
    }

    @Provides
    SchedulerProvider provideSchedularProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(TypeEnum.class, new TypeEnumDeserializer());

        return gsonBuilder.create();
    }


}
