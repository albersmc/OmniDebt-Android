package org.omnidebt.client.controller;

import java.io.IOException;

import org.omnidebt.client.view.main.contact.AddContactListener;
import org.omnidebt.client.view.main.contact.AddContactListener.EAddContactResult;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.util.Log;

public class AddContactCallback implements Callback<AddContactCallback.AddContactResponse> {

	private AddContactListener callback = null;

	public AddContactCallback(AddContactListener c) {
		callback = c;
	}
	
	@Override
	public void success(AddContactResponse r, Response response) {

		if(response.getStatus() == 200)
		{
			if(r.status.equals("OK"))
			{
				Log.i("login", "Authentication Succeed");
				callback.onAddContactResult(EAddContactResult.Success);
			}
			else if(r.status.equals("EXIST"))
			{
				Log.i("login", "Already a contact");
				callback.onAddContactResult(EAddContactResult.ContactAlready);
			}
			else if(r.status.equals("UNKNOWN"))
			{
				Log.i("login", "Unknown Contact");
				callback.onAddContactResult(EAddContactResult.UnknownContact);
			}
			else
			{
				Log.i("login", "Unkown Error");
				callback.onAddContactResult(EAddContactResult.Failed);
			}
		}
		else
		{
			Log.e("login", "Unexpected error");
			callback.onAddContactResult(EAddContactResult.Failed);
		}
	}

	@Override
	public void failure(RetrofitError error) {

		if (error.isNetworkError())
		{
			Log.e("login", "Authentication failed : conenction with database problem");
			callback.onAddContactResult(EAddContactResult.Failed);
		}
		else
		{
			Log.e("login", "Unexpected error");
			callback.onAddContactResult(EAddContactResult.Failed);
		}
	}

	public class AddContactResponse {
		public String status;
		public String message;
	}
}

