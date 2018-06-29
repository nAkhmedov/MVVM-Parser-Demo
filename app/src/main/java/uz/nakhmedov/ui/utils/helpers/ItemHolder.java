package uz.nakhmedov.ui.utils.helpers;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/26/18
 * Time: 7:54 PM
 * To change this template use File | Settings | File Templates
 */
public class ItemHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public T binding;

    public ItemHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }
}
