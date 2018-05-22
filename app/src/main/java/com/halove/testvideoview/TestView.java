package com.halove.testvideoview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by zqy on 2018/5/9.
 */

public class TestView extends View {

    Paint paint = new Paint();

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        DisplayMetrics dm = getResources().getDisplayMetrics(); //得到屏幕
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.addRect(0, 0, width, height, Path.Direction.CCW);
        path.addCircle(width / 2, height / 2, width>height?height/2:width/2, Path.Direction.CCW);
        canvas.drawPath(path, paint);
    }
}
