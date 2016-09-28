package xteam.resourcexproject.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by 大灯泡 on 2016/9/27.
 */

public enum FileUtil {

    INSTANCE;

    private final String DECRYPT_PIC_PATH = "Alarms" + File.separator + "."+"xTeam".hashCode() + File.separator + "output"
            .hashCode() + File.separator;
    private final String INTERNAL_SDCARD_ROOTPATH = Environment.getExternalStorageDirectory()
            .getAbsolutePath();

    private String decryptPicPath;

    public void initStorage() {
        decryptPicPath = INTERNAL_SDCARD_ROOTPATH.concat(File.separator) + DECRYPT_PIC_PATH;

        File decryPicDir = new File(decryptPicPath);
        if (!decryPicDir.exists()) {
            decryPicDir.mkdirs();
        }
    }

    public String getDecryptPicPath() {
        return decryptPicPath;
    }
}
