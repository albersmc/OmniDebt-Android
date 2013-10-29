package org.omnidebt.client.view;

import org.omnidebt.client.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainODActivity extends Activity {

    private DrawerLayout	dlMainLayout		= null;
    private String[]		sDrawerContent		= null;
    private ListView		lvDrawerContainer	= null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_od);

        dlMainLayout		= (DrawerLayout)	findViewById(R.id.drawer_layout);
        lvDrawerContainer	= (ListView)		findViewById(R.id.drawer_container);
		sDrawerContent		= (String[])		getResources().getStringArray(R.array.drawer_content);

        // Set the adapter for the list view
        lvDrawerContainer.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, sDrawerContent));
        
        // Set the list's click listener
        //lvDrawerContainer.setOnItemClickListener(new DrawerItemClickListener());


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_od, menu);
		return true;
	}

}
