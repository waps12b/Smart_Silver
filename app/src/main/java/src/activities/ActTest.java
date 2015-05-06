package src.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cdmst.smartsilver.R;

import src.activities.Step01.*;
import src.activities.Step02.*;
import src.activities.Step03.*;
import src.activities.Step04.*;


/**
 * Created by waps12b on 15. 4. 15..
 */
public class ActTest extends FrameActivity {

    Button btnStep1_1;
    Button btnStep1_2;
    Button btnStep1_3;
    Button btnStep1_4;
    Button btnStep2_1;
    Button btnStep2_2;
    Button btnStep2_3;
    Button btnStep2_4;
    Button btnStep2_5;
    Button btnStep3_1;
    Button btnStep3_2;
    Button btnStep3_3;
    Button btnStep3_4;
    Button btnStep3_5;
    Button btnStep4_1;
    Button btnStep4_2;
    Button btnStep4_3;
    Button btnStep4_4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_test);
        //.setOnClickListener(clickBtnStep
        btnStep1_1 = (Button)findViewById(R.id.btn_step_1_1);
        btnStep1_1.setOnClickListener(clickBtnStep);

        btnStep1_2 = (Button)findViewById(R.id.btn_step_1_2);
        btnStep1_2.setOnClickListener(clickBtnStep);

        btnStep1_3 = (Button)findViewById(R.id.btn_step_1_3);
        btnStep1_3.setOnClickListener(clickBtnStep);

        btnStep1_4 = (Button)findViewById(R.id.btn_step_1_4);
        btnStep1_4.setOnClickListener(clickBtnStep);

        btnStep2_1 = (Button)findViewById(R.id.btn_step_2_1);
        btnStep2_1.setOnClickListener(clickBtnStep);

        btnStep2_1 = (Button)findViewById(R.id.btn_step_2_1);
        btnStep2_1.setOnClickListener(clickBtnStep);

        btnStep2_2 = (Button)findViewById(R.id.btn_step_2_2);
        btnStep2_2.setOnClickListener(clickBtnStep);

        btnStep2_3 = (Button)findViewById(R.id.btn_step_2_3);
        btnStep2_3.setOnClickListener(clickBtnStep);

        btnStep2_4 = (Button)findViewById(R.id.btn_step_2_4);
        btnStep2_4.setOnClickListener(clickBtnStep);

        btnStep2_5 = (Button)findViewById(R.id.btn_step_2_5);
        btnStep2_5.setOnClickListener(clickBtnStep);

        btnStep3_1 = (Button)findViewById(R.id.btn_step_3_1);
        btnStep3_1.setOnClickListener(clickBtnStep);

        btnStep3_2 = (Button)findViewById(R.id.btn_step_3_2);
        btnStep3_2.setOnClickListener(clickBtnStep);

        btnStep3_3 = (Button)findViewById(R.id.btn_step_3_3);
        btnStep3_3.setOnClickListener(clickBtnStep);

        btnStep3_4 = (Button)findViewById(R.id.btn_step_3_4);
        btnStep3_4.setOnClickListener(clickBtnStep);

        btnStep3_5 = (Button)findViewById(R.id.btn_step_3_5);
        btnStep3_5.setOnClickListener(clickBtnStep);

        btnStep4_1 = (Button)findViewById(R.id.btn_step_4_1);
        btnStep4_1.setOnClickListener(clickBtnStep);

        btnStep4_2 = (Button)findViewById(R.id.btn_step_4_2);
        btnStep4_2.setOnClickListener(clickBtnStep);

        btnStep4_3 = (Button)findViewById(R.id.btn_step_4_3);
        btnStep4_3.setOnClickListener(clickBtnStep);

        btnStep4_4 = (Button)findViewById(R.id.btn_step_4_4);
        btnStep4_4.setOnClickListener(clickBtnStep);
    }

    View.OnClickListener clickBtnStep = new View.OnClickListener()
    {
        public void onClick(View v)
        {

            if(v == btnStep1_1)
            {

                Intent intent = new Intent(v.getContext(), ActStep01.class);
                startActivity(intent);
            }else if(v == btnStep1_2)
            {

                Intent intent = new Intent(v.getContext(), ActStep0102.class);
                startActivity(intent);
            }else if(v == btnStep1_3)
            {

                Intent intent = new Intent(v.getContext(), ActStep0103.class);
                startActivity(intent);
            }else if(v == btnStep1_4)
            {
                Intent intent = new Intent(v.getContext(), ActStep0104.class);
                startActivity(intent);
            }
            else if(v == btnStep2_1)
            {

                Intent intent = new Intent(v.getContext(), ActStep0201.class);
                startActivity(intent);
            }else if(v == btnStep2_2)
            {

                Intent intent = new Intent(v.getContext(), ActStep0202.class);
                startActivity(intent);
            }else if(v == btnStep2_3)
            {

                Intent intent = new Intent(v.getContext(), ActStep0203.class);
                startActivity(intent);
            }else if(v == btnStep2_4)
            {

                Intent intent = new Intent(v.getContext(), ActStep0204.class);
                startActivity(intent);
            }
            else if(v == btnStep2_2)
            {

                Intent intent = new Intent(v.getContext(), ActStep0202.class);
                startActivity(intent);
            }
            else if(v == btnStep2_3)
            {

                Intent intent = new Intent(v.getContext(), ActStep0203.class);
                startActivity(intent);
            }
            else if(v == btnStep2_4)
            {

                Intent intent = new Intent(v.getContext(), ActStep0204.class);
                startActivity(intent);
            }
            else if(v == btnStep2_5)
            {

                Intent intent = new Intent(v.getContext(), ActStep0205.class);
                startActivity(intent);
            }
            else if(v == btnStep3_1)
            {

                Intent intent = new Intent(v.getContext(), ActStep0301.class);
                startActivity(intent);
            }

            else if(v == btnStep3_2)
            {

                Intent intent = new Intent(v.getContext(), ActStep0302.class);
                startActivity(intent);
            }
            else if(v == btnStep3_3)
            {

                Intent intent = new Intent(v.getContext(), ActStep0303.class);
                startActivity(intent);
            }
            else if(v == btnStep3_4)
            {

                Intent intent = new Intent(v.getContext(), ActStep0304.class);
                startActivity(intent);
            }
            else if(v == btnStep3_5) {

                Intent intent = new Intent(v.getContext(), ActStep0305.class);
                startActivity(intent);
            }
            else if(v == btnStep4_1)
            {

                Intent intent = new Intent(v.getContext(), ActStep0401.class);
                startActivity(intent);
            }
            else if(v == btnStep4_2)
            {

                Intent intent = new Intent(v.getContext(), ActStep0402.class);
                startActivity(intent);
            }
            else if(v == btnStep4_3)
            {

                Intent intent = new Intent(v.getContext(), ActStep0403.class);
                startActivity(intent);
            }
            else if(v == btnStep4_4)
            {

                Intent intent = new Intent(v.getContext(), ActStep0404.class);
                startActivity(intent);
            }
        }
    };
}
