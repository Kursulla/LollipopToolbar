package rs.webnet.material;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;


public class MyActivity extends ActionBarActivity {

    private static final String TAG = "MyActivity";
    private ScrollViewWithListener scrollView;
    private int scrollingDelta = 0;
    private ImageView photo;
    private LinearLayout topMenu;
    private RelativeLayout stickyContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        scrollView = (ScrollViewWithListener) findViewById(R.id.scroll_container);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getSupportActionBar().setTitle("");


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            setToolbarTopMargin(toolbar);
        }

        photo = (ImageView) findViewById(R.id.image);
        topMenu = (LinearLayout) findViewById(R.id.top_menu);
        stickyContainer = (RelativeLayout) findViewById(R.id.sticky_container);

        scrollView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        scrollView.setOnScrollChangedListener(new ScrollViewWithListener.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ScrollView scrollView, int scrollDirection, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int scrollDelta = Math.abs(scrollY - oldScrollY);
//                controlToolbarSlideLeft(scrollY, oldScrollY, toolbar);
                controlView(scrollView.getScrollY(), scrollDirection, scrollDelta, 340, stickyContainer);

            }
        });

    }

    private void controlToolbarSlideUp(int newScrollPosition, int oldScrollPosition, View toolbar) {
        final int SCROLL_THRESHOLD = 50;
        if (newScrollPosition > oldScrollPosition) {
            if (scrollingDelta < 0) {
                scrollingDelta = 0;
            }
            if (scrollingDelta > SCROLL_THRESHOLD && scrollingDelta != 0) {
                if (toolbar.getTop() > -90) {
                    toolbar.setTop(toolbar.getTop() - 10);
                    toolbar.setAlpha(toolbar.getAlpha() - 0.2f);
                }
            }
        } else {
            if (scrollingDelta > 0) {
                scrollingDelta = 0;
            }
            if (scrollingDelta < -SCROLL_THRESHOLD && scrollingDelta != 0) {
                if (toolbar.getTop() < 0) {
                    toolbar.setTop(toolbar.getTop() + 10);
                    toolbar.setAlpha(toolbar.getAlpha() + 0.2f);
                }
            }
        }
        scrollingDelta += newScrollPosition - oldScrollPosition;

        if (newScrollPosition == 0 && toolbar.getTop() != 0) {
            toolbar.setTop(0);
            toolbar.setAlpha(1);
        }
    }
    private void controlToolbarSlideLeft(int newScrollPosition, int oldScrollPosition, View toolbar) {
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

    private void controlPhoto(int newScrollPosition, int oldScrollPosition, final View view) {
        final int SCROLL_THRESHOLD = 50;
        if (newScrollPosition > oldScrollPosition) {
            if (scrollingDelta < 0) {
                scrollingDelta = 0;
            }
            if (scrollingDelta > SCROLL_THRESHOLD && scrollingDelta != 0) {
                if (view.getTop() <= 90) {
                    view.setTop(view.getTop() + (newScrollPosition - oldScrollPosition));
                }
            }
        } else {
            if (scrollingDelta > 0) {
                scrollingDelta = 0;
            }
            if (view.getTop() >= 0) {
                view.setTop(view.getTop() - (oldScrollPosition - newScrollPosition));
            }
        }
        scrollingDelta += newScrollPosition - oldScrollPosition;

        if (newScrollPosition == 0 && view.getTop() != 0) {
            view.animate().y(0).setDuration(100);
        }
    }

    private void controlView(int scrollPosition, int scrollDirection, int scrollDelta, int topOffset, final View view) {
        if (scrollDirection == ScrollViewWithListener.SCROLL_UP) {
            if (view.getTop() > -topOffset) {
                view.setTop(view.getTop() - scrollDelta);
                if (view.getTop() < -topOffset) {
                    view.setTop(-topOffset);
                }
            }
        } else {
            if (scrollPosition < topOffset) {
                view.setTop(view.getTop() + scrollDelta);
            }
        }
        if (view.getTop() > 0) {
            view.setTop(0);
        }

        Log.d(TAG, "scrollPosition = " + scrollView.getScrollY()+" view.getTop() = " + view.getTop() + " view.getHeight()=" + view.getHeight());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setToolbarTopMargin(Toolbar toolbar) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) toolbar.getLayoutParams();
        params.setMargins(0, getStatusBarHeight(), 0, 0);
        toolbar.setLayoutParams(params);
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    interface ViewPositionListener {
        void viewPosition(int x, int y);
    }
}
