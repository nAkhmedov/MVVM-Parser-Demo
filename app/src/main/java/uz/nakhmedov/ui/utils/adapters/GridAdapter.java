package uz.nakhmedov.ui.utils.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uz.nakhmedov.data.model.Item;
import uz.nakhmedov.databinding.ListItemViewBinding;
import uz.nakhmedov.ui.main.MainViewModel;
import uz.nakhmedov.ui.utils.helpers.ItemHolder;
import uz.nakhmedov.ui.utils.helpers.ItemTouchHelperAdapter;
import uz.nakhmedov.ui.utils.helpers.OnStartDragListener;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/26/18
 * Time: 7:42 PM
 * To change this template use File | Settings | File Templates
 */
public class GridAdapter extends RecyclerView.Adapter<ItemHolder> implements ItemTouchHelperAdapter {

    private final OnStartDragListener mDragStartListener;
    private final MainViewModel mViewModel;
    private List<Item> mItems;

    public GridAdapter(MainViewModel viewModel, OnStartDragListener dragStartListener) {
        this.mViewModel = viewModel;
        mDragStartListener = dragStartListener;
        mItems = new ArrayList<>(16);
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemViewBinding view = ListItemViewBinding.inflate(inflater, parent, false);
        return new ItemHolder(view.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Item item = mItems.get(holder.getAdapterPosition());

        final ItemHolder<ListItemViewBinding> itemHolder = (ItemHolder<ListItemViewBinding>) holder;

        itemHolder.binding.setViewModel(mViewModel);
        itemHolder.binding.setItem(item);
        itemHolder.binding.setHolder(itemHolder);
        itemHolder.binding.executePendingBindings();
        itemHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(itemHolder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public boolean onItemMove(int startPosition, int endPosition) {
        Collections.swap(mItems, startPosition, endPosition);
        notifyItemMoved(startPosition, endPosition);
        mViewModel.updatePositionsInFile();
        return true;
    }

    public void swapData(List<Item> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    public void removeItem(int adapterPosition) {
        mItems.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
        mViewModel.updatePositionsInFile();
    }

    public void addItem(Item newItem) {
        int beforeLastPosition = mItems.size() - 1;
        mItems.add(beforeLastPosition, newItem);
        notifyItemInserted(beforeLastPosition);
    }
}
