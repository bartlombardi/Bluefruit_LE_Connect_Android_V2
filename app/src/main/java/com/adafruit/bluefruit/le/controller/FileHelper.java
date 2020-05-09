package com.adafruit.bluefruit.le.controller;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;
import com.adafruit.bluefruit.le.connect.R;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class FileHelper
{
    final static String path = "/mnt/ext_sdcard/codici_speechtovibration/";  // + "/codici_speechtovibration/" ; //.getAbsolutePath()
    final static String TAG = FileHelper.class.getName();

    public static String searchInFiles (String code, Context context)
    {
        final HashMap<File, String> filesName = new HashMap<File, String>();
        filesName.put(new File(path, "codici1.txt"),"1");
        filesName.put(new File(path, "codici2.txt"),"2");
        filesName.put(new File(path, "codici3.txt"),"3");
        filesName.put(new File(path, "codici4.txt"),"4");
        filesName.put(new File(path, "codici5.txt"),"5");

        code = code.replaceAll("\\s","");


        for (HashMap.Entry<File, String> idFile : filesName.entrySet())
        {

            File file = idFile.getKey();

            if(ReadFile(code, file, context))
                return idFile.getValue();
        }

        return "0";
    }

    private static boolean ReadFile(String code, File file, Context context)
    {
        String line = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();

            while ( (line = bufferedReader.readLine()) != null )
            {
                if(line.equals(code)) return true;
            }
            line = stringBuilder.toString();

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return false;
    }

}
