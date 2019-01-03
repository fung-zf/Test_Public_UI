package com.xzkydz.function.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 *
 * 数据库备份还原类
 *
 * 备份操作
 * new DbBackups(this).execute("backupDatabase");
 * 还原操作
 * new DbBackups(this).execute("restroeDatabase");
 *
 */

public class DbBackups extends AsyncTask<String, Void, Integer> {
    public static final String COMMAND_BACKUP = "backupDatabase";
    public static final String COMMAND_RESTORE = "restroeDatabase";
    private static final int BACKUP_SUCCESS = 1;
    public static final int RESTORE_SUCCESS = 2;
    private static final int BACKUP_ERROR = 3;
    public static final int RESTORE_NOFLEERROR = 4;
    private Context myContext;
    private String appName ;

    public DbBackups(Context context , String appName) {
        this.myContext = context;
        this.appName = appName;
    }

    @Override
    protected Integer doInBackground(String... params) {

        String pathDatabase= myContext.getDatabasePath("database.db").getPath();
        // 需要备份的数据库路径
        File dbFile = new File(pathDatabase);
        // 创建数据库目录路径
        String local_file = Environment.getExternalStorageDirectory().getAbsolutePath() + "/.xzkydz/backup/"+appName+"/";
        File exportDir = new File(local_file);
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        File backup = new File(exportDir, dbFile.getName());

        String command = params[0];
        if (command.equals(COMMAND_BACKUP)) {
            try {
                backup.createNewFile();
                fileCopy(dbFile, backup);
                return BACKUP_SUCCESS;
            } catch (Exception e) {
                Log.e("DbBackups", "数据库备份异常:" + e.getMessage());
                return BACKUP_ERROR;
            }
        } else if (command.equals(COMMAND_RESTORE)) {
            try {
                fileCopy(backup, dbFile);
                return RESTORE_SUCCESS;
            } catch (Exception e) {
                Log.e("DbBackups", "数据库还原异常" + e.getMessage());
                return RESTORE_NOFLEERROR;
            }
        } else {
            return null;
        }
    }

    private void fileCopy(File dbFile, File backup) throws IOException {
        FileChannel inChannel = new FileInputStream(dbFile).getChannel();
        FileChannel outChannel = new FileOutputStream(backup).getChannel();
        try {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e) {
            Log.e("DbBackups", "数据库文件操作异常" + e.getMessage());
        } finally {
            if (inChannel != null) {
                inChannel.close();
            }
            if (outChannel != null) {
                outChannel.close();
            }
        }
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        switch (result) {
            case BACKUP_SUCCESS:
                Log.d("数据库备份", "成功");
                Toast.makeText(myContext, "数据库备份成功", Toast.LENGTH_SHORT).show();
                break;
            case BACKUP_ERROR:
                Log.d("数据库备份", "失败");
                Toast.makeText(myContext, "数据库备份失败", Toast.LENGTH_SHORT).show();
                break;
            case RESTORE_SUCCESS:
                Log.d("数据库还原", "成功");
                Toast.makeText(myContext, "数据库还原成功", Toast.LENGTH_SHORT).show();
                break;
            case RESTORE_NOFLEERROR:
                Log.d("数据库还原", "失败");
                Toast.makeText(myContext, "数据库还原失败", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

}
