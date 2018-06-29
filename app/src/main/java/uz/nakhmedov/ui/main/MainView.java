package uz.nakhmedov.ui.main;

import java.util.List;

import uz.nakhmedov.data.model.Item;
import uz.nakhmedov.ui.utils.helpers.ItemHolder;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/25/18
 * Time: 7:51 PM
 * To change this template use File | Settings | File Templates
 */
public interface MainView {

    void showConfirmationDialog(Item item, int position);

    void onLoadItems(List<Item> items);

    void onListItemClicked(Item item);

    void onInsert(Item item);

    void showErrorMessage(String msg);
}
