package org.omnidebt.client.view.main.dashboard;

import org.omnidebt.client.R;
import org.omnidebt.client.controller.UserController;
import org.omnidebt.client.view.main.Debt;
import org.omnidebt.client.view.main.DebtAdapter;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

public class DashboardFragment extends Fragment {
	public Debt[] theList;
	public ListView viewList;
	public DebtAdapter adapter;
	
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
		
	    adapter=new DebtAdapter(faActivity, R.layout.debt_list_item, theList);
	    viewList.setAdapter(adapter);
	    
	    //viewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    //	public void onItemClick(AdapterView av, View v, int lInt, long leLong)
	   // 	{
	    //		Object theItem=theList[lInt];
	    	//}
	//	});

        return llLayout;

    }

}

