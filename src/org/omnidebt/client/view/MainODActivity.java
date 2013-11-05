package org.omnidebt.client.view;

import org.omnidebt.client.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainODActivity extends FragmentActivity {
	
	private enum			EFragments {
		Dashboard,
		Contact,
		History,
		About
	};

	private DrawerLayout			dlMainLayout		= null;
	private String[]				sDrawerContent		= null;
	private ListView				lvDrawerContainer	= null;
	private ActionBarDrawerToggle	abActionBar			= null;
	private Integer					iPosition			= null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_od);

		dlMainLayout		= (DrawerLayout)	findViewById(R.id.drawer_layout);
		lvDrawerContainer	= (ListView)		findViewById(R.id.drawer_container);
		sDrawerContent		= (String[])		getResources().getStringArray(R.array.drawer_content);
		iPosition			= EFragments.Dashboard.ordinal();

		abActionBar			= new ODActionBarDrawerToggle(this, dlMainLayout, R.drawable.ic_drawer,
			R.string.drawer_open, R.string.drawer_close);

		// Set the adapter for the list view
		lvDrawerContainer.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, sDrawerContent));

		// Set the list's click listener
		lvDrawerContainer.setOnItemClickListener(new ODDrawerItemClickListener());		

		dlMainLayout.setDrawerListener(abActionBar);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		// Setup the selected fragment
		changeFragment(iPosition);
		getActionBar().setTitle(sDrawerContent[iPosition]);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		abActionBar.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		abActionBar.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// If the event on the drawer toggle button
		if (abActionBar.onOptionsItemSelected(item)) {
			return true;
		}

		// Anything else

		return super.onOptionsItemSelected(item);
	}
	
	private class ODDrawerItemClickListener implements ListView.OnItemClickListener {
		
		@Override
		public void onItemClick(AdapterView parent, View view, int position, long id) {
			changeFragment(position);
		}
	}
	
	private class ODActionBarDrawerToggle extends ActionBarDrawerToggle {
		
		public ODActionBarDrawerToggle(Activity activity,
				DrawerLayout drawerLayout, int drawerImageRes,
				int openDrawerContentDescRes, int closeDrawerContentDescRes) {
			super(activity, drawerLayout, drawerImageRes, openDrawerContentDescRes,
					closeDrawerContentDescRes);
		}

		@Override
		public void onDrawerOpened(View drawerView) {
			getActionBar().setTitle(R.string.app_name);
			invalidateOptionsMenu();
		}

		@Override
		public void onDrawerClosed(View view) {
			getActionBar().setTitle(sDrawerContent[iPosition]);
			invalidateOptionsMenu();
		}
		
	};
	
	private void changeFragment(int position) {
		iPosition = position;
		
		// Create a new fragment and specify args
		Fragment fragment = null;
		Bundle args = new Bundle();

		if( iPosition.equals(EFragments.Dashboard.ordinal()) )
		{
			fragment = new DashboardFragment();
			//args.putInt(ContactFragment.ARG_..., position);
		}
		else if( iPosition.equals(EFragments.Contact.ordinal()) )
		{
			fragment = new ContactFragment();
			//args.putInt(ContactFragment.ARG_..., position);
		}
		else if( iPosition.equals(EFragments.History.ordinal()) )
		{
			fragment = new HistoryFragment();
			//args.putInt(HistoryFragment.ARG_..., position);
		}
		else if( iPosition.equals(EFragments.About.ordinal()) )
		{
			fragment = new AboutFragment();
			//args.putInt(AboutFragment.ARG_..., position);
		}

		fragment.setArguments(args);

		// Insert the fragment by replacing any existing fragment
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
						.replace(R.id.fragment_container, fragment)
						.commit();

		// Highlight the selected item, update the title, and close the drawer
		lvDrawerContainer.setItemChecked(iPosition, true);
		dlMainLayout.closeDrawer(lvDrawerContainer);
	}

}
