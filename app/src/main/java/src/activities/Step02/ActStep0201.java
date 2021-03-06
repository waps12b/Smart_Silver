package src.activities.Step02;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.HashMap;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;

/**
 * Created by Acka on 2015-04-15.
 */
public class ActStep0201 extends StageActivity {

    private static final int ROW_COUNT = 4;
    private static final int COLUMN_COUNT = 10;

    private TextView txtDescription;
    private Button btnSingleCell[] = new Button[ROW_COUNT*COLUMN_COUNT];
    private FrameLayout frameLayout[] = new FrameLayout[ROW_COUNT*COLUMN_COUNT];
    private HashMap<View, Integer> mapIndex = new HashMap<View, Integer>();
    private boolean[] isSelected = new boolean[ROW_COUNT * COLUMN_COUNT];


    private GridLayout gridLayout;
    private Button btnSubmit;

    private int iRetryCount = 0;
    private int iNextAnswer = -1;
    private int iLastButton = 49;


    public final String arrDescription[] = {
            "다음 수를 50에서 3씩 빼기.\n해당 숫자를 누르세요.",
            "다음 수를 50에서 5씩 빼기.\n해당 숫자를 누르세요.",
            "다음 수를 100에서 빼기.\n해당 숫자를 누르세요.",
            "다음 수를 100에서 빼기.\n해당 숫자를 누르세요.",
            "다음 수를 70에서 7씩 빼기.\n해당 숫자를 누르세요."
    };
    private final int arrStartNumber[] =        {50, 50, 100, 100, 70};
    private final int arrRowCount[] =           {3, 3, 2, 3, 3};
    private final int arrDistanceNumber[] =     {3, 5, 5, 7, 7};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_02_1);

        gridLayout = (GridLayout)findViewById(R.id.grid);
        txtDescription = (TextView)findViewById(R.id.txt_description);
        btnSubmit = (Button)findViewById(R.id.btn_submit);

        for(int i = 0 ; i < ROW_COUNT * COLUMN_COUNT ; i ++)
        {
            frameLayout[i]  = (FrameLayout)gridLayout.getChildAt(i);
            btnSingleCell[i] = (Button)frameLayout[i].getChildAt(0);
            mapIndex.put(btnSingleCell[i], i);
            btnSingleCell[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(v);
                }
            });
        }
        setQuestion(false);
    }


    public synchronized void setCheck(Button btn){

        btn.setBackgroundResource(R.drawable.dialog_correct_cell);

        isSelected[mapIndex.get(btn)] = true;
    }

    public synchronized void setQuestion(boolean isRetry, Object object){
        int iVal = arrStartNumber[iStage-1];
        int iDistance = arrDistanceNumber[iStage-1];
        txtDescription.setText(arrDescription[iStage-1]);
        int idxLast = ROW_COUNT * COLUMN_COUNT -1;

        for(int i=0;i<=idxLast;i++){
            btnSingleCell[i].setBackgroundColor(getResources().getColor( R.color.transparent));
            btnSingleCell[i].setText(String.valueOf(iVal - i));
        }



        iNextAnswer = 2*iDistance ;
        iLastButton = arrRowCount[iStage-1] * 10 -1;
        for(int i=0;i<=idxLast;i++)
        {
            isSelected[i] = false;
            if(i <= iLastButton)
                frameLayout[i].setVisibility(View.VISIBLE);
            else
                frameLayout[i].setVisibility(View.GONE);
        }
        setCheck(btnSingleCell[0]);
        setCheck(btnSingleCell[iDistance]);
        iRetryCount = 0;
        frameLayout[0].setBackgroundColor(Color.GRAY);
        startRecording();
    }

    //123123
    public synchronized void checkAnswer(Object o){
        int iSelected = mapIndex.get(o);
        if(isSelected[iSelected])
            return;


        boolean isRight;
        if(iNextAnswer == iSelected){
            setCheck( btnSingleCell[iSelected] );
            iNextAnswer += arrDistanceNumber[iStage-1];
            if(iNextAnswer <= iLastButton)
                return;
            isRight = true;
        }else{
            isRight = false;
            iRetryCount ++;
        }
        countUpTry();
        if(iRetryCount >= 3 || iNextAnswer > iLastButton) {
            stopRecording(isRight);
            if(++iStage <= 5)
                setQuestion(false);
            else
                goNext();
        }

        DlgResultMark dlg = new DlgResultMark(this, isRight);
        dlg.show();
    }

    public void goNext(Object object){
        Intent intent = new Intent(this, ActStep0202.class);
        startActivity(intent);
    }

}
