package uz.nakhmedov.ui.base;

import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;

import io.reactivex.disposables.CompositeDisposable;
import uz.nakhmedov.ParserApplication;
import uz.nakhmedov.data.DataSource;
import uz.nakhmedov.ui.utils.rx.SchedulerProvider;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/25/18
 * Time: 7:47 PM
 * To change this template use File | Settings | File Templates
 */
public class BaseViewModel<N> extends AndroidViewModel {

    private final DataSource mDataSource;
    private final SchedulerProvider mSchedulerProvider;
    private final Context mContext;
    private CompositeDisposable mCompositeDisposable;
    private N mNavigator;

    public BaseViewModel(ParserApplication context, DataSource dataSource, SchedulerProvider schedulerProvider) {
        super(context);
        this.mContext = context.getApplicationContext();
        this.mDataSource = dataSource;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public DataSource getDataSource() {
        return mDataSource;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public Context getContext() {
        return mContext;
    }

    public N getNavigator() {
        return mNavigator;
    }

    public void setNavigator(N navigator) {
        this.mNavigator = navigator;
    }
}