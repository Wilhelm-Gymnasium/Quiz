package de.wilhelmgym.quiz.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacingItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacingItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);

        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();

            outRect.right = space;
            outRect.bottom = space;

            if (layoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
                if (position % layoutManager.getSpanCount() == 0)
                    outRect.left = space;
                if (position < layoutManager.getSpanCount())
                    outRect.top = space;
            } else {
                if (position % layoutManager.getSpanCount() == 0)
                    outRect.top = space;
                if (position < layoutManager.getSpanCount())
                    outRect.left = space;
            }
        } else if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();

            outRect.right = space;
            outRect.bottom = space;

            if (layoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
                if (position < 0)
                    outRect.top = space;
                outRect.left = space;
            } else {
                if (position < 0)
                    outRect.left = space;
                outRect.top = space;
            }
        }

    }
}