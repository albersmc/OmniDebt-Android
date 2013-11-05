package org.omnidebt.client.view;

import java.util.ArrayList;

import org.omnidebt.client.R;
import org.omnidebt.client.controller.UserController;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class DashboardFragment extends Fragment {
	public ArrayList theList;
	public ListView viewList;
	public ArrayAdapter adapter;
	
	private LinearLayout		llLayout	= null;
	private FragmentActivity	faActivity	= null;
		
	public DashboardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        faActivity	= (FragmentActivity)	super.getActivity();
        llLayout	= (LinearLayout)		inflater.inflate(R.layout.activity_dashboard, container, false);

	    theList=UserController.getDebtList();
		viewList=(ListView) llLayout.findViewById(R.id.DebtList);
		
	    adapter=new ArrayAdapter<Object>(faActivity, android.R.layout.simple_list_item_1, theList);
	    
	    viewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    	public void onItemClick(AdapterView av, View v, int lInt, long leLong)
	    	{
	    		Object theItem=theList.get(lInt);
	    	}
		});

        return llLayout;

    }

}

