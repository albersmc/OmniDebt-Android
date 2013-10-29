package org.omnidebt.client.view;

import org.omnidebt.client.R;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainODActivity extends Activity {

    private DrawerLayout			dlMainLayout		= null;
    private String[]				sDrawerContent		= null;
    private ListView				lvDrawerContainer	= null;
    private ActionBarDrawerToggle	abActionBar			= null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_od);

        dlMainLayout		= (DrawerLayout)	findViewById(R.id.drawer_layout);
        lvDrawerContainer	= (ListView)		findViewById(R.id.drawer_container);
		sDrawerContent		= (String[])		getResources().getStringArray(R.array.drawer_content);

		abActionBar			= new ActionBarDrawerToggle(this, dlMainLayout, R.drawable.ic_drawer,
				R.string.drawer_open, R.string.drawer_close) {
			
			@Override
	        public void onDrawerClosed(View view) {
	            getActionBar().setTitle(R.string.title_activity_main_od);
	            invalidateOptionsMenu();
	        }

			@Override
	        public void onDrawerOpened(View drawerView) {
	            getActionBar().setTitle(R.string.app_name);
	            invalidateOptionsMenu();
	        }
	        
		};

        // Set the adapter for the list view
        lvDrawerContainer.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, sDrawerContent));
        // Set the list's click listener
        // TODO find why DrawerItemClickListener is not defined
        //lvDrawerContainer.setOnItemClickListener(new DrawerItemClickListener());        

		dlMainLayout.setDrawerListener(abActionBar);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

	}
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        abActionBar.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        abActionBar.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (abActionBar.onOptionsItemSelected(item)) {
          return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

}
