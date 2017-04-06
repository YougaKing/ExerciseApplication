package youga.touchevent.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by YougaKing on 2017/1/21.
 */

public class TouchView extends View {
    private static final String TAG = TouchView.class.getSimpleName();
    private float mLastX;
    private float mLastY;
    Paint mPaint = new Paint();

    static final int MOVE_X = 1, MOVE_Y = -1, MOVE_NULL = 0;

    @Move
    int mMove = MOVE_NULL;
    private Bitmap mBitmap;

    @IntDef({MOVE_X, MOVE_Y, MOVE_NULL})
    @Retention(RetentionPolicy.SOURCE)
    @interface Move {

    }

    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "dispatchTouchEvent()");
        boolean dispatch = super.dispatchTouchEvent(event);
        Log.d(TAG, "dispatch:" + dispatch);
        return dispatch;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent()");
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = event.getX();
                mLastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                float offsetX = x - mLastX;
                float offsetY = y - mLastY;
                float offset = Math.abs(Math.max(offsetX, offsetY));
                if (offset > 100 && offset == Math.abs(offsetX)) {
                    mMove = MOVE_X;
                } else if (offset > 100 && offset == Math.abs(offsetY)) {
                    mMove = MOVE_Y;
                } else {
                    mMove = MOVE_NULL;
                }
                Log.i(TAG, String.format("mMove:%d", mMove));
                break;

        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawBitmap(mBitmap, 0, 0, null);

        switch (mMove) {
            case MOVE_X:
                canvas.save();
                Matrix matrix = new Matrix();
                matrix.postTranslate(getWidth(), 0);
                canvas.drawBitmap(mBitmap, matrix, null);
                canvas.restore();
                break;
            case MOVE_Y:
                canvas.save();
                matrix = new Matrix();
                matrix.postTranslate(getHeight(), 0);
                canvas.drawBitmap(mBitmap, matrix, null);
                canvas.restore();
                break;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w / 6, h / 8, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mBitmap);
        canvas.drawColor(Color.BLUE);
    }
}
