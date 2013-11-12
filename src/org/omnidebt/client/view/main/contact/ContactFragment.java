package org.omnidebt.client.view.main.contact;

import org.omnidebt.client.R;
import org.omnidebt.client.controller.ContactProvider;
import org.omnidebt.client.view.main.ContactAdapter;
import org.omnidebt.client.view.main.ContactComparator;
import org.omnidebt.client.view.main.MainODActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ContactFragment extends Fragment {

	private ListView			lvLayout	= null;
	private MainODActivity		moActivity	= null;
	private ContactAdapter		caAdapter	= null;
	
	public ContactFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		moActivity	= (MainODActivity)	super.getActivity();
		lvLayout	= (ListView)		inflater.inflate(R.layout.contact_fragment, container, false);
		caAdapter	= new ContactAdapter(moActivity, R.layout.contact_item_fragment, ContactProvider.getList());

		ContactProvider.tryRetreiveContact(retreiveContactListener);
		lvLayout.setAdapter(caAdapter);

		return lvLayout;
	}

	@Override
	public void onResume() {
		super.onResume();
		caAdapter.notifyDataSetChanged();
		caAdapter.sort(new ContactComparator());
	}

	public void onAddContact() {
		Log.d("contact", "add");
		moActivity.goToAddContact();
	}

	public void onEditContact() {
		Log.d("contact", "edit");
	}

	private RetreiveContactListener retreiveContactListener = new RetreiveContactListener() {

		@Override
		public void onRetreiveContactResult(ERetreiveContactResult result) {
			caAdapter.sort(new ContactComparator());
		}
		
	};

	private RemoveContactListener removeContactListener = new RemoveContactListener() {

		@Override
		public void onRemoveContactResult(ERemoveContactResult result) {
			// TODO Auto-generated method stub
		}
		
	};

}
