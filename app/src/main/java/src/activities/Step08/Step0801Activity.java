package src.activities.Step08;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cdmst.smartsilver.R;
import src.activities.StageActivity;
import src.dialogs.DlgResultMark;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by jhobo_000 on 2015-06-22.
 */
public class Step0801Activity extends StageActivity {

    private TextView txtDescription;
    private ImageView img;
    private Button btnAnswer[] = new Button[3];
    private boolean ans;
    private PhotoViewAttacher mAttacher;
    private static int Count = 0;
    public Step0801DataSet dataSet = new Step0801DataSet();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_step_08_01);

        txtDescription = (TextView)findViewById(R.id.txt_description);

        img = (ImageView)findViewById(R.id.img_6_2);

        btnAnswer[0] = (Button)findViewById(R.id.btn_ans_1);
        btnAnswer[1] = (Button)findViewById(R.id.btn_ans_2);
        btnAnswer[2] = (Button)findViewById(R.id.btn_ans_3);

        mAttacher = new PhotoViewAttacher(img);
        mAttacher.setScaleType(ImageView.ScaleType.FIT_XY);

        for(int i = 0; i < 3; i++){
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

        btnAnswer[0].setText(dataSet.btnTxt[0]);
        btnAnswer[1].setText(dataSet.btnTxt[1]);
        if(Seed != 0) {
            btnAnswer[2].setText(dataSet.btnTxt[2]);
            btnAnswer[2].setVisibility(View.VISIBLE);
        }
        else
            btnAnswer[2].setVisibility(View.GONE);

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
        Intent intent = new Intent(this, Step0802Activity.class);
        startActivity(intent);
    }
}
