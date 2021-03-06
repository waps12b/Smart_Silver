package src.activities.Step06;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.activities.Step08.Step0801Activity;
import src.dialogs.DlgResultMark;

/**
 * Created by jhobo_000 on 2015-05-06.
 */
public class Step0605Activity extends StageActivity {

    private TextView txtDescription;
    private ImageView img;
    private Button btnAnswer[] = new Button[2];
    private boolean ans;
    private static int Count = 0;
    public Step0605DataSet dataSet = new Step0605DataSet();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_06_05);

        txtDescription = (TextView)findViewById(R.id.txt_description);

        img = (ImageView)findViewById(R.id.img_6_1);

        btnAnswer[0] = (Button)findViewById(R.id.btn_ans_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_ans_2);

        for(int i = 0; i < 2; i++){
            btnAnswer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Button btnCurrentButton = (Button)v;
                    String iSelectAnswer = btnCurrentButton.getText().toString();

                    if(iSelectAnswer.equals(dataSet.strAns)) ans = true;
                    else ans = false;

                    checkAnswer();
                }
            });
        } // end of button listener

        setQuestion(false);
    }


    @Override
    public synchronized void setQuestion(boolean isRetry, Object object) {
        int Seed = iStage - 1;

        dataSet.setData(Seed);

        txtDescription.setText(dataSet.Description);
        img.setImageResource(dataSet.img);

        // btn set

        btnAnswer[0].setText(dataSet.txtBtn1);
        btnAnswer[1].setText(dataSet.txtBtn2);

        startRecording();
    }

    @Override
    public synchronized void checkAnswer(Object object) {
        DlgResultMark dlg = new DlgResultMark(this, ans);
        dlg.show();

        countUpTry();

        if(ans || Count > 1) stopRecording(ans);

        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(ans || Count > 1){
                    iStage++;
                    if(iStage > NUM_OF_STAGE) goNext();
                    else {
                        Count = 0;
                        setQuestion(false);
                    }
                }
                else{
                    Count++;
                }
            }
        });
    }

    @Override
    public synchronized void goNext(Object object) {
        Intent intent = new Intent(this, Step0801Activity.class);
        startActivity(intent);
    }
 }