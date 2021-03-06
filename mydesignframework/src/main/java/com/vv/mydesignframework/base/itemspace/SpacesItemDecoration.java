package com.vv.mydesignframework.base.itemspace;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private boolean shouldPadding;
    private boolean justPaddingRight;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    public void setLeftRight(boolean shouldPadding) {
        this.shouldPadding = shouldPadding;
    }

    public void setJustPaddingRight(boolean justPaddingRight) {
        this.justPaddingRight = justPaddingRight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (shouldPadding) {
            outRect.left = space;
            outRect.right = space;
        }
        if (justPaddingRight) {
            outRect.left = 0;
            outRect.right = space;
        }
        outRect.bottom = space;

        // Add top margin only for the first item to avoid double space between items
        if(parent.getChildPosition(view) == 0)
            outRect.top = space;
    }
}
