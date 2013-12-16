package org.omnidebt.client.view.main.contact;

import org.omnidebt.client.R;
import org.omnidebt.client.controller.ContactProvider;
import org.omnidebt.client.view.main.ContactAdapter;
import org.omnidebt.client.view.main.ContactComparator;
import org.omnidebt.client.view.main.MainODActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ContactFragment extends Fragment {

	private ListView			lvLayout			= null;
	private MainODActivity		moActivity			= null;
	private ContactAdapter		caAdapter			= null;
	private Integer				iNumber				= null;
	private SparseBooleanArray	baSelected			= null;
	private Boolean				bIsSelectContact	= false;
	
	public ContactFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		moActivity	= (MainODActivity)	super.getActivity();
		lvLayout	= (ListView)		inflater.inflate(R.layout.contact_fragment, container, false);
		baSelected	= new SparseBooleanArray();
		
		ContactProvider.resetContact();
		ContactProvider.tryRetreiveContact(retreiveContactListener);
		
		caAdapter	= new ContactAdapter(moActivity, R.layout.contact_item_fragment, ContactProvider.getList(), baSelected);

		bIsSelectContact = getArguments().getBoolean("isSelectContact");
		if(bIsSelectContact == null)
			bIsSelectContact = false;

		ContactProvider.tryRetreiveContact(retreiveContactListener);

		lvLayout.setAdapter(caAdapter);

		lvLayout.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		lvLayout.setMultiChoiceModeListener(multiChoiceListener);
		lvLayout.setOnItemClickListener(onItemClickListener);

		return lvLayout;
	}

	@Override
	public void onResume() {
		super.onResume();
		caAdapter.sort(new ContactComparator());
		caAdapter.notifyDataSetChanged();
	}

	public void onAddContact() {
		moActivity.goToAddContact();
	}

	private OnItemClickListener onItemClickListener	= new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

			if(bIsSelectContact)
				moActivity.goToAddDebt(( (TextView) arg1.findViewById(R.id.contact_name) ).getText().toString());
			else
				moActivity.goToContactInfos(( (TextView) arg1.findViewById(R.id.contact_name) ).getText().toString());
		}
		
	};

	private RetreiveContactListener retreiveContactListener	= new RetreiveContactListener() {

		@Override
		public void onRetreiveContactResult(ERetreiveContactResult result) {
			caAdapter.sort(new ContactComparator());
			caAdapter.notifyDataSetChanged();
		}
		
	};

	private RemoveContactListener removeContactListener		= new RemoveContactListener() {

		@Override
		public void onRemoveContactResult(ERemoveContactResult result) {
			// TODO Auto-generated method stub
			caAdapter.sort(new ContactComparator());
			caAdapter.notifyDataSetChanged();
		}
	};
		
	private MultiChoiceModeListener multiChoiceListener		= new MultiChoiceModeListener() {

		@Override
		public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
			// Here you can do something when items are selected/de-selected,
			// such as update the title in the CAB

			baSelected.put(position, checked);
			caAdapter.notifyDataSetChanged();

			if(checked)
				iNumber++;
			else
				iNumber--;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			// Respond to clicks on the actions in the CAB
			switch (item.getItemId()) {
				case R.id.remove:
					deleteSelectedItems();
					mode.finish(); // Action picked, so close the CAB
					return true;
				default:
					return false;
			}
		}

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			iNumber = 0;
			baSelected.clear();
			caAdapter.notifyDataSetChanged();

			MenuInflater inflater = mode.getMenuInflater();
			inflater.inflate(R.menu.contact_edit, menu);

			return true;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
			iNumber = null;
			baSelected.clear();
			caAdapter.notifyDataSetChanged();
		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			// Here you can perform updates to the CAB due to
			// an invalidate() request
			return false;
		}
	};

	private void deleteSelectedItems() {
		for(Integer i = baSelected.size(); i >= 0; i--) {
			if(baSelected.get(i))
				ContactProvider.tryRemoveContact(i, removeContactListener);
		}
	}

}
