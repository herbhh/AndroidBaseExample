package com.bud.localdatastore;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * </br>
 * <p>
 * &nbsp
 *
 * @author: NIO
 * @since: 1.0.0, 6/28/20 3:45 PM
 * @version: 1.0.0
 */
public class FileStoreHelper {

    private static FileStoreHelper instance;
    private static Context mContext;

    public static FileStoreHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (FileStoreHelper.class) {
                if (instance == null) {
                    instance = new FileStoreHelper();
                    mContext = context.getApplicationContext();
                }
            }
        }
        return instance;
    }

    public void writeFile(String fileName, String fileContent) {
        if (TextUtils.isEmpty(fileContent)) {
            return;
        }
        try {
            // 创建FileOutputStream对象，
            // MODE_PRIVATE：为默认操作模式，代表该文件是私有数据，只能被应用本身访问，在该模式下，写入的内容会覆盖原文件的内容.
            // MODE_APPEND：模式会检查文件是否存在，存在就往文件追加内容，否则就创建新文件。
            // MODE_WORLD_READABLE：表示当前文件可以被其他应用读取；
            // MODE_WORLD_WRITEABLE：表示当前文件可以被其他应用写入。
            FileOutputStream fileOutputStream = mContext.openFileOutput(fileName, Context.MODE_APPEND);
            fileOutputStream.write(fileContent.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile(String fileName) {
        try {
            FileInputStream fileInputStream = mContext.openFileInput(fileName);
            byte[] buffer = new byte[1024];
            StringBuilder stringBuilder = new StringBuilder();
            while (fileInputStream.read(buffer) > -1) {
                stringBuilder.append(new String(buffer, 0, buffer.length));
            }
            fileInputStream.close();
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 往sdcard中写入文件
     *
     * @param fileName
     * @param fileContent
     */
    public void writeSDCard(String fileName, String fileContent) {
        if (!TextUtils.equals(Environment.getExternalStorageState(), Environment.MEDIA_MOUNTED)) {
            Toast.makeText(mContext, "sdcard 不可用", Toast.LENGTH_SHORT).show();
        }
        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            fileOutputStream.write(fileContent.getBytes());
//            fileOutputStream.close();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(fileContent);
            fileWriter.close();
        } catch (IOException e) {
            Toast.makeText(mContext, "写入数据失败!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 读取sdcard中的文件
     *
     * @param fileName
     */
    public String readSDCard(String fileName) {
        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        if (!file.exists()) {
            Toast.makeText(mContext, fileName + " 文件不存在!", Toast.LENGTH_SHORT).show();
            return "";
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String result = bufferedReader.readLine();
            bufferedReader.close();
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
