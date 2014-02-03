package org.omnidebt.client.view.main.dashboard;

import org.omnidebt.client.R;
import org.omnidebt.client.controller.DebtController;
import org.omnidebt.client.controller.UserController;
import org.omnidebt.client.view.main.MainODActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DebtPayFragment extends Fragment{
	EditText value;
	Button cancel;
	Button payment;
	TextView currentDebt;
	MainODActivity faActivity;
	LinearLayout llLayout;
	String name;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		
		faActivity	= (MainODActivity)	super.getActivity();
		llLayout	= (LinearLayout)		inflater.inflate(R.layout.debt_pay_fragment, container, false);
		
		value=(EditText) faActivity.findViewById(R.id.debt_pay_value);
		cancel=(Button) faActivity.findViewById(R.id.debt_pay_cancel);
		payment=(Button) faActivity.findViewById(R.id.debt_pay_pay);
		currentDebt=(TextView) faActivity.findViewById(R.id.currentDebt);
		
		
		
		
		name=savedInstanceState.getString("User");
		double debt=Double.parseDouble(savedInstanceState.getString("Debt"));
		
		currentDebt.setText(""+debt);
		
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((MainODActivity) v.getContext()).goToPreviousFragment();
			}
		});
		
		payment.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PaiementListener pl=new PaiementListener(){

					@Override
					public void onConnectResult(PaiementResult code) {
						// TODO Auto-generated method stub
						
					}
				};
				Double paymentValue=Double.parseDouble(value.getText().toString());
				
				DebtController.tryPay(name, paymentValue, pl);
				
			}
		});
		
		return llLayout;
	}
	
	
}
