package com.mhd.basekit;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.ref.WeakReference;

/**
 * @author zhangming
 * @Date 2019/7/9 11:01
 * @Description: 文件操作
 */
public class FileUtils {

    public static WeakReference<RandomAccessFile> raf;
    public static File file;

    public static RandomAccessFile getRandomAccessFile() {
        if (raf == null) {
            return null;
        }
        return raf.get();
    }

    // 将字符串写入到文本文件中
    public static void writeTxtToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        String strContent = strcontent + "\r\n";
        if (getRandomAccessFile() == null) {
            makeFilePath(filePath, fileName);
            String strFilePath = filePath + fileName;
            // 每次写入时，都换行写
            try {
                file = new File(strFilePath);
                if (!file.exists()) {
                    Log.d("TestFile", "Create the file:" + strFilePath);
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                FileUtils.raf = new WeakReference<>(raf);
                raf.seek(file.length());
                raf.write(strContent.getBytes());
//                raf.close();
            } catch (Exception e) {
                Log.e("TestFile", "Error on write File:" + e);
            }
        } else {
            try {
                getRandomAccessFile().write(strContent.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void closeFile() {
        if (getRandomAccessFile() != null) {
            try {
                getRandomAccessFile().close();
                raf = null;
                file = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 生成文件
    public static File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }

    }
}
