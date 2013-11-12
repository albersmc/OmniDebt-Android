package org.omnidebt.client.view.main.history;

import org.omnidebt.client.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HistoryFragment extends Fragment {
	
	private ListView			lvLayout	= null;
	private FragmentActivity	faActivity	= null;
	private String[]			sListContent		= null;
	
	public HistoryFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		faActivity	= (FragmentActivity)	super.getActivity();
		lvLayout	= (ListView)			inflater.inflate(R.layout.history_fragment, container, false);
		sListContent	= (String[]) lvLayout.getResources().getStringArray(R.array.drawer_content);
		
		lvLayout.setAdapter(new ArrayAdapter<String>(faActivity, R.layout.debt_item_fragment, sListContent));
		
		return lvLayout;
	}

}
