package uz.nakhmedov.ui.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/25/18
 * Time: 7:17 PM
 * To change this template use File | Settings | File Templates
 */
public class ViewModelProviderFactory<V> implements ViewModelProvider.Factory {

    private V viewModel;

    public ViewModelProviderFactory(V viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(viewModel.getClass())) {
            return (T) viewModel;
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
