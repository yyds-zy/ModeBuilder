package com.tencent.modebuilder.util;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.tencent.modebuilder.MyApplication;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileUtils {

    public static final String savePath = "/ModeFile";
    private static String filePath = null;

    public static boolean createJsonFile(String jsonData, String fileName) {
        filePath = getFilePath(MyApplication.application);
        if (null == filePath) {
            return false;
        }
        boolean flag = true;
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();//创建父路径
        }

        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            fos = new FileOutputStream(filePath + fileName);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(jsonData);
        } catch (FileNotFoundException e) {
            flag = false;
            e.printStackTrace();
        } catch (IOException e) {
            flag = false;
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();//关闭缓冲流
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    private static String getFilePath(Context context) {
        File dir = new File(context.getExternalFilesDir(null).getAbsolutePath() + savePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.getPath();//存当前app的文件夹里
    }


    public static String readJson(String filePath) {
        File file = new File(filePath);
        BufferedReader reader = null;
        StringBuffer data = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while ((temp = reader.readLine()) != null) {
                data.append(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data.toString();
    }

    public static File uriToFileApiQ(Uri uri, Context context) {
        File file = null;
        if (uri == null) return file;
        //android10以上转换
        if (uri.getScheme().equals(ContentResolver.SCHEME_FILE)) {
            file = new File(uri.getPath());
        } else if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            //把文件复制到沙盒目录
            ContentResolver contentResolver = context.getContentResolver();
            String displayName = System.currentTimeMillis() + Math.round((Math.random() + 1) * 1000)
                    + "." + MimeTypeMap.getSingleton().getExtensionFromMimeType(contentResolver.getType(uri));

            try {
                InputStream is = contentResolver.openInputStream(uri);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;//宽高压缩为原来的1/2
                options.inPreferredConfig = Bitmap.Config.ARGB_4444;
                Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);

                File cache = new File(context.getCacheDir().getAbsolutePath(), displayName);
                FileOutputStream fos = new FileOutputStream(cache);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);

                android.os.FileUtils.copy(is, fos);
                file = cache;
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
