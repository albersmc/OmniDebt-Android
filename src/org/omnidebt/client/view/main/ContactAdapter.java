package org.omnidebt.client.view.main;

import java.util.List;

import org.omnidebt.client.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ContactAdapter extends ArrayAdapter<Contact> {
	
	Context			cContext	= null;
	int				iLayout		= 0;
	List<Contact>	lcData		= null;

	public ContactAdapter(Context context, int resource, List<Contact> objects) {
		super(context, resource, objects);
		cContext	= context;
		iLayout		= resource;
		lcData		= objects;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View			rowView = convertView;
		ContactHolder	holder	= null;
		
		if(rowView == null)
		{
			LayoutInflater inflater=((Activity) cContext).getLayoutInflater();
			rowView=inflater.inflate(iLayout, parent, false);
			
			holder = new ContactHolder();
			holder.tvName		=(TextView)	rowView.findViewById(R.id.contact_name);
			holder.tvBalance		=(TextView)	rowView.findViewById(R.id.contact_balance);
			holder.tvPositive	=(TextView)	rowView.findViewById(R.id.contact_positive);
			holder.tvNegative	=(TextView)	rowView.findViewById(R.id.contact_negative);
			
			rowView.setTag(holder);
		}
		else
		{
			holder=(ContactHolder) rowView.getTag();
		}
		
		Contact contact = lcData.get(position);
		holder.tvName.setText(contact.sName);
		holder.tvBalance.setText(contact.dBalance.toString());
		holder.tvPositive.setText(contact.dPositive.toString());
		holder.tvNegative.setText(contact.dNegative.toString());

		return rowView;
		
	}

	static class ContactHolder
	{
		TextView tvName;
		TextView tvBalance;
		TextView tvPositive;
		TextView tvNegative;
	}

}
