package org.omnidebt.client.view.main.dashboard;

import java.util.List;

import org.omnidebt.client.R;
import org.omnidebt.client.controller.DebtController;
import org.omnidebt.client.controller.ContactProvider;
import org.omnidebt.client.controller.DebtProvider;
import org.omnidebt.client.controller.UserController;
import org.omnidebt.client.view.main.Contact;
import org.omnidebt.client.view.main.Debt;
import org.omnidebt.client.view.main.DebtAdapter;
import org.omnidebt.client.view.main.DebtAdapter.DebtHolder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import org.omnidebt.client.view.main.MainODActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class DashboardFragment extends Fragment {

	public List<Debt> theList;
	public ListView viewList;
	public DebtAdapter adapter;
	public Button addDebtButton;
	private LinearLayout		llLayout	= null;

	private MainODActivity	faActivity	= null;
	public String				sUser		= "";

	private PaiementListener pl;
		
	public DashboardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        faActivity	= (MainODActivity)	super.getActivity();
        llLayout	= (LinearLayout)		inflater.inflate(R.layout.activity_dashboard, container, false);
        
		if(getArguments() != null && getArguments().getBoolean("User"))
		{
			sUser = faActivity.getAddDebtName();
			theList = DebtProvider.getContactOpen(sUser);
		}
		else
		{
			sUser = UserController.getName();
			theList = DebtProvider.getOpen();
		}

		Contact user	= ContactProvider.getContact(sUser);

        View theView	= faActivity.getLayoutInflater().inflate(R.layout.contact_item_fragment,null);

		( (TextView)	theView.findViewById(R.id.contact_name)		).setText(sUser);

		if(user != null)
		{
			( (TextView)	theView.findViewById(R.id.contact_balance)	).setText(user.balance.toString());
			( (TextView)	theView.findViewById(R.id.contact_positive)	).setText(user.pos.toString());
			( (TextView)	theView.findViewById(R.id.contact_negative)	).setText(user.neg.toString());
		}

        llLayout.addView(theView, 0);

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
	    
	    viewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    	public void onItemClick(AdapterView av, View v, int lInt, long leLong)
	    	{
	    		DebtHolder tag=(DebtHolder) v.getTag();
	    		DebtController.tryPay(tag.person.getText().toString(), tag.value.getText().toString(), pl);
	    	}
    	});
	    

        return llLayout;

    }
    
    public void onAddDebt()
    {
		faActivity.goToAddDebt(sUser);
    }
    
    public void sendRequest(String date, String person, String value)
    {
    	
    }

}

