
package org.omnidebt.client.controller;

import java.util.List;

import org.omnidebt.client.view.main.Contact;
import org.omnidebt.client.view.main.contact.RetreiveContactListener;
import org.omnidebt.client.view.main.contact.RetreiveContactListener.ERetreiveContactResult;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.util.Log;

public class RetreiveContactCallback implements Callback<RetreiveContactCallback.RetreiveContactResponse> {

	private RetreiveContactListener callback = null;

	public  RetreiveContactCallback(RetreiveContactListener c) {
		callback = c;
	}
	
	@Override
	public void success(RetreiveContactResponse r, Response response) {

		if(response.getStatus() == 200)
		{
			if(r.status.equals("OK"))
			{
				ContactProvider.resetContact();
				ContactProvider.addSelf(r.self);
				if(r.contacts != null)
				{
					for(Contact c : r.contacts)
					{
						ContactProvider.addContact(c);

						Log.i("contact", "Got a contact Succeed");
					}
				}
				callback.onRetreiveContactResult(ERetreiveContactResult.Success);
			}
			else if(r.status.equals("KO"))
			{
				Log.e("contact", "No Contact");
				callback.onRetreiveContactResult(ERetreiveContactResult.Success);
			}
			else
			{
				Log.e("contact", "Unexpected error");
				callback.onRetreiveContactResult(ERetreiveContactResult.Failed);
			}
		}
		else
		{
			Log.e("contact", "Unexpected error");
			callback.onRetreiveContactResult(ERetreiveContactResult.Failed);
		}
	}

	@Override
	public void failure(RetrofitError error) {

		if (error.isNetworkError())
		{
			Log.e("contact", "Authentication failed : conenction with database problem");
			callback.onRetreiveContactResult(ERetreiveContactResult.Failed);
		}
		else
		{
			Log.e("contact", "Unexpected error 2");
			callback.onRetreiveContactResult(ERetreiveContactResult.Failed);
		}
	}

	public class RetreiveContactResponse {
		public String status;
		public Contact self;
		public List<Contact> contacts;
	}
}

