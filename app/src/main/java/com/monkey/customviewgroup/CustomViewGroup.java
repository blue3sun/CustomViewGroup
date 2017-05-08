package com.monkey.customviewgroup;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Description:自定义的ViewGroup布局
 * Author: lanjing
 * Time: 2017/5/5 9:35
 */

public class CustomViewGroup extends ViewGroup {

    private int interval = 60;

    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int childCount = getChildCount();
        for(int i=0;i<childCount;i++){
            View child = getChildAt(i);
            child.measure(widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int buttom) {
        int childCount = getChildCount();
        for(int i=0;i<childCount;i++){
            View child = getChildAt(i);
            int measureWidth = child.getMeasuredWidth();
            int measureHeight = child.getMeasuredHeight();
            Log.e("layout", "measureWidth: "+measureWidth +"   measureHeight"+measureHeight);
            if(i>0){
                left = left + interval;
                top = top + measureHeight;
            }
            child.layout(left,top,left+measureWidth,top+measureHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
