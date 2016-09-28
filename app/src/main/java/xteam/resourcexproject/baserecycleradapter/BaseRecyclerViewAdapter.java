package xteam.resourcexproject.baserecycleradapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import xteam.resourcexproject.R;

/**
 * Created by 大灯泡 on 2016/7/20.
 *
 * 抽象的adapter
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder<T>> {

    protected Context context;
    protected List<T> datas;
    protected LayoutInflater mInflater;

    private OnRecyclerViewItemClick<T> onRecyclerViewItemClick;
    private OnRecyclerViewLongItemClick<T> onRecyclerViewLongItemClick;

    public BaseRecyclerViewAdapter(@NonNull Context context, @NonNull List<T> datas) {
        this.context = context;
        this.datas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override public int getItemViewType(int position) {
        return getViewType(position, datas.get(position));
    }

    @Override public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseRecyclerViewHolder holder = null;
        if (getLayoutResId(viewType) != 0) {
            View rootView = mInflater.inflate(getLayoutResId(viewType), parent, false);
            holder = getViewHolder(rootView, viewType);
        } else {
            holder = getViewHolder(null, viewType);
        }
        setUpItemEvent(holder);
        return holder;
    }

    @Override public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        T data = datas.get(position);
        holder.itemView.setTag(R.id.recycler_view_tag, data);
        holder.onBindData(data, position);
        onBindData(holder, data, position);
    }

    private void setUpItemEvent(final BaseRecyclerViewHolder holder) {
        if (onRecyclerViewItemClick != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //这个获取位置的方法，防止添加删除导致位置不变
                    int layoutPosition = holder.getAdapterPosition();
                    onRecyclerViewItemClick.onItemClick(holder.itemView, layoutPosition, datas.get(layoutPosition));
                }
            });
            //longclick
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPosition = holder.getAdapterPosition();
                    onRecyclerViewLongItemClick.onItemLongClick(holder.itemView, layoutPosition, datas.get(layoutPosition));
                    return false;
                }
            });
        }
    }

    @Override public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void addData(int pos, @NonNull T data) {
        if (datas != null) {
            datas.add(pos, data);
            notifyItemInserted(pos);
        }
    }

    public void addData(@NonNull T data) {
        if (datas != null) {
            datas.add(data);
            notifyItemInserted(datas.size() - 1);
        }
    }

    public void deleteData(int pos) {
        if (datas != null && datas.size() > pos) {
            datas.remove(pos);
            notifyItemRemoved(pos);
        }
    }

    protected abstract int getViewType(int position, @NonNull T data);

    protected abstract int getLayoutResId(int viewType);

    protected abstract BaseRecyclerViewHolder getViewHolder(View rootView, int viewType);

    protected void onBindData(BaseRecyclerViewHolder<T> holder, T data, int position) {

    }

    public OnRecyclerViewItemClick<T> getOnRecyclerViewItemClick() {
        return onRecyclerViewItemClick;
    }

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClick<T> onRecyclerViewItemClick) {
        this.onRecyclerViewItemClick = onRecyclerViewItemClick;
    }

    public OnRecyclerViewLongItemClick<T> getOnRecyclerViewLongItemClick() {
        return onRecyclerViewLongItemClick;
    }

    public void setOnRecyclerViewLongItemClick(OnRecyclerViewLongItemClick<T> onRecyclerViewLongItemClick) {
        this.onRecyclerViewLongItemClick = onRecyclerViewLongItemClick;
    }
}
