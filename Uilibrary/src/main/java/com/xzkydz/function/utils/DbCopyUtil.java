package com.xzkydz.function.utils;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

public class DbCopyUtil {



    String DATABASE_NAME = "数据库文件名称";

    String oldPath = "data/data/应用包名/databases/" + DATABASE_NAME;
    String newPath = Environment.getExternalStorageDirectory() + File.separator + DATABASE_NAME;

    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            File newfile = new File(newPath);
            if (!newfile.exists()) {
                newfile.createNewFile();
            }
            if (oldfile.exists()) { // 文件存在时
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; // 字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }



    /**
     * 拷贝数据库到SD卡
     * @param source
     * @param dest
     * @return
     */
    public static boolean copyDbToSD(String source, String dest) {
        try {
            File f1 = new File(source);
            File f2 = new File(dest);
            InputStream in = new FileInputStream(f1);
            OutputStream out = new FileOutputStream(f2);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0)
                out.write(buf, 0, len);
            in.close();
            out.close();
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }


    //     * @deprecated <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    /**
     * 拷贝数据库到sd卡
     *
     */
    public static void copyDataBaseToSD(String dbAddress){
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return ;
        }
        File dbFile = new File(dbAddress);
        File file  = new File(Environment.getExternalStorageDirectory(), "seeker.db");

        FileChannel inChannel = null,outChannel = null;

        try {
            file.createNewFile();
            inChannel = new FileInputStream(dbFile).getChannel();
            outChannel = new FileOutputStream(file).getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (inChannel != null) {
                    inChannel.close();
                    inChannel = null;
                }
                if(outChannel != null){
                    outChannel.close();
                    outChannel = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
