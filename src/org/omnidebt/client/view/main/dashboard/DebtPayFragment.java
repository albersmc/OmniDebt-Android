package org.omnidebt.client.view.main.dashboard;

import org.omnidebt.client.R;
import org.omnidebt.client.controller.DebtController;
import org.omnidebt.client.view.main.MainODActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
	private Fragment frag=this;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		
		faActivity	= (MainODActivity)	super.getActivity();
		llLayout	= (LinearLayout)		inflater.inflate(R.layout.debt_pay_fragment, container, false);
		
		value=(EditText) llLayout.findViewById(R.id.debt_pay_value);
		cancel=(Button) llLayout.findViewById(R.id.debt_pay_cancel2);
		payment=(Button) llLayout.findViewById(R.id.debt_pay_pay);
		currentDebt=(TextView) llLayout.findViewById(R.id.currentDebt);
		
		name=getArguments().getString("User");
		double debt=Double.parseDouble(getArguments().getString("Debt"));
		
		value.setText( ((Double)debt).toString() );

		if(cancel==null)
		{
			Log.d("debt", "NULL! CANCEL!");
		}
		if(payment==null)
		{
			Log.d("debt", "NULL! PAYMENT!");
		}
		payment.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PaiementListener pl=new PaiementListener(){

					@Override
					public void onConnectResult(PaiementResult code) {
						// TODO Auto-generated method stub
						if(code.equals(PaiementResult.Succeed))
							faActivity.goToPreviousFragment();
					}
				};
				Double paymentValue=Double.parseDouble(value.getText().toString());
				
				DebtController.tryPay(frag, faActivity.getPreferences().getString("token", ""), name, paymentValue, pl);
				
			}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((MainODActivity) v.getContext()).goToPreviousFragment();
			}
		});

		return llLayout;
	}
	
	
}
