package src.activities.ResultPage;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import cdmst.smartsilver.R;
import src.data.DB;
import src.data.ResultData;
import src.activities.FrameActivity;

/**
 * Created by waps12b on 15. 7. 9..
 */
public class StepAllStatisticsActivity extends FrameActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_statistics_step_all);

        TableLayout tableStep = (TableLayout)findViewById(R.id.table_result);
        for(int iStep=1; iStep<=10; iStep++)
        {
            TableRow rowLevel = (TableRow)tableStep.getChildAt(iStep);
            for(int iLevel =1; iLevel<=5; iLevel ++ )
            {
                TextView txtTitle = (TextView)rowLevel.getChildAt(iLevel);

                ResultData[] res = DB.getResultData( String.format("SELECT * FROM %s WHERE step = '%d' AND level = '%d' ORDER BY stage DESC LIMIT 1 ;", DB.TABLE_RESULT, iStep, iLevel) );
                if(res == null || res.length == 0) continue;

                if( res[0].iStage == 5)
                {
                    txtTitle.setBackgroundColor( getResources().getColor( R.color.result_done));
                }else
                {
                    txtTitle.setBackgroundColor( getResources().getColor(R.color.result_doing));
                }

            }

        }
    }

    //view.setBackgroundColor( getResources().getColor( R.color.result_success));
    //view.setBackgroundColor( getResources().getColor( R.color.result_success));

}