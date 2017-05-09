package com.monkey.customviewgroup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
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
        setWillNotDraw(false);//如果不设置的话没有执行onDraw()
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //遍历所有的子View 执行子View的measure()方法
        int childCount = getChildCount();
        for(int i=0;i<childCount;i++){
            View child = getChildAt(i);
            LayoutParams childLp = child.getLayoutParams();
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec,0,childLp.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec,0,childLp.height);
            child.measure(childWidthMeasureSpec,childHeightMeasureSpec);
        }

        //通过子View测量出来的宽度和高度，计算出能包裹所有子View的ViewGroup最小宽度和高度
        int totalWidth = 0;
        int totalHeight = 0;
        for(int i=0;i<childCount;i++){
            View child = getChildAt(i);
            totalWidth = totalWidth>child.getMeasuredWidth()+i*interval?totalHeight:child.getMeasuredWidth()+i*interval;
            totalHeight = totalHeight+child.getMeasuredHeight();
        }
        int viewGroupWidth = 0;
        int viewGroupHeight = 0;

        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //计算ViewGroup的宽度
        switch (widthMode){
            case MeasureSpec.EXACTLY:
                viewGroupWidth = measureWidth;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                viewGroupWidth = measureWidth>totalWidth?measureWidth:totalWidth;
                break;
        }
        //计算ViewGroup的高度
        switch (heightMode){
            case MeasureSpec.EXACTLY:
                viewGroupHeight = measureHeight;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                viewGroupHeight = measureHeight>totalHeight?measureHeight:totalHeight;
                break;
        }
        //设置ViewGroup的宽度和高度

        setMeasuredDimension(viewGroupWidth,viewGroupHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int buttom) {
        //计算每一个子View的位置
        int childCount = getChildCount();
        for(int i=0;i<childCount;i++){
            View child = getChildAt(i);
            int measureWidth = child.getMeasuredWidth();
            int measureHeight = child.getMeasuredHeight();
            Log.e("layout", "measureWidth: "+measureWidth +"   measureHeight:"+measureHeight);
            child.layout(left,top,left+measureWidth,top+measureHeight);
            left = left + interval;
            top = top + measureHeight;
        }
        Log.e("layout", "getWidth(): "+getWidth() +"   getHeight():"+getHeight());
        Log.e("layout", "getLeft(): "+getLeft() +"   getTop():"+getTop()+"    getRight(): "+getRight() +"   getBottom():"+getBottom());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xff00ff);
        paint.setAlpha(123);
        Log.e("onDraw", "getLeft(): "+getLeft() +"   getTop()"+getTop()+"    getRight(): "+getRight() +"   getBottom()"+getBottom());
        canvas.drawRect(getLeft(),getTop(),getRight(),getBottom(),paint);
    }
}
