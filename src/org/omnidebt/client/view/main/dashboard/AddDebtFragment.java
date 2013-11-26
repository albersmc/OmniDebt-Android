package org.omnidebt.client.view.main.dashboard;

import org.omnidebt.client.R;
import org.omnidebt.client.controller.DebtController;
import org.omnidebt.client.controller.DebtCreateCallback;
import org.omnidebt.client.controller.UserController;
import org.omnidebt.client.view.main.DebtAdapter;
import org.omnidebt.client.view.main.DebtCreateListener;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class AddDebtFragment extends Fragment{
	private LinearLayout		llLayout	= null;
	private FragmentActivity	faActivity	= null;
	private EditText et;
	private Button cancel;
	private Button addDebt;
	private DebtCreateListener dcl;
	private DebtCreateCallback dcc;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
        
        faActivity	= (FragmentActivity)	super.getActivity();
        llLayout	= (LinearLayout)		inflater.inflate(R.layout.add_debt_fragment, container, false);
        
	    et=(EditText) llLayout.findViewById(R.id.AddDebtValue);
	    cancel=(Button) llLayout.findViewById(R.id.AddDebtCancelButton);
	    
	    addDebt=(Button) llLayout.findViewById(R.id.AddDebtSubmitButton);
	    
	    dcl=new DebtCreateListener(){
	    	public void onConnectResult(DebtCreateResult code)
	    	{
	    		
	    	}
	    };
	    
	    cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
			}
		});
	    
	    
	    addDebt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String nameLender=null;
				String nameOwner=null;
				float value=Float.parseFloat(et.getText().toString());
				
				if(value>0)
				{
					nameLender=UserController.getName();
					
				}
				else
				{
					nameOwner=UserController.getName();
				}
				
				//DebtController.tryCreate(nameLender, nameOwner, value, dcl);
			}
		});
	    
        return llLayout;

    }
}
