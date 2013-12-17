package org.omnidebt.client.view.main.dashboard;

import org.omnidebt.client.R;
import org.omnidebt.client.controller.DebtController;
import org.omnidebt.client.controller.DebtCreateCallback;
import org.omnidebt.client.controller.UserController;
import org.omnidebt.client.view.main.Debt;
import org.omnidebt.client.view.main.DebtAdapter;
import org.omnidebt.client.view.main.DebtCreateListener;
import org.omnidebt.client.view.main.MainODActivity;

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
import android.widget.TextView;

public class AddDebtFragment extends Fragment{
	private LinearLayout		llLayout	= null;
	private MainODActivity	faActivity	= null;
	private EditText et;
	private Button cancel;
	private Button addDebt;
	private DebtCreateListener dcl;
	private DebtCreateCallback dcc;
	private String sUser;
	public TextView	err;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
        
        faActivity	= (MainODActivity)	super.getActivity();
        llLayout	= (LinearLayout)		inflater.inflate(R.layout.add_debt_fragment, container, false);
        
        sUser=getArguments().getString("User");
        
	    et=(EditText) llLayout.findViewById(R.id.AddDebtValue);
	    cancel=(Button) llLayout.findViewById(R.id.AddDebtCancelButton);
	    err=(TextView) llLayout.findViewById(R.id.add_debt_error_view);
	    addDebt=(Button) llLayout.findViewById(R.id.AddDebtSubmitButton);

		if(getArguments().getString("value") != null);
			et.setText(getArguments().getString("value"));
	    
	    dcl=new DebtCreateListener(){
	    	public void onConnectResult(DebtCreateResult code)
	    	{
	    		if(code==DebtCreateResult.success)
	    		{
	    			faActivity.goToPreviousFragment();
	    		}
	    		else if(code==DebtCreateResult.failure)
	    		{
	    			err.setText("Error");
	    		}
	    	}
	    };
	    
	    cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				faActivity.goToPreviousFragment();
				
			}
		});
	    
	    
	    addDebt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String nameLender=null;
				String nameOwner=null;
				float value=Float.parseFloat(et.getText().toString());
				if(value!=0)
				{
					if(value>0)
					{
						nameLender=UserController.getName();
						nameOwner=sUser;
						
						
					}
					else if(value <0)
					{
						nameLender=sUser;
						nameOwner=UserController.getName();
						
					}
					value=Math.abs(value);
					DebtController.tryCreate(nameLender, nameOwner, value, dcl);
					
				}
				
			}
		});
	    
        return llLayout;

    }

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putString("value", et.getText().toString());
	}
}
