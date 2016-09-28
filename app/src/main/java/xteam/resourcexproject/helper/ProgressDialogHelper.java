package xteam.resourcexproject.helper;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import xteam.resourcexproject.R;
import xteam.resourcexproject.widget.ProgressDialogView;


/**
 * Created by 大灯泡 on 2016/09/27.
 */

public class ProgressDialogHelper extends Dialog {

    private ProgressDialogView progressDialogView;
    String title;
    int progress;

    private ProgressDialogHelper(Context context) {
        this(context, R.style.MDialog);
    }

    private ProgressDialogHelper(Context context, int themeResId) {
        super(context, themeResId);
    }

    private ProgressDialogHelper(Context context,
                                 boolean cancelable,
                                 OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private ProgressDialogHelper(Context context, String title, int progress) {
        this(context);
        this.title = title;
        this.progress = progress;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialogView = new ProgressDialogView(getContext());
        progressDialogView.setTitle(title);
        progressDialogView.setProgress(progress);
        setContentView(progressDialogView.getRootView());
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = UIHelper.getWidthOrHeight(true) - UIHelper.dipToPx(25f);
        dialogWindow.setAttributes(lp);
    }

    public static ProgressDialogHelper create(Context context, String title, int progress) {
        return new ProgressDialogHelper(context, title, progress);
    }

    public void setTitle(String titleStr) {
        this.title = titleStr;
        if (progressDialogView != null) {
            progressDialogView.setTitle(titleStr);
        }
    }

    public void setProgress(int progress) {
        this.progress = progress;
        if (progressDialogView != null) {
            progressDialogView.setProgress(progress);
        }
    }
}
