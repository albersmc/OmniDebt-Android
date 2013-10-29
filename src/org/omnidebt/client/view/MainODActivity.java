package org.omnidebt.client.view;

import org.omnidebt.client.R;
import org.omnidebt.client.R.layout;
import org.omnidebt.client.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainODActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_od);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_od, menu);
		return true;
	}

}
