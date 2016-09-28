package xteam.resourcexproject.thread;

import android.os.AsyncTask;
import android.os.Build;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import xteam.resourcexproject.log.XLog;


/**
 * Created by 大灯泡 on 2016/9/27.
 * <p>
 * 简单的线程池封装
 */
public class ThreadPoolManager {

    private static ExecutorService threadPool = null;
    private static ExecutorService linkThreadPool = null;

    static {

        int processorNum = Runtime.getRuntime().availableProcessors(); //CPU数量
        threadPool = new ThreadPoolExecutor(processorNum, processorNum * 2 + 1, 20, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        linkThreadPool = Executors.newSingleThreadExecutor();
    }

    public static void execute(Runnable runnable) {
        try {
            threadPool.execute(runnable);
        } catch (Exception e) {
            e.printStackTrace();
            XLog.error(e.toString());
        }
    }

    public static void executeOnLinkThreadPool(Runnable runnable) {
        try {
            linkThreadPool.execute(runnable);
        } catch (Exception e) {
            e.printStackTrace();
            XLog.error(e.toString());
        }
    }


    /**
     * 针对不同api的 asynctask处理
     * 3.0以后的asynctask被改为默认串行，使用自己的线程池实现并行
     */
    public static <Params, Progress, Result> void executeOnExecutor(AsyncTask<Params, Progress, Result> task, Params...
            params) {
        if (Build.VERSION.SDK_INT >= 11) {
            task.executeOnExecutor(threadPool, params);
        } else {
            task.execute(params);
        }
    }
}
