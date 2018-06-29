package uz.nakhmedov.ui.utils.helpers;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/27/18
 * Time: 6:03 PM
 * To change this template use File | Settings | File Templates
 */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public GridSpacingItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;

        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = space;
        } else {
            outRect.top = 0;
        }
    }
}