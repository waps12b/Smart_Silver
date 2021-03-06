package src.activities.Step02;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.activities.Step03.ActStep0301;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-04-23.
 */
public class ActStep0205 extends StageActivity{
    private final ImageView imgNumberField[] = new ImageView[4];
    private final TextView txtNumber[] = new TextView[4];
    private final TextView txtOperator[] = new TextView[2];
    private final Button btnAnswer[] = new Button[3];

    private int iRetryCount = 0;
    public boolean isRight = false;

    public Step0205DataSet dataSet = new Step0205DataSet();
    private Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_02_5);

        imgNumberField[0] = (ImageView)findViewById(R.id.img_operand_field_1);
        imgNumberField[1] = (ImageView)findViewById(R.id.img_operand_field_2);
        imgNumberField[2] = (ImageView)findViewById(R.id.img_operand_field_3);
        imgNumberField[3] = (ImageView)findViewById(R.id.img_answer_field);
        txtNumber[0] = (TextView)findViewById(R.id.txt_operand_1);
        txtNumber[1] = (TextView)findViewById(R.id.txt_operand_2);
        txtNumber[2] = (TextView)findViewById(R.id.txt_operand_3);
        txtNumber[3] = (TextView)findViewById(R.id.txt_answer);
        txtOperator[0] = (TextView)findViewById(R.id.txt_operator_1);
        txtOperator[1] = (TextView)findViewById(R.id.txt_operator_2);
        btnAnswer[0] = (Button)findViewById(R.id.btn_answer_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_answer_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_answer_3);

        for(int i = 0; i < 3; i++)
            btnAnswer[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(Integer.parseInt(((Button) v).getText().toString()) == dataSet.iNumberSet[dataSet.iEmptyIndex]) isRight = true;
                    else isRight = false;
                    checkAnswer();
                }
            });

        setQuestion(false);
    }

    public synchronized void setQuestion(boolean isRetry, Object object){
        dataSet.setData(iStage);

        for(int i = 0; i < 4; i++){
            if(i == dataSet.iEmptyIndex){
                imgNumberField[i].setVisibility(View.VISIBLE);
                txtNumber[i].setText("");
            }
            else{
                imgNumberField[i].setVisibility(View.INVISIBLE);
                txtNumber[i].setText("" + dataSet.iNumberSet[i]);
            }
        }

        int iNextExample = dataSet.iNumberSet[dataSet.iEmptyIndex] - rand.nextInt(2);
        if(iNextExample < 0) iNextExample = 0;
        if(iNextExample > 7) iNextExample = 7;

        for(int i = 0; i < 3; i++)
            btnAnswer[i].setText("" + (iNextExample + i));

        startRecording();
    }

    public synchronized void checkAnswer(Object o){
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();
        countUpTry();
        if(isRight || iRetryCount > 1) stopRecording(isRight);

        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(isRight || iRetryCount > 1){
                    iStage++;
                    iRetryCount = 0;
                    if(iStage <= NUM_OF_STAGE) setQuestion(false);
                    else goNext();
                }
                else{
                    iRetryCount++;
                }
            }
        });
    }

    public synchronized void goNext(Object object){
        Intent intent = new Intent(this, ActStep0301.class);
        startActivity(intent);
    }

    public class Step0205DataSet {
        private final boolean arrExceedTen[] = {false, true, false, true, true};
        private final int arrEmptyIndex[] = {2, 2, 1, 1, 0};
        private final int arrOperator[] = {1, 1, -1};

        public int iNumberSet[] = new int[4];
        public int iEmptyIndex;

        public void setData(int iStage) {
            int iSeed = iStage - 1;
            boolean bExeedTen;

            iEmptyIndex = arrEmptyIndex[iSeed];

            do {
                bExeedTen = false;
                iNumberSet[3] = 0;

                for (int i = 0; i < 3; i++) {
                    iNumberSet[i] = 1 + rand.nextInt(9);
                    iNumberSet[3] += arrOperator[i] * iNumberSet[i];
                    if(iNumberSet[3] >= 10) bExeedTen = true;
                }
            } while (iNumberSet[3] < 0 || bExeedTen != arrExceedTen[iSeed]);
        }
    }
}
