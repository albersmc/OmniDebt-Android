package org.omnidebt.client.view.main.contact;

import org.omnidebt.client.R;
import org.omnidebt.client.controller.ContactProvider;
import org.omnidebt.client.view.main.MainODActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddContactFragment extends Fragment {

	private LinearLayout	llLayout	= null;
	private MainODActivity	moActivity	= null;

	private Button			bAdd		= null;
	private Button			bCancel		= null;
	
	public AddContactFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		moActivity	= (MainODActivity)	super.getActivity();
		llLayout	= (LinearLayout)	inflater.inflate(R.layout.add_contact_fragment, container, false);

		bAdd		= (Button)	llLayout.findViewById(R.id.button_add_contact);
		bCancel		= (Button)	llLayout.findViewById(R.id.button_cancel);

		bAdd.setOnClickListener(onClickAddContact);
		bCancel.setOnClickListener(onClickCancel);

		if(getArguments().getString("name") != null)
			((TextView)	llLayout.findViewById(R.id.contact_name)).setText(getArguments().getString("name"));
		if(getArguments().getString("status") != null)
			((TextView)	llLayout.findViewById(R.id.add_contact_status)).setText(getArguments().getString("status"));

		return llLayout;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putString("name", ((TextView)		llLayout.findViewById(R.id.contact_name)).getText().toString());
		outState.putString("status", ((TextView)	llLayout.findViewById(R.id.add_contact_status)).getText().toString());
	}

	// On Create Account clicked		
	private OnClickListener onClickAddContact = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			String name		= ((TextView)	llLayout.findViewById(R.id.contact_name)).getText().toString();
			((TextView)	llLayout.findViewById(R.id.add_contact_status)).setText(R.string.login_trying_login);
			ContactProvider.tryAddContact(name, addContactListener);
		}
	};

	// On Cancel clicked		
	private OnClickListener onClickCancel = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			moActivity.goToPreviousFragment();
		}
	};

	private AddContactListener addContactListener = new AddContactListener() {

		@Override
		public void onAddContactResult(EAddContactResult result) {
			// TODO Auto-generated method stub
			if(result.equals(EAddContactResult.Success))
			{
				moActivity.goToPreviousFragment();
			}
			else if(result.equals(EAddContactResult.ContactAlready))
			{
				((TextView)	llLayout.findViewById(R.id.add_contact_status)).setText(R.string.contact_already);
			}
			else if(result.equals(EAddContactResult.UnknownContact))
			{
				((TextView)	llLayout.findViewById(R.id.add_contact_status)).setText(R.string.unknown_contact);
			}
			else if(result.equals(EAddContactResult.Failed))
			{
				((TextView)	llLayout.findViewById(R.id.add_contact_status)).setText(R.string.login_failed);
			}
			else if(result.equals(EAddContactResult.UnknownError))
			{
				((TextView)	llLayout.findViewById(R.id.add_contact_status)).setText(R.string.login_unkown_error);
			}
		}
		
	};

}
