package src;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;

import src.activities.Step01.*;
import src.activities.Step02.*;
import src.activities.Step03.*;
import src.activities.Step04.*;
import src.activities.Step05.*;
import src.activities.Step06.*;
import src.activities.Step07.*;
import src.activities.Step10.*;


/**
 * Created by waps12b on 15. 3. 18..
 */
public class Utility {

    public static boolean isNumber(String strNumber){
        try
        {
            long lNum = Long.parseLong(strNumber);
            return true;
        }catch (Exception ex)
        {
            return false;
        }
    }

    public static void swap(Object a, Object b)
    {
        Object t = a;
        a = b;
        b = t;
    }

    /**
     * 디렉토리 생성
     * @return dir
     */
    public static  File makeDirectory(String dir_path){
        File dir = new File(dir_path);
        if (!dir.exists())
        {
            dir.mkdirs();

            Log.i( "FILE IO" , "!dir.exists" );
        }else{
            Log.i("FILE IO", "dir.exists");
        }

        return dir;
    }

    /**
     * 파일 생성
     * @param dir
     * @return file
     */
    public static File makeFile(File dir , String file_path){
        File file =  new File(file_path);;
        boolean isSuccess = false;
        if(!file.exists()) {
            Log.i("FILE IO", "!file.exists");
            try {
                isSuccess = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                Log.i("FILE IO", "파일생성 여부 = " + isSuccess);
            }
        }
        return file;
    }


    /**
     * 디렉토리에 안에 내용을 보여 준다.
     * @param
     * @return string[]
     */
    public static String[] getList(File dir){
        if(dir!=null&&dir.exists())
            return dir.list();
        return null;
    }

    /**
     * 파일에 내용 쓰기
     * @param file
     * @param file_content
     * @return
     */
    public static boolean writeFile(File file , byte[] file_content){
        boolean result;
        FileOutputStream fos;
        if(file!=null&&file.exists()&&file_content!=null){
            try {
                fos = new FileOutputStream(file);
                try {
                    fos.write(file_content);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            result = true;
        }else{
            result = false;
        }
        return result;
    }

    /**
     * 파일 읽어 오기
     * @param file
     */
    public static byte[] readFile(File file){
        int readcount=0;
        byte[] buffer = null;
        if(file!=null&&file.exists()){
            try {
                FileInputStream fis = new FileInputStream(file);
                readcount = (int)file.length();
                buffer = new byte[readcount];
                fis.read(buffer);
                for(int i=0 ; i<file.length();i++){
                    Log.d("FILE IO", ""+buffer[i]);
                }
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
                buffer = null;
            }
        }
        return buffer;
    }









    public static final Class<?> getStepClass(int iStep, int iLevel)
    {
        return Setting.arrStepClass[iStep-1][iLevel-1];
    }

    public static final int getStep(Class<?> classStep)
    {
        for(int i=0; i< Setting.arrStepClass.length; i++)
        {
            for(int j=0; j< Setting.arrStepClass[i].length; j++)
            {
                if(classStep == Setting.arrStepClass[i][j])
                    return i+1;
            }


        }
        return 0;
    }

    public static final int getLevel(Class<?> classStep)
    {
        for(int i=0; i< Setting.arrStepClass.length; i++)
        {
            for(int j=0; j< Setting.arrStepClass[i].length; j++)
            {
                if(classStep ==  Setting.arrStepClass[i][j])
                    return j+1;
            }
        }
        return 0;
    }

    public static final int getNumberOfStage(Class<?> classLevel){
        for(int i=0; i< Setting.arrStepClass.length; i++)
        {
            for(int j=0; j< Setting.arrStepClass[i].length; j++)
            {
                if(classLevel == Setting.arrStepClass[i][j])
                    return Setting.arrNumOfStage[i][j];
            }
        }
        return -1;
    }

    public static final int getNumberOfLevel(int iStep){
        return Setting.arrStepClass[iStep - 1].length;
    }
}
