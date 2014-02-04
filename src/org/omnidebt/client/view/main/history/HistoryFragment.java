package org.omnidebt.client.view.main.history;


import java.util.List;

import org.omnidebt.client.R;
import org.omnidebt.client.controller.DebtProvider;
import org.omnidebt.client.view.main.Debt;
import org.omnidebt.client.view.main.DebtAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

public class HistoryFragment extends Fragment{
	public List<Debt> theList;
	public ListView viewList;
	public DebtAdapter adapter;
	
	private LinearLayout		llLayout	= null;
	private FragmentActivity	faActivity	= null;
		

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        faActivity	= (FragmentActivity)	super.getActivity();
        llLayout	= (LinearLayout)		inflater.inflate(R.layout.historic_fragment, container, false);
        
	    theList= DebtProvider.getAll();
		viewList=(ListView) llLayout.findViewById(R.id.listHistoric);
		
	    adapter=new DebtAdapter(faActivity, R.layout.debt_list_item, theList);
	    viewList.setAdapter(adapter);

        return llLayout;

    }

	@Override
	public void onSaveInstanceState(Bundle outState) {

	}

    private HistoryListener hListener=new HistoryListener(){
    	public void onRetrieveHistoryListener()
    	{
    		
    	}
    };
}
