package org.omnidebt.client.view.main;

import java.util.List;

import org.omnidebt.client.R;
import org.omnidebt.client.controller.DebtController;
import org.omnidebt.client.controller.UserController;
import org.omnidebt.client.view.main.dashboard.DashboardFragment;
import org.omnidebt.client.view.main.dashboard.PaiementListener;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class DebtAdapter extends ArrayAdapter<Debt>{
	Context context;
	int layoutResourceId;    
    List<Debt> data = null;
	
	public DebtAdapter(Context context, int layoutResourceId, List<Debt> data)
	{
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		final int pos=position;
		View row=convertView;
		DebtHolder holder=null;
		if(row==null)
		{
			LayoutInflater inflater=((Activity) context).getLayoutInflater();
			row=inflater.inflate(layoutResourceId, parent, false);
			
			holder=new DebtHolder();
			holder.date=(TextView) row.findViewById(R.id.date_debt);
			holder.person=(TextView) row.findViewById(R.id.person_debt);
			holder.value=(TextView) row.findViewById(R.id.value_debt);
			holder.pay=(ImageButton) row.findViewById(R.id.DebtPayment);
			
			row.setTag(holder);
		}
		else
		{
			holder=(DebtHolder) row.getTag();
		}
		
		Debt debt=data.get(position);
		holder.date.setText(debt.date);
		holder.person.setText(debt.name);
		holder.value.setText(String.format("%.2f", debt.value));
		holder.pay.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final View v2=v;
				PaiementListener pl=new PaiementListener(){
					public void onConnectResult(PaiementResult pr){
						if(pr==PaiementResult.Succeed)
						{
							DashboardFragment db=(DashboardFragment) ((MainODActivity) v2.getContext()).currentFragment;
							db.theList.remove(pos);
							DebtAdapter adapter=new DebtAdapter((MainODActivity) v2.getContext(), R.layout.debt_list_item, db.theList);
							db.viewList.setAdapter(adapter);
							adapter.notifyDataSetChanged();
						}
					}
					
				};
				DashboardFragment db=(DashboardFragment) ((MainODActivity) v2.getContext()).currentFragment;
				DebtController.tryPay(db.theList.get(pos).name, pl);
			}
		});

		if(debt.owed)
			holder.value.setTextColor(Color.parseColor("#ff0000"));
		else
			holder.value.setTextColor(Color.parseColor("#00ff00"));

		Log.d("debt", debt.name + " " + ( (Boolean) debt.closed ).toString());

		if(debt.closed)
			( (ImageButton) row.findViewById(R.id.DebtPayment) ).setVisibility(View.INVISIBLE);
		else
			( (ImageButton) row.findViewById(R.id.DebtPayment) ).setVisibility(View.VISIBLE);
		
		return row;
	}
	
	public static class DebtHolder
	{
		public TextView date;
		public TextView person;
		public TextView value;
		public ImageButton pay;
	}
}
