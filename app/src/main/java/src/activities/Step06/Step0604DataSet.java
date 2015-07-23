package src.activities.Step06;

import cdmst.smartsilver.R;

/**
 * Created by jhobo_000 on 2015-06-24.
 */
public class Step0604DataSet {
    public int img;
    public String Discription;
    public String btnTxt[];
    public String strAns;
    public int iAns;

    public void setData(int iSeed) {
        Discription = DiscriptionList[iSeed];
        img = imgList[iSeed];
        btnTxt = txtBtnList[iSeed];
        strAns = strAnsList[iSeed];
        iAns = iAnsList[iSeed];
        btnTxt = txtBtnList[iSeed];
    }

    private static final int imgList[] = {
            R.drawable.map_weather_4_1,
            R.drawable.map_weather_4_2,
            R.drawable.map_weather_4_3,
            R.drawable.map_weather_4_4,
            R.drawable.map_weather_4_5
    };

    private static final String DiscriptionList[] = {
            "아래 그림은 날씨 예보입니다. 제주의 날씨는 어떻습니까?\n오른쪽 날씨 단추를 누르세요!",
            "아래 그림은 날씨 예보입니다. 청주의 날씨는 어떻습니까?\n오른쪽 날씨 단추를 누르세요!",
            "아래 그림은 날씨 예보입니다. 부산은 대구 아래에 있다고 합니다.\n부산의 날씨는 어떤지 오른쪽 날씨 단추를 누르세요!",
            "아래 그림은 날씨 예보입니다. 맑은 지역은 모두 몇 군데인가요?\n오른쪽 단추를 누르세요!",
            "아래 그림은 날씨 예보입니다. 비가 오는 곳은 모두 몇 군데인가요?\n오른쪽 단추를 누르세요!"
    };

    private static final int iAnsList[] = {1,2,1,0,0};

    private static final String strAnsList[] = {"","","","4","3"};

    private static final String txtBtnList[][] = {
            {},
            {},
            {},
            {"2", "4"},
            {"3", "5"}
    };
}