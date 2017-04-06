package youga.graphview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import youga.graphview.data.PieData;

/**
 * Created by YougaKing on 2017/3/22.
 */

public class PieGraph extends GraphView<PieData> {


    private RectF mRectF;
    private float mLoopWidth;
    private float mTextSize;
    private int mTextColor;

    public PieGraph(Context context) {
        this(context, null);
    }

    public PieGraph(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieGraph(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        TypedArray array = context.obtainStyledAttributes(R.styleable.GraphView);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        mLoopWidth = array.getDimension(R.styleable.GraphView_loopWidth,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, metrics));
        mTextSize = array.getDimension(R.styleable.GraphView_textSize,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, metrics));
        mTextColor = array.getColor(R.styleable.GraphView_textColor, Color.BLACK);
        array.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mRectF == null) initRectF();

        if (mData == null) {
            int[] values = {103, 145, 100, 450};
            mData = new PieData("PieData", values, Color.BLUE, Color.CYAN, Color.GREEN, Color.LTGRAY);
        }


        int width = getWidth();
        int height = getHeight();
        canvas.translate(width / 2, height / 2);

        mPaint.setAntiAlias(true);

        for (int i = 0; i < mData.getValues().length; i++) {
            mPaint.setColor(mData.getColors()[i]);
            float angle = mData.getAngel(i);
            canvas.drawArc(mRectF, mPieStart, angle, true, mPaint);
            mPieStart += angle;
        }

        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(0, 0, width / 2 - mLoopWidth, mPaint);

        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        int stringWidth = computeMaxStringWidth(0, mPaint, mData.getName());

        Rect bounds = new Rect();
        mPaint.getTextBounds(mData.getName(), 0, mData.getName().length(), bounds);
        int stringHeight = bounds.height();

        canvas.drawText(mData.getName(), 0, mData.getName().length(), -stringWidth / 2, stringHeight / 2, mPaint);
    }

    float mPieStart;

    /**
     * 初始化绘制弧形所在矩形的四点坐标
     **/
    private void initRectF() {
        mRectF = new RectF();

        mRectF.left = -getWidth() / 2;
        mRectF.top = -getHeight() / 2;
        mRectF.right = getWidth() / 2;
        mRectF.bottom = getHeight() / 2;
    }

}
