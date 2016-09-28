package xteam.resourcexproject.log;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by 大灯泡 on 2016/9/27.
 * <p>
 * 简单的LOG封装
 */

public class XLog {
    public static final String LOG_TAG = "xTeam";

    public static void info(String msg) {
        info(LOG_TAG, msg);
    }

    public static void info(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void verbose(String msg) {
        verbose(LOG_TAG, msg);
    }

    public static void verbose(String tag, String msg) {
        Log.v(tag, msg);
    }

    public static void debug(String msg) {
        debug(LOG_TAG, msg);
    }

    public static void debug(String tag, String msg) {
        Log.d(tag, msg);
    }


    public static void error(String msg) {
        error(LOG_TAG, msg);
    }

    public static void error(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void error(Exception e) {
        error(LOG_TAG, e);
    }

    public static void error(String tag, Exception e) {
        Log.e(tag, getCrashInfo(e));
    }

    public static void error(Throwable ex) {
        error(LOG_TAG, ex);
    }

    public static void error(String tag, Throwable ex) {
        Log.e(tag, getCrashInfo(ex));
    }

    private static String getCrashInfo(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        String crashInfo = writer.toString();
        printWriter.close();
        return crashInfo;
    }

}
