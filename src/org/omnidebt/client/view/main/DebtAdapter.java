package org.omnidebt.client.view.main;

import org.omnidebt.client.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class DebtAdapter extends ArrayAdapter<Debt>{
	Context context;
	int layoutResourceId;    
    Debt data[] = null;
	
	public DebtAdapter(Context context, int layoutResourceId, Debt[] data)
	{
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
	}
	
	public View getView(int position, View convertView, ViewGroup parent)
	{
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
			
			row.setTag(holder);
		}
		else
		{
			holder=(DebtHolder) row.getTag();
		}
		
		Debt debt=data[position];
		holder.date.setText(debt.date);
		holder.person.setText(debt.owner);
		holder.value.setText(debt.value.toString());
		if(debt.closed)
			( (ImageButton) row.findViewById(R.id.DebtPayment) ).setVisibility(View.INVISIBLE);
		
		return row;
	}
	
	public static class DebtHolder
	{
		public TextView date;
		public TextView person;
		public TextView value;
	}
}
