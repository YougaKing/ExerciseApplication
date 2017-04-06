package youga.graphview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by YougaKing on 2017/3/22.
 */

public abstract class GraphView<T> extends View {

    protected Paint mPaint = new Paint();

    protected T mData = null;

    public GraphView(Context context) {
        this(context, null);
    }

    public GraphView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public void setData(T data) {
        mData = data;
    }

    private void init(Context context) {


    }


    /**
     * UNSPECIFIED	父容器没有对当前View有任何限制，当前View可以任意取尺寸
     * EXACTLY	当前的尺寸就是当前View应该取的尺寸
     * AT_MOST	当前尺寸是当前View能取的最大尺寸
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        switch (MeasureSpec.getMode(widthMeasureSpec)) {
            case MeasureSpec.UNSPECIFIED: {//如果没有指定大小，就设置为默认大小
                width = getResources().getDisplayMetrics().widthPixels;
                break;
            }
            case MeasureSpec.AT_MOST: {//如果测量模式是最大取值为size
                //我们将大小取最大值,你也可以取其他值
                width = MeasureSpec.getSize(widthMeasureSpec);
                break;
            }
            case MeasureSpec.EXACTLY: {//如果是固定的大小，那就不要去改变它
                width = MeasureSpec.getSize(widthMeasureSpec);
                break;
            }
        }
        setMeasuredDimension(width, MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }


    protected int computeMaxStringWidth(int currentMax, Paint p, String... strings) {
        float maxWidthF = 0.0f;
        int len = strings.length;
        for (int i = 0; i < len; i++) {
            float width = p.measureText(strings[i]);
            maxWidthF = Math.max(width, maxWidthF);
        }
        int maxWidth = (int) (maxWidthF + 0.5);
        if (maxWidth < currentMax) {
            maxWidth = currentMax;
        }
        return maxWidth;
    }
}
