package xteam.resourcexproject.util;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;

import xteam.resourcexproject.R;
import xteam.resourcexproject.key.XKey;
import xteam.resourcexproject.log.XLog;
import xteam.resourcexproject.thread.ThreadPoolManager;

/**
 * Created by 大灯泡 on 2016/9/27.
 * <p>
 * 图片管理类
 */

public class XImageUtil {

    private static HashMap<String, String> ORIGIN_PIC_MD5_MAP = new HashMap<>();

    private static volatile LinkedList<Integer> taskList = new LinkedList<>();

    private static final int[] PROTECT_IMAGE_LIST = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
            R.drawable.image9,
            R.drawable.image10,
            R.drawable.image11,
            R.drawable.image12,
            R.drawable.image13,
            R.drawable.image14,
            R.drawable.image15,
            R.drawable.image16
    };

    static {
        ORIGIN_PIC_MD5_MAP.put(MD5Util.MD5("image1"), "ff307350e7d347fdd53019c65dc132bb");
        ORIGIN_PIC_MD5_MAP.put(MD5Util.MD5("image2"), "024480294dcb42e2883622aee66d26ae");
        ORIGIN_PIC_MD5_MAP.put(MD5Util.MD5("image3"), "6bc5fa1dbe1f68fb49b6cbb59073d61b");
        ORIGIN_PIC_MD5_MAP.put(MD5Util.MD5("image4"), "91db4d97b19d96f9cbd33538a008c80c");
        ORIGIN_PIC_MD5_MAP.put(MD5Util.MD5("image5"), "f73487ef9a30f110ac0a174138d253c4");
        ORIGIN_PIC_MD5_MAP.put(MD5Util.MD5("image6"), "1340d1f159c8764b9833cbf189e945e6");
        ORIGIN_PIC_MD5_MAP.put(MD5Util.MD5("image7"), "1744e354feb8ba475933186c2ecdd813");
        ORIGIN_PIC_MD5_MAP.put(MD5Util.MD5("image8"), "e7dcabc4303410ccb370a3f3521771a5");
        ORIGIN_PIC_MD5_MAP.put(MD5Util.MD5("image9"), "ec5807ddfec04ff2526167067243ae9b");
        ORIGIN_PIC_MD5_MAP.put(MD5Util.MD5("image10"), "64f765326c330d596cdd6a456e3434b5");
        ORIGIN_PIC_MD5_MAP.put(MD5Util.MD5("image11"), "2b87c840c27cb3760f7466861208e056");
        ORIGIN_PIC_MD5_MAP.put(MD5Util.MD5("image12"), "0f9488b3de2fa098d8b91434954aafcc");
        ORIGIN_PIC_MD5_MAP.put(MD5Util.MD5("image13"), "41716fb6448f948ea9b0ad0231edc9be");
        ORIGIN_PIC_MD5_MAP.put(MD5Util.MD5("image14"), "5dcd18494be08fc7a02df2fcf0ee56f0");
        ORIGIN_PIC_MD5_MAP.put(MD5Util.MD5("image15"), "e1260c0e913fd34d86ab05bb89e1acd0");
        ORIGIN_PIC_MD5_MAP.put(MD5Util.MD5("image16"), "493b11d12f29283cb7f150044e53b911");

    }

    public static void decryptImageAndCopy(final Context context,
                                           final int resourceid,
                                           @Nullable final
                                           OnDecryptListener onDecryptListener) {
        taskList.add(resourceid);
        ThreadPoolManager.executeOnLinkThreadPool(new Runnable() {
            @Override
            public void run() {
                XLog.info("正在执行图片解密操作");
                String name = context.getResources().getResourceEntryName(resourceid);
                name = MD5Util.MD5(name);
                File file = new File(FileUtil.INSTANCE.getDecryptPicPath() + name);
                int currentTask = taskList.indexOf(resourceid);
                if (file.exists()) {
                    String fileMD5 = MD5Util.getFileMD5(file);
                    XLog.info("发现了已经解密的文件，其MD5>>>  " + fileMD5 + "    本地存有的MD5  >>>  " + ORIGIN_PIC_MD5_MAP
                            .get(name));
                    if (TextUtils.equals(fileMD5, ORIGIN_PIC_MD5_MAP.get(name))) {
                        if (onDecryptListener != null) {
                            onDecryptListener.onProgress(currentTask + 1, taskList.size(), 100);
                            if (currentTask == (taskList.size() - 1)) {
                                onDecryptListener.onSuccess();
                            }
                        }
                        XLog.info("已经进行过解密并且MD5校验正确，退出");
                        return;
                    } else {
                        XLog.info("MD5校验错误，删除文件");
                        file.delete();
                    }
                }
                if (onDecryptListener != null) {
                    onDecryptListener.onStart(currentTask + 1, taskList.size());
                }
                InputStream inputStream = context.getResources().openRawResource(resourceid);
                try {
                    if (inputStream != null) {
                        FileOutputStream fileOutputStream = new FileOutputStream(FileUtil.INSTANCE.getDecryptPicPath() + name);
                        int size = inputStream.available();
                        int count = 0;
                        int read = -1;
                        byte[] buffered = new byte[1024];
                        while ((read = inputStream.read(buffered)) > -1) {
                            for (int i = 0; i < buffered.length; i++) {
                                buffered[i] ^= XKey.KEY_BY_XOR;
                            }
                            fileOutputStream.write(buffered, 0, read);
                            count += buffered.length;
                            if (onDecryptListener != null) {
                                onDecryptListener.onProgress(currentTask + 1,
                                                             taskList.size(),
                                                             (int) ((count * 1.0f / size * 1.0f) * 100));
                            }
                        }
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        inputStream.close();
                        if (currentTask == (taskList.size() - 1)) {
                            if (onDecryptListener != null) {
                                onDecryptListener.onSuccess();
                                taskList.clear();
                            }
                        }
                        XLog.info("完成执行图片解密操作完成");
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static File getFileById(Context context, int resourceid) {
        String name = context.getResources().getResourceEntryName(resourceid);
        name = MD5Util.MD5(name);
        File file = new File(FileUtil.INSTANCE.getDecryptPicPath() + name);
        return file;
    }

    /**
     * 返回解密过的文件，如果文件不存在，就返回ID（字符串）
     *
     * @param context
     * @param resourceid
     * @return
     */
    public static String getStringById(Context context, int resourceid) {
        String name = context.getResources().getResourceEntryName(resourceid);
        name = MD5Util.MD5(name);
        File file = new File(FileUtil.INSTANCE.getDecryptPicPath() + name);
        if (file.exists()) {
            return file.getAbsolutePath();
        } else {
            return String.valueOf(resourceid);
        }
    }

    public static void decryptImageAndCopy(Context context,
                                           @Nullable OnDecryptListener onDecryptListener) {
        for (int i : PROTECT_IMAGE_LIST) {
            decryptImageAndCopy(context, i, onDecryptListener);
        }
    }


    public interface OnDecryptListener {
        void onStart(int currentTask, int taskSumNum);

        void onProgress(int currentTask, int taskSumNum, int progress);

        void onSuccess();
    }


}
