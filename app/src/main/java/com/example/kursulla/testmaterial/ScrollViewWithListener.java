package com.example.kursulla.testmaterial;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Kursulla on 23/10/14.
 */
public class ScrollViewWithListener extends ScrollView {
    private OnScrollChangedListener mOnScrollChangedListener;
    public interface OnScrollChangedListener {
        void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt);
    }
    public ScrollViewWithListener(Context context) {
        super(context);
    }

    public ScrollViewWithListener(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewWithListener(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedListener != null) {
            mOnScrollChangedListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }

    public void setOnScrollChangedListener(OnScrollChangedListener listener) {
        mOnScrollChangedListener = listener;
    }


}
