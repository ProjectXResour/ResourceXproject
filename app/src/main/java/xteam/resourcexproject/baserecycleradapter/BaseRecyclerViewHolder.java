package xteam.resourcexproject.baserecycleradapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.logging.Logger;

import xteam.resourcexproject.log.XLog;


/**
 * Created by 大灯泡 on 2016/7/20.
 *
 * 对viewholder的封装
 */

public abstract class BaseRecyclerViewHolder<T> extends RecyclerView.ViewHolder {

    private int viewType;

    public BaseRecyclerViewHolder(Context context, ViewGroup viewGroup, int layoutResId) {
        super(LayoutInflater.from(context).inflate(layoutResId, viewGroup, false));
    }

    public BaseRecyclerViewHolder(Context context, int layoutResId) {
        this(context,null,layoutResId);
    }

    public BaseRecyclerViewHolder(View itemView, int viewType) {
        super(itemView);
        this.viewType = viewType;
       XLog.info("init", "初始化了一个ViewHolder");
    }

    public final View findViewById(int resid) {
        if (resid > 0 && itemView != null) {
            return itemView.findViewById(resid);
        }
        return null;
    }

    public abstract void onBindData(T data, int position);

    protected int getViewType() {
        return viewType;
    }
}
