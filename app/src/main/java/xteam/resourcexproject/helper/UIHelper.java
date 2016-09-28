package xteam.resourcexproject.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import xteam.resourcexproject.XApp;
import xteam.resourcexproject.log.XLog;

/**
 * @author 大灯泡
 *         <p/>
 *         各种UI工具类（activity跳转类？）
 */
public class UIHelper {

    public static final int ORIGINAL_VALUE = -100;

    // =============================================================tools
    // methods

    /**
     * dip转px
     */
    public static int dipToPx(float dip) {
        return (int) (dip * XApp.getContext().getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     * px转dip
     */
    public static int pxToDip(float pxValue) {
        final float scale = XApp.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值
     */
    public static int sp2px(float spValue) {
        final float fontScale = XApp.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取屏幕宽高
     *
     * @param isWidth 是否用取宽度
     */
    public static int getWidthOrHeight(boolean isWidth) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) XApp.getContext()
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getMetrics(metrics);
        XLog.info("ScreenSize",
                  "widthPixels:" + metrics.widthPixels + "--heightPixels" + metrics.heightPixels);
        if (isWidth) {
            return metrics.widthPixels;
        } else {
            return metrics.heightPixels;
        }
    }


    /**
     * Toast封装
     */
    public static void ToastMessage(String msg) {
        Toast.makeText(XApp.getContext(), msg, Toast.LENGTH_LONG).show();
    }

    public static void setViewsClickListener(@NonNull View.OnClickListener listener,
                                             View... views) {
        for (View view : views) {
            if (view != null) {
                view.setOnClickListener(listener);
            }
        }
    }

}
