package org.omnidebt.client.view;

import java.util.ArrayList;

import org.omnidebt.client.R;
import org.omnidebt.client.R.anim;
import org.omnidebt.client.R.id;
import org.omnidebt.client.R.layout;
import org.omnidebt.client.controller.UserController;
import org.omnidebt.client.view.LoginListener.ConnectResult;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class DashboardActivity extends Activity {
	public ArrayList theList;
	public ListView viewList;
	public ArrayAdapter adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_dashboard);
	    theList=UserController.getDebtList();
		viewList=(ListView) findViewById(R.id.DebtList);
		
	    adapter=new ArrayAdapter<Object>(this, android.R.layout.simple_list_item_1, theList);
	    
	    viewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    	public void onItemClick(AdapterView av, View v, int lInt, long leLong)
	    	{
	    		Object theItem=theList.get(lInt);
	    	}
		});
	    // TODO Auto-generated method stub
	}

	

}
