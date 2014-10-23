package com.example.kursulla.testmaterial;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.ScrollView;


public class MyActivity extends ActionBarActivity {

    private static final String TAG = "MyActivity";
    private ScrollViewWithListener scrollView;
    private int scrollingDelta = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        scrollView = (ScrollViewWithListener) findViewById(R.id.scroll_container);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            setToolbarTopMargin(toolbar);
        }


        scrollView.setOnScrollChangedListener(new ScrollViewWithListener.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
                controlToolbar(t, oldt, toolbar);
            }
        });

    }

    private void controlToolbar(int t, int oldt, Toolbar toolbar) {
        final int headerHeight = findViewById(R.id.image).getTop();
        Log.d(TAG, "oldt=" + oldt + " -> t=" + t + " | toolbar.getTop() = " + toolbar.getTop());
        if (t > oldt) {
            if (scrollingDelta < 0) {
                scrollingDelta = 0;
            }
            if (scrollingDelta > 50 && scrollingDelta != 0) {
                if (toolbar.getTop() > -90) {
                    toolbar.setTop(toolbar.getTop() - 10);
                    toolbar.setAlpha(toolbar.getAlpha() - 0.2f);
                }
            }
        } else {
            if (scrollingDelta > 0) {
                scrollingDelta = 0;
            }
            if (scrollingDelta < -50 && scrollingDelta != 0) {
                if (toolbar.getTop() < 0) {
                    toolbar.setTop(toolbar.getTop() + 10);
                    toolbar.setAlpha(toolbar.getAlpha() + 0.2f);
                }
            }
        }
        Log.d(TAG, "scrollingDelta=" + scrollingDelta);
        scrollingDelta += t - oldt;

        if (t == 0 && toolbar.getTop() != 0) {
            Log.d(TAG, "**** toolbar.getTop() = " + toolbar.getTop() + " and should reset it");
            toolbar.setTop(0);
            toolbar.setAlpha(1);
        }
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
            Intent intent = new Intent(this, RecyclerViewActivity.class);
            startActivity(intent);
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
}
