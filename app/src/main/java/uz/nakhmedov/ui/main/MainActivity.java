package uz.nakhmedov.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import uz.nakhmedov.BR;
import uz.nakhmedov.R;
import uz.nakhmedov.data.model.Item;
import uz.nakhmedov.data.model.enums.TypeEnum;
import uz.nakhmedov.databinding.ActivityMainBinding;
import uz.nakhmedov.ui.base.BaseActivity;
import uz.nakhmedov.ui.utils.adapters.GridAdapter;
import uz.nakhmedov.ui.utils.helpers.GridSpacingItemDecoration;
import uz.nakhmedov.ui.utils.helpers.ItemTouchHelperCallback;
import uz.nakhmedov.ui.utils.helpers.OnStartDragListener;
import uz.nakhmedov.ui.utils.helpers.SpannedGridLayoutManager;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements
        MainView,
        OnStartDragListener {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    private MainViewModel mViewModel;
    private GridAdapter mAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);
        return mViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mViewModel.setNavigator(this);

        mViewModel.loadData();

        mAdapter = new GridAdapter(mViewModel, this);

        final int spanCount = getResources().getInteger(R.integer.grid_columns);
        SpannedGridLayoutManager layoutManager = new SpannedGridLayoutManager(
                position -> {
                    if (position == 0) {
                        return new SpannedGridLayoutManager.SpanInfo(2, 2);
                    } else {
                        return new SpannedGridLayoutManager.SpanInfo(1, 1);
                    }
                }, spanCount, 1
        );

        getBinding().recyclerView.setHasFixedSize(true);
        getBinding().recyclerView.addItemDecoration(new GridSpacingItemDecoration(20));
        getBinding().recyclerView.setLayoutManager(layoutManager);
        getBinding().recyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(getBinding().recyclerView);
    }

    @Override
    public void showConfirmationDialog(final Item item, final int position) {
        new AlertDialog.Builder(this)
                .setMessage(getString(R.string.confirm_text, item.getUuid()))
                .setPositiveButton(R.string.yes, (dialog, which) -> {
                    mAdapter.removeItem(position);
                })
                .setNegativeButton(R.string.cancel, null)
                .create()
                .show();
    }

    @Override
    public void onLoadItems(List<Item> items) {
        mAdapter.swapData(items);
    }

    @Override
    public void onListItemClicked(Item item) {
        if (item.getType().equals(TypeEnum.ACTION_ADD)) {
            final EditText input = new EditText(MainActivity.this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);

            new AlertDialog.Builder(this)
                    .setView(input)
                    .setTitle(getString(R.string.insert_img_url))
                    .setPositiveButton(R.string.ok, (dialog, which) -> mViewModel.addNewItem(input.getText().toString()))
                    .setNegativeButton(R.string.cancel, null)
                    .create()
                    .show();

            return;
        }
        new AlertDialog.Builder(this)
                .setMessage(getString(R.string.select_message, item.getUuid()))
                .setPositiveButton(R.string.great, null)
                .create()
                .show();
    }


    @Override
    public void onInsert(Item item) {
        mAdapter.addItem(item);
    }

    @Override
    public void showErrorMessage(String msg) {
        Toast.makeText(this, msg,  Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
