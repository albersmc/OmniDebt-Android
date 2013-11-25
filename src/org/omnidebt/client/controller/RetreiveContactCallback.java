
package org.omnidebt.client.controller;

import java.io.IOException;
import java.util.Scanner;

import org.omnidebt.client.view.main.Contact;
import org.omnidebt.client.view.main.contact.RetreiveContactListener;
import org.omnidebt.client.view.main.contact.RetreiveContactListener.ERetreiveContactResult;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.util.Log;

public class RetreiveContactCallback implements Callback<Response> {

	private RetreiveContactListener callback = null;

	public  RetreiveContactCallback(RetreiveContactListener c) {
		callback = c;
	}
	
	@Override
	public void success(Response r, Response response) {

		if(response.getStatus() == 200)
		{
			try{
				Scanner test = new Scanner(r.getBody().in()).useDelimiter(" ");
				if(test.next().equals("Aucun") && test.next().equals("contact"))
				{
					Log.i("contact", "No contact");
					callback.onRetreiveContactResult(ERetreiveContactResult.Success);
				}
				else
				{
					Scanner scan = new Scanner(r.getBody().in()).useDelimiter(" ");
					while(scan.hasNext())
					{
						String s = scan.next();
						if(s.length() != 0)
						{
							Contact contact	= new Contact();
							contact.sName		= s;
							contact.dBalance	= 0.;
							contact.dPositive	= 0.;
							contact.dNegative	= 0.;
							ContactProvider.addContact(contact);

							Log.i("contact", "Got a contact Succeed");
							callback.onRetreiveContactResult(ERetreiveContactResult.Success);
						}
						else
						{
							Log.i("contact", "Unkown Error len = 0");
							callback.onRetreiveContactResult(ERetreiveContactResult.Failed);
						}
					}
				}
			} catch(IOException e) {
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
}

