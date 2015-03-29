package src.activities.Step03;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;

import cdmst.smartsilver.R;
import src.activities.FrameActivity;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-03-27.
 */

public class ActStep0301 extends StageActivity {
    private TextView txtProcess[] = new TextView[3];
    private TextView txtAnswer[] = new TextView[4];
    private Button btnAnswer[] = new Button[3];

    private int iStage = 0;
    private int iRetryCount = 0;
    public boolean isRight = false;
    public int iProcess = 0;
    public int iCurrentResult = 0;
    public int iNextResult = 0;

    public Step0301DataSet processSet = new Step0301DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_03_1);

        txtProcess[0] = (TextView)findViewById(R.id.txt_process_1);
        txtProcess[1] = (TextView)findViewById(R.id.txt_process_2);
        txtProcess[2] = (TextView)findViewById(R.id.txt_process_3);
        txtAnswer[0] = (TextView)findViewById(R.id.txt_answer_1);
        txtAnswer[1] = (TextView)findViewById(R.id.txt_answer_2);
        txtAnswer[2] = (TextView)findViewById(R.id.txt_answer_3);
        txtAnswer[3] = (TextView)findViewById(R.id.txt_answer_4);
        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_answer_3);

        //button listener
        for(int i = 0; i < 3; i++){
            btnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Button btnCurrentButton = (Button)v;
                    int iSelectAnswer = Integer.parseInt(btnCurrentButton.getText().toString());
                    if(iSelectAnswer == iNextResult) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });
        } // end of button listener

        setQuestion(false);
    }


    public void setQuestion(boolean isRetry, Object object){
        if(!isRetry) {
            int iRandomSeed = 2 * iStage + rand.nextInt(2);
            processSet.setData(iRandomSeed);
        }

        //delete last process
        for(int i = 0; i < 4; i++)
            txtAnswer[i].setText("");

        //set process
        String sSign = iStage < 4? "+" : "";
        for(int i = 0; i < 3; i++)
            txtProcess[i].setText(sSign + processSet.arrProcess[i]);

        //set button
        int iChangeCount = 0, iButtonValue = processSet.startNumber;
        boolean isChanged[] = new boolean[3];
        while(iChangeCount < 3){
            int index = rand.nextInt(3);
            if(isChanged[index]) continue;
            iButtonValue += processSet.arrProcess[iChangeCount++];
            btnAnswer[index].setText("" + iButtonValue);
            isChanged[index] = true;
        }

        iProcess = 0;
        iNextResult = processSet.startNumber;
        setNextProcess();
    }

    public void setNextProcess(){
        txtAnswer[iProcess].setText("" + iNextResult);
        iCurrentResult = iNextResult;

        if(iProcess < 3)
            iNextResult = iCurrentResult + processSet.arrProcess[iProcess];
    }


    public void checkAnswer(Object object){
        if(isRight){
            iProcess++;
            setNextProcess();

            if(iProcess > 2){
                txtAnswer[iProcess].setText("" + iNextResult);
                DlgResultMark dlg = new DlgResultMark(this, isRight);
                dlg.show();

                dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if(iStage >= 7) goNext();
                        else {
                            iRetryCount = 0;
                            iStage++;
                            setQuestion(false);
                        }
                    }
                });
            }
        }//isRight

        else{
            DlgResultMark dlg = new DlgResultMark(this, isRight);
            dlg.show();

            dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    if(iRetryCount > 1){
                        iRetryCount = 0;
                        iStage++;
                        setQuestion(false);
                    }
                    else{
                        iRetryCount++;
                        setQuestion(true);
                    }
                }
            });
        }// !isRight
    }

    public void goNext(Object object){
        Intent intent = new Intent(this, ActStep0303.class);
        startActivity(intent);
    }
}
