package uz.nakhmedov.ui.utils.rx;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/25/18
 * Time: 7:36 PM
 * To change this template use File | Settings | File Templates
 */
public class AppSchedulerProvider implements SchedulerProvider {
    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
