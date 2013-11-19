package org.omnidebt.client.view.main.dashboard;

import org.omnidebt.client.R;
import org.omnidebt.client.controller.UserController;
import org.omnidebt.client.view.main.Debt;
import org.omnidebt.client.view.main.DebtAdapter;
import org.omnidebt.client.view.main.DebtAdapter.DebtHolder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class DashboardFragment extends Fragment {
	public Debt[] theList;
	public ListView viewList;
	public DebtAdapter adapter;
	
	private LinearLayout		llLayout	= null;
	private FragmentActivity	faActivity	= null;
	
	private PaiementListener pl;
		
	public DashboardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        faActivity	= (FragmentActivity)	super.getActivity();
        llLayout	= (LinearLayout)		inflater.inflate(R.layout.activity_dashboard, container, false);
        
        View theView=faActivity.getLayoutInflater().inflate(R.layout.contact_item_fragment,null);
        llLayout.addView(theView, 1);
        
        
	    theList=UserController.getDebtList();
		viewList=(ListView) llLayout.findViewById(R.id.DebtList);
		
	    adapter=new DebtAdapter(faActivity, R.layout.debt_list_item, theList);
	    viewList.setAdapter(adapter);
	    
	    
	    
	    viewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    	public void onItemClick(AdapterView av, View v, int lInt, long leLong)
	    	{
	    		DebtHolder tag=(DebtHolder) v.getTag();
	    		sendRequest(tag.date.getText().toString(), tag.person.getText().toString(), tag.value.getText().toString());
	    	}
    	});
	    
	    pl=new PaiementListener(){
	    	public void onConnectResult(PaiementResult pr)
	    	{
	    		
	    	}
	    };

        return llLayout;

    }
    
    public void sendRequest(String date, String person, String value)
    {
    	
    }

}

