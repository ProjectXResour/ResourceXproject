package xteam.resourcexproject.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import xteam.resourcexproject.R;


/**
 * Created by 大灯泡 on 2016/09/27.
 *
 * 进度条dialog
 */

public class ProgressDialogView {

    private LinearLayout rootView;
    private Context context;

    private TextView title;
    private ProgressBar progressBar;

    public ProgressDialogView(Context context) {
        this.context = context;
        rootView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.progress_dialog, null);
        initView();
    }

    private void initView() {
        title = (TextView) rootView.findViewById(R.id.dialog_title);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
    }

    public void setTitle(String titleStr) {
        if (title != null) {
            title.setText(titleStr);
        }
    }

    public void setProgress(int progress) {
        if (progressBar != null) {
            progressBar.setProgress(progress);
        }
    }

    public View getRootView() {
        return rootView;
    }
}
