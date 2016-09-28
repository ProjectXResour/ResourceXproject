package xteam.resourcexproject.encry;

import java.io.File;
import java.io.IOException;

import xteam.resourcexproject.key.XKey;

/**
 * Created by 大灯泡 on 2016/9/27.
 * <p>
 * 加密程序，这段代码应放到PC(eclipse)上执行
 */

public class EncryImageUtil {
   /*



    public static final int XOR_CONST = XKey.KEY_BY_XOR; // 密钥

    public static final String ORIGINAL_PIC_PATH = "D:/OriginPic";
    public static final String ENCRY_PIC_PATH = "D:/EncryedPic";

    public static void main(String[] args) {
		encryImage();
        printFileMD5();
    }

    public static void printFileMD5() {
        File dir = new File(ORIGINAL_PIC_PATH);
        if (dir.exists() && dir.isDirectory()) {
            final File[] images = dir.listFiles();
            if (images != null && images.length > 0) {
                for (int i = 0; i < images.length; i++) {
                    System.out.println(images[i].getName() + "   MD5   >>>>>   ");
                    System.out.println(MD5Util.getFileMD5(images[i]));
                }
            }

        }
    }

    public static void encryImage() {
        File dir = new File(ORIGINAL_PIC_PATH);
        if (dir.exists() && dir.isDirectory()) {
            final File[] images = dir.listFiles();
            if (images != null && images.length > 0) {
                for (int i = 0; i < images.length; i++) {
                    encry(images[i]);
                }
            }

        }

    }

    public static void encry(final File originalFile) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    ImageInputStream fis = new FileImageInputStream(
                            originalFile);
                    File outFile = new File(ENCRY_PIC_PATH + File.separator
                            + originalFile.getName());
                    ImageOutputStream fos = new FileImageOutputStream(outFile);
                    int read;
                    while ((read = fis.read()) > -1) {
                        fos.write(read);
                    }
                    fos.flush();
                    fos.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }




    */
}
