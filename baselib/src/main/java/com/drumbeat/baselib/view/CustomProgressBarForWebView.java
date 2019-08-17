package com.drumbeat.baselib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;

import com.drumbeat.baselib.R;

/**
 * WebView进度条
 * Created by wtq on 2016/12/15.
 */
public class CustomProgressBarForWebView extends View {

    private final static int DEFAULT_BAR_HEIGHT = 5;

    private Context context;
    private int progress = 1;
    private Paint paint;

    public CustomProgressBarForWebView(Context context) {
        this(context, null);
    }

    public CustomProgressBarForWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        paint = new Paint(Paint.DITHER_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DEFAULT_BAR_HEIGHT);
        paint.setAntiAlias(true);
        paint.setColor(context.getResources().getColor(R.color.baselib_colorDefault));
    }


    public void setProgressBarColor(@ColorRes int colorRes) {
        paint.setColor(ContextCompat.getColor(context, colorRes));
        invalidate();
    }

    public void setProgressBarHeight(int heightPx) {
        paint.setStrokeWidth(heightPx);
        invalidate();
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth() * progress / 100, DEFAULT_BAR_HEIGHT, paint);
    }
}