package src.data;

import android.app.UiAutomation;
import android.os.Environment;
import android.text.format.Time;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by waps12b on 15. 3. 25..
 */
public class ResultData implements Serializable {



    private boolean isRecording;
    private long startTime ;

    public boolean isSuccess; //test result
    public long millisec;
    public final int iStep;
    public final int iLevel;
    public final int iStage;
    public final String userId;
    public int tryCount = 1;

    public String timestamp = null;




    public ResultData(int step, int level, int stage)
    {
        this(step, level, stage, null);
    }

    public ResultData(int step, int level, int stage, String userId){
        this.iStep = step;
        this.iStage = stage;
        this.iLevel = level;

        isRecording = false;
        millisec = 0;
        this.userId = userId;
    }
    public void setTryCount(int tryCount){
        this.tryCount = tryCount;
    }
    public int getTryCount(){
        return this.tryCount;
    }

    public long getMilliTime()
    {
        return millisec;
    }

    public void Start()
    {
        isRecording = true;
        startTime = System.currentTimeMillis();

    }

    public void Stop(boolean bResult)
    {
        isRecording = false;
        isSuccess = bResult;
        millisec += System.currentTimeMillis() - startTime;
    }

    @Override
    public String toString(){
        // +" ( id INTEGER PRIMARY KEY AUTOINCREMENT, step INTEGER, level INTEGER, stage INTEGER, second INTEGER, success INTEGER, timestamp DATE, user_id TEXT);");
        if(userId != null){
            return String.format(" (null, '%d', '%d', '%d', '%d', '%d', " + DB.SQL_TIME_NOW + ", null ) " , iStep, iLevel, iStage, millisec, (isSuccess ? tryCount : -tryCount));
        }else{
            return String.format(" (null, '%d', '%d', '%d', '%d', '%d', " + DB.SQL_TIME_NOW + " , %s ) ", iStep, iLevel, iStage, millisec, (isSuccess ? tryCount : -tryCount), userId);
        }
    }

}
