package uz.nakhmedov.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/25/18
 * Time: 7:46 PM
 * To change this template use File | Settings | File Templates
 */

public abstract class BaseActivity<B extends ViewDataBinding, M extends BaseViewModel>
        extends DaggerAppCompatActivity {

    private B mBinding;
    private M mViewModel;

    /**
     * @return layout resource id
     */
    public abstract @LayoutRes int getLayoutId();

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract M getViewModel();

    /**
     * Override for set binding model variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    public B getBinding() {
        return mBinding;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performDataBinding();
    }

    private void performDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mBinding.setVariable(getBindingVariable(), mViewModel);
        mBinding.executePendingBindings();
    }
}