package src.viewes;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import cdmst.smartsilver.R;

/**
 * Created by waps12b on 15. 3. 1..
 */
public abstract class FrameView extends FrameLayout
{

    public final Context context;

    public FrameView(Context context) {
        this(context, null);
    }

    public FrameView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FrameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
        // real work here
    }
    protected abstract void init();

    protected void setLayout(int layoutID){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater inflat;
        inflat = (LayoutInflater) getContext().getSystemService(infService);
        inflat.inflate(layoutID, this, true);
    }

    protected void setBackgroundImage(int drawable_id)
    {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackgroundDrawable( getResources().getDrawable(drawable_id) );
        } else {
            this.setBackground( getResources().getDrawable(drawable_id));
        }
    }






    @Override
    public void onFinishInflate()
    {
        super.onFinishInflate();
    }

    @Override
    public void onMeasure( int widthMeasureSpec, int heightMeasureSpec )
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}

