package rs.webnet.material;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Kursulla on 23/10/14.
 *
 */
public class ScrollViewWithListener extends ScrollView {
    public static final int SCROLL_UP = 1;
    public static final int SCROLL_DOWN = 2;
    private OnScrollChangedListener mOnScrollChangedListener;
    public interface OnScrollChangedListener {
        void onScrollChanged(ScrollView scrollView,int scrollDirection, int scrollX, int scrollY, int oldScrollX, int oldScrollY);
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
    protected void onScrollChanged(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        super.onScrollChanged(scrollX, scrollY, oldScrollX, oldScrollY);
        if (mOnScrollChangedListener != null) {
            int scrollDirection;
            if(scrollY > oldScrollY){
                scrollDirection = SCROLL_UP;
            }else{
                scrollDirection = SCROLL_DOWN;
            }
            mOnScrollChangedListener.onScrollChanged(this, scrollDirection, scrollX, scrollY, oldScrollX, oldScrollY);
        }
    }

    public void setOnScrollChangedListener(OnScrollChangedListener listener) {
        mOnScrollChangedListener = listener;
    }


}
