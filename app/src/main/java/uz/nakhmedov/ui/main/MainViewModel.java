package uz.nakhmedov.ui.main;

import android.text.TextUtils;

import java.util.UUID;

import io.reactivex.Single;
import uz.nakhmedov.ParserApplication;
import uz.nakhmedov.R;
import uz.nakhmedov.data.DataSource;
import uz.nakhmedov.data.model.Item;
import uz.nakhmedov.data.model.enums.TypeEnum;
import uz.nakhmedov.ui.base.BaseViewModel;
import uz.nakhmedov.ui.utils.helpers.ItemHolder;
import uz.nakhmedov.ui.utils.rx.SchedulerProvider;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/25/18
 * Time: 7:16 PM
 * To change this template use File | Settings | File Templates
 */
public class MainViewModel extends BaseViewModel<MainView> {

    public MainViewModel(ParserApplication context, DataSource dataSource, SchedulerProvider schedulerProvider) {
        super(context, dataSource, schedulerProvider);
    }

    public void loadData() {
        getCompositeDisposable().add(
                getDataSource().loadData()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(imageData -> {
                    getNavigator().onLoadItems(imageData.getItems());
                }, Throwable::printStackTrace)
        );
    }

    public void onItemClicked(Item item) {
        getNavigator().onListItemClicked(item);
    }

    public void onItemDeleteClicked(Item item, ItemHolder holder) {
        getNavigator().showConfirmationDialog(item, holder.getAdapterPosition());
    }

    public void addNewItem(String imgUrl) {
        if (TextUtils.isEmpty(imgUrl)) {
            getNavigator().showErrorMessage(getContext().getString(R.string.required_field));
            return;
        }

        String uuid = UUID.randomUUID().toString();
        Item newItem = new Item(uuid, imgUrl, TypeEnum.ACTION_DEFAULT);
        getNavigator().onInsert(newItem);
    }

    public void updatePositionsInFile() {
        getCompositeDisposable().add(
                Single.fromCallable(() -> getDataSource().updateFile())
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(isSuccess -> System.out.println("RemoveItem success"), Throwable::printStackTrace)
        );

    }
}
