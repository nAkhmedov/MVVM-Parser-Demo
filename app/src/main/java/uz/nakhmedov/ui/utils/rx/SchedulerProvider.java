package uz.nakhmedov.ui.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/25/18
 * Time: 7:35 PM
 * To change this template use File | Settings | File Templates
 */
public interface SchedulerProvider {

    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}
