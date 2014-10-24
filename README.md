LollipopToolbar
========

Add dynamic to ToolBar and Sticky view to ScrollView. 

Since, ScrollView do not have some kind of onScroll listener, tou have to create your own ScrollView: ScrollViewWithListener

In layout file, add new Lollipop Toolbar

	<android.support.v7.widget.Toolbar
        android:id="@+id/my_awesome_toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@drawable/toolbar_transparent_bgd"
        android:minHeight="?attr/actionBarSize" />
        
        
end style it as any other view.

In Activity, you have to assign this Toolbar to ActionBar by

	Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar); 
	setSupportActionBar(toolbar);

Any interaction with Toolbar should NOT go through instance of 

	Toolbar toolbar
	
but with reference to regular action bar. For example:

	 getSupportActionBar().setTitle("");
	 
One really important thing is that if you want to use Toolbar, you have to disable "old" ActionBar. You can do that in styles.xml by inheriting 

	Theme.AppCompat.Light.NoActionBar

or full style.xml

	<resources>
    	<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
        	<item name="colorPrimary">#ff37cd42</item>
        	<item name="colorPrimaryDark">#ff2faf38</item>
        	<item name="colorAccent">#ff32b93b</item>
        	<item name="android:textColorPrimary">#ff1b6521</item>
    	</style>
	</resources>
	
Also, here you can see 3 really important new attributes of Material design

	 <item name="colorPrimary">#ff37cd42</item>
     <item name="colorPrimaryDark">#ff2faf38</item>
     <item name="colorAccent">#ff32b93b</item>
        
These 3 colors define color identity of your app. Try to experiment with it, and you will see how it effects your app.


To add fancy effect to Toolbar or Sticky view, add custom ScrollView as:


	<rs.webnet.material.ScrollViewWithListener
        android:id="@+id/scroll_container"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
    >
        ...
    </rs.webnet.material.ScrollViewWithListener>


Outside of ScrollViewWithListener you can place View/ViewGroup that you want to be a Sticky one:

	<RelativeLayout
        android:id="@+id/sticky_container"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:orientation="vertical">
        <ImageView
            android:id="@+id/image"
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:scaleType="centerCrop"
            android:src="@drawable/blgrd" />
    </RelativeLayout>
        

Notice
	
	android:layout_height="200dp"
	
You will need this value.


In onCreate add

	 stickyView = (RelativeLayout) findViewById(R.id.sticky_container);
	 scrollView.setOnScrollChangedListener(new ScrollViewWithListener.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ScrollView scrollView, int scrollDirection, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                KursullaControl.controlToolbarSlideUp(scrollY, oldScrollY, toolbar);
                KursullaControl.controlStickyView(scrollView, scrollDirection, scrollY, oldScrollY, 200, stickyView);
            }
        });
        
        
After first rerad, you will understand what is this about. You can play around offset value (200) to get best top distance. 

All logic is in KursullaControll class. 