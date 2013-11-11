package org.omnidebt.client.view.main.contact;

import org.omnidebt.client.R;
import org.omnidebt.client.controller.ContactProvider;
import org.omnidebt.client.view.main.ContactArrayAdapter;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ContactFragment extends Fragment {

	private ListView			lvLayout	= null;
	private FragmentActivity	faActivity	= null;
	
	public ContactFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		faActivity	= (FragmentActivity)	super.getActivity();
		lvLayout	= (ListView)			inflater.inflate(R.layout.contact_fragment, container, false);
		
		ContactProvider.tryRetreiveContact(contactListener);
		lvLayout.setAdapter(new ContactArrayAdapter(faActivity, R.layout.contact_item_fragment, ContactProvider.getList()));
		
		return lvLayout;
	}

	public void onAddContact() {
		Log.d("contact", "add");
	}

	public void onEditContact() {
		Log.d("contact", "edit");
	}
	
	private ContactListener contactListener = new ContactListener() {

		@Override
		public void onRetreiveContactResult(ERetreiveContactResult result) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAddContactResult(EAddContactResult result) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRemoveContactResult(ERemoveContactResult result) {
			// TODO Auto-generated method stub
			
		}
		
	};

}
