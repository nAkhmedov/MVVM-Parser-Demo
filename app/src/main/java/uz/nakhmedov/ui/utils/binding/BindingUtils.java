package uz.nakhmedov.ui.utils.binding;

import android.databinding.BindingAdapter;
import android.support.v7.content.res.AppCompatResources;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import uz.nakhmedov.R;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/26/18
 * Time: 8:18 PM
 * To change this template use File | Settings | File Templates
 */
public class BindingUtils {

    @BindingAdapter({"visibleOrGone"})
    public static void setVisibility(View view, boolean isEnable) {
        view.setVisibility(isEnable ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        loadImage(view, url, R.mipmap.ic_launcher);
    }

    private static void loadImage(ImageView view, String url, int drawable) {
        Glide
            .with(view.getContext())
            .load(url)
            .apply(new RequestOptions()
            .placeholder(AppCompatResources.getDrawable(view.getContext(), drawable)))
            .into(view);

    }

}
