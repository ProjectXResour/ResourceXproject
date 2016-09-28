package xteam.resourcexproject;

import android.app.Application;
import android.content.Context;

import xteam.resourcexproject.util.FileUtil;

/**
 * Created by 大灯泡 on 2016/9/27.
 *
 *
 */

public class XApp extends Application {

    private static Context CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = getApplicationContext();

        FileUtil.INSTANCE.initStorage();
    }

    public static Context getContext() {
        return CONTEXT;
    }
}
