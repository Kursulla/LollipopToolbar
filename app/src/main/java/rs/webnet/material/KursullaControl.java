package rs.webnet.material;

import android.view.View;
import android.widget.ScrollView;

/**
 * Created by Kursulla on 24/10/14.
 *
 */
public class KursullaControl {
    static int scrollingDelta = 0;

    public static void controlStickyView(ScrollView scrollView, int scrollDirection, int scrollY, int oldScrollY, int topOffset, final View view) {
        int scrollViewPosition = scrollView.getScrollY();
        int scrollDelta = Math.abs(scrollY - oldScrollY);
        if (scrollDirection == ScrollViewWithListener.SCROLL_UP) {
            if (view.getTop() > -topOffset) {
                view.setTop(view.getTop() - scrollDelta);
                if (view.getTop() < -topOffset) {
                    view.setTop(-topOffset);
                }
            }
        } else {
            if (scrollViewPosition < topOffset) {
                view.setTop(view.getTop() + scrollDelta);
            }
        }
        if (view.getTop() > 0) {
            view.setTop(0);
        }
    }

    public static void controlToolbarSlideUp(int newScrollPosition, int oldScrollPosition, View toolbar) {
        final int SCROLL_THRESHOLD = 50;
        final int MOVING_SPEED = 10;
        final float ALPHA_SPEED = 0.2f;

        if (newScrollPosition > oldScrollPosition) {
            if (scrollingDelta < 0) {
                scrollingDelta = 0;
            }
            if (scrollingDelta > SCROLL_THRESHOLD && scrollingDelta != 0) {
                if (toolbar.getTop() > -90) {
                    toolbar.setTop(toolbar.getTop() - MOVING_SPEED);
                    toolbar.setAlpha(toolbar.getAlpha() - ALPHA_SPEED);
                }
            }
        } else {
            if (scrollingDelta > 0) {
                scrollingDelta = 0;
            }
            if (scrollingDelta < -SCROLL_THRESHOLD && scrollingDelta != 0) {
                if (toolbar.getTop() < 0) {
                    toolbar.setTop(toolbar.getTop() + MOVING_SPEED);
                    toolbar.setAlpha(toolbar.getAlpha() + ALPHA_SPEED);
                }
            }
        }
        scrollingDelta += newScrollPosition - oldScrollPosition;

        if (newScrollPosition == 0 && toolbar.getTop() != 0) {
            toolbar.setTop(0);
            toolbar.setAlpha(1);
        }
    }

    public static void controlToolbarSlideLeft(int newScrollPosition, int oldScrollPosition, View toolbar) {
        final int MOVING_SPEED = 2;
        final float ALPHA_SPEED = 0.1f;
        final int REMOVE_THRESHOLD = 200;
        final int SHOW_THRESHOLD = 50;

        if (newScrollPosition > oldScrollPosition) {
            if (scrollingDelta < 0) {
                scrollingDelta = 0;
            }
            if (scrollingDelta > REMOVE_THRESHOLD && scrollingDelta != 0) {
                if (toolbar.getLeft() > -90) {
                    toolbar.setLeft(toolbar.getLeft() - MOVING_SPEED);
                    toolbar.setAlpha(toolbar.getAlpha() - ALPHA_SPEED);
                }
            }
        } else {
            if (scrollingDelta > 0) {
                scrollingDelta = 0;
            }
            if (scrollingDelta < - SHOW_THRESHOLD && scrollingDelta != 0) {
                if (toolbar.getLeft() < 0) {
                    toolbar.setLeft(toolbar.getLeft() + MOVING_SPEED);
                    toolbar.setAlpha(toolbar.getAlpha() + ALPHA_SPEED);
                }
            }
        }
        scrollingDelta += newScrollPosition - oldScrollPosition;

        if (newScrollPosition == 0 && toolbar.getLeft() != 0) {
            toolbar.setLeft(0);
            toolbar.setAlpha(1);
        }
    }


}
