package xteam.resourcexproject.baserecycleradapter;

import android.view.View;

/**
 * Created by 大灯泡 on 2016/7/20.
 *
 */

public interface OnRecyclerViewLongItemClick<T> {
    boolean onItemLongClick(View v, int position, T data);
}
