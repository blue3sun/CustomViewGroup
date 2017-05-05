package com.monkey.customviewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Description:自定义的ViewGroup布局
 * Author: lanjing
 * Time: 2017/5/5 9:35
 */

public class CustomViewGroup extends ViewGroup {

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
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
