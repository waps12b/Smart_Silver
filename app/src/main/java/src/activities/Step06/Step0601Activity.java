package src.activities.Step06;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by jhobo_000 on 2015-09-06.
 */
public class Step0601Activity extends StageActivity {

    private int iRetryCount = 0;
    public boolean isRight = false;
    private ImageView mainimage;
    private ImageButton btn1, btn2;
    private ImageView[] box = new ImageView[10];

    public Step0601DataSet dataSet = new Step0601DataSet();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_06_01);

        btn1 = (ImageButton)findViewById(R.id.btn_answer_1);
        btn2 = (ImageButton)findViewById(R.id.btn_answer_2);

        box[0] = (ImageView)findViewById(R.id.bx1);
        box[1] = (ImageView)findViewById(R.id.bx2);
        box[2] = (ImageView)findViewById(R.id.bx3);
        box[3] = (ImageView)findViewById(R.id.bx4);
        box[4] = (ImageView)findViewById(R.id.bx5);
        box[5] = (ImageView)findViewById(R.id.bx6);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataSet.ans == 0) isRight = true;
                else isRight = false;
                checkAnswer();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataSet.ans == 1) isRight = true;
                else isRight = false;
                checkAnswer();
            }
        });

        setQuestion(false);
    }

    public synchronized void setQuestion(boolean isRetry, Object object) {
        dataSet.setData(iStage - 1);

        btn1.setImageResource(dataSet.imgbtn[0]);
        btn2.setImageResource(dataSet.imgbtn[1]);

        for(int i = 0 ; i < 6 ; i++)
        {
            if(i == dataSet.blk) box[i].setVisibility(View.INVISIBLE);
            else box[i].setVisibility(View.VISIBLE);

            if(dataSet.pb[i] != 0)
                box[i].setImageResource(dataSet.pb[i]);
        }

        startRecording();
    }

    public synchronized void checkAnswer(Object o) {
        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();
        countUpTry();
        if (isRight || iRetryCount > 1) stopRecording(isRight);

        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (isRight || iRetryCount > 1) {
                    iStage++;
                    iRetryCount = 0;
                    if (iStage <= NUM_OF_STAGE) setQuestion(false);
                    else goNext();
                } else {
                    iRetryCount++;
                }
            }
        });
    }

    public synchronized void goNext(Object object) {
        Intent intent = new Intent(this, Step0602Activity.class);
        startActivity(intent);
    }

    public class Step0601DataSet {
        private final int ansbtn[][] = {{1,1,1},{1,1,0},{1,1,1,},{1,1,1},{1,1,0}}; // 답 버튼
        private final int blank[] = {1,5,4,5,1};
        private final int prob[][][] = {{{R.drawable.icon_8_1_3, R.drawable.icon_8_1_3, R.drawable.icon_8_1_3, R.drawable.icon_8_1_2, R.drawable.icon_8_1_3, R.drawable.icon_8_1_2,},
                {R.drawable.icon_8_1_3, R.drawable.icon_8_1_3, R.drawable.icon_8_1_3, R.drawable.icon_8_1_4, R.drawable.icon_8_1_3, R.drawable.icon_8_1_4},
                {R.drawable.icon_8_1_8, R.drawable.icon_8_1_3, R.drawable.icon_8_1_8, R.drawable.icon_8_1_6, R.drawable.icon_8_1_8, R.drawable.icon_8_1_6}},

                {{R.drawable.icon_8_1_3, R.drawable.icon_8_1_1, R.drawable.icon_8_1_3, R.drawable.icon_8_1_1, R.drawable.icon_8_1_3, R.drawable.icon_8_1_3},
                        {R.drawable.icon_8_1_3, R.drawable.icon_8_1_6, R.drawable.icon_8_1_3, R.drawable.icon_8_1_6, R.drawable.icon_8_1_3, R.drawable.icon_8_1_3},
                        {R.drawable.icon_8_1_8, R.drawable.icon_8_1_6, R.drawable.icon_8_1_3, R.drawable.icon_8_1_8, R.drawable.icon_8_1_6, R.drawable.icon_8_1_3}},

                {{R.drawable.icon_8_1_3, R.drawable.icon_8_1_2, R.drawable.icon_8_1_5,R.drawable.icon_8_1_3, R.drawable.icon_8_1_3, R.drawable.icon_8_1_5,},
                        {R.drawable.icon_8_1_3, R.drawable.icon_8_1_6, R.drawable.icon_8_1_5, R.drawable.icon_8_1_3, R.drawable.icon_8_1_3, R.drawable.icon_8_1_5,},
                        {R.drawable.icon_8_1_8, R.drawable.icon_8_1_6, R.drawable.icon_8_1_3,R.drawable.icon_8_1_8, R.drawable.icon_8_1_3, R.drawable.icon_8_1_3,},},

                {{R.drawable.icon_8_1_4, R.drawable.icon_8_1_2, R.drawable.icon_8_1_5, R.drawable.icon_8_1_4, R.drawable.icon_8_1_2, R.drawable.icon_8_1_5, },
                        {R.drawable.icon_8_1_4, R.drawable.icon_8_1_6, R.drawable.icon_8_1_5, R.drawable.icon_8_1_4, R.drawable.icon_8_1_6, R.drawable.icon_8_1_5, },
                        {R.drawable.icon_8_1_4, R.drawable.icon_8_1_8, R.drawable.icon_8_1_7, R.drawable.icon_8_1_4, R.drawable.icon_8_1_8, R.drawable.icon_8_1_7}},

                {{R.drawable.icon_8_1_4, R.drawable.icon_8_1_2, R.drawable.icon_8_1_5,R.drawable.icon_8_1_4, R.drawable.icon_8_1_2, R.drawable.icon_8_1_5,},
                        {R.drawable.icon_8_1_4, R.drawable.icon_8_1_6, R.drawable.icon_8_1_5,R.drawable.icon_8_1_4, R.drawable.icon_8_1_6, R.drawable.icon_8_1_5,},
                        {R.drawable.icon_8_1_6, R.drawable.icon_8_1_8, R.drawable.icon_8_1_7,R.drawable.icon_8_1_6, R.drawable.icon_8_1_8, R.drawable.icon_8_1_7,}}};

        private final int arrImgBtn[][][] ={
                {{R.drawable.icon_8_1_3, R.drawable.icon_8_1_2}, {R.drawable.icon_8_1_3,R.drawable.icon_8_1_4}, {R.drawable.icon_8_1_8, R.drawable.icon_8_1_6}},
                {{R.drawable.icon_8_1_3, R.drawable.icon_8_1_1}, {R.drawable.icon_8_1_3,R.drawable.icon_8_1_6}, {R.drawable.icon_8_1_3, R.drawable.icon_8_1_8}},
                {{R.drawable.icon_8_1_3, R.drawable.icon_8_1_2}, {R.drawable.icon_8_1_3,R.drawable.icon_8_1_6}, {R.drawable.icon_8_1_8, R.drawable.icon_8_1_6}},
                {{R.drawable.icon_8_1_4, R.drawable.icon_8_1_5}, {R.drawable.icon_8_1_4, R.drawable.icon_8_1_5}, {R.drawable.icon_8_1_4,R.drawable.icon_8_1_7}},
                {{R.drawable.icon_8_1_6, R.drawable.icon_8_1_2}, {R.drawable.icon_8_1_4, R.drawable.icon_8_1_6}, {R.drawable.icon_8_1_8,R.drawable.icon_8_1_6}}
        };

        int ans;
        int imgbtn[] = new int[10];
        int blk;
        int pb[] = new int[10];

        public void setData(int iStage) {
            int rand = (int)(Math.random() * 3.0); // 0 ~ 2

            ans = ansbtn[iStage][rand];
            imgbtn = arrImgBtn[iStage][rand];
            blk = blank[iStage];
            pb = prob[iStage][rand];
        }
    }
}