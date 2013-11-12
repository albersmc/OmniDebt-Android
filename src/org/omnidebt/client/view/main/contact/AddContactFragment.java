package org.omnidebt.client.view.main.contact;

import org.omnidebt.client.R;
import org.omnidebt.client.view.main.MainODActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class AddContactFragment extends Fragment {

	private LinearLayout		llLayout	= null;
	private MainODActivity		moActivity	= null;
	
	public AddContactFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		moActivity	= (MainODActivity)	super.getActivity();
		llLayout	= (LinearLayout)	inflater.inflate(R.layout.add_contact_fragment, container, false);
		
		return llLayout;
	}

}
