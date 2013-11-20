package org.omnidebt.client.controller;

import java.io.IOException;

import org.omnidebt.client.view.main.contact.AddContactListener;
import org.omnidebt.client.view.main.contact.AddContactListener.EAddContactResult;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.util.Log;

public class AddContactCallback implements Callback<Response> {

	private AddContactListener callback = null;

	public AddContactCallback(AddContactListener c) {
		callback = c;
	}
	
	@Override
	public void success(Response r, Response response) {

		if(response.getStatus() == 200)
		{
			byte array[] = new byte[1];
			try {
				response.getBody().in().read(array, 0, 1);
				String res = new String(array);
				if(res.equals("1"))
				{
					Log.i("login", "Authentication Succeed");
					callback.onAddContactResult(EAddContactResult.Success);
				}
				if(res.equals("2"))
				{
					Log.i("login", "Already a contact");
					callback.onAddContactResult(EAddContactResult.ContactAlready);
				}
				if(res.equals("3"))
				{
					Log.i("login", "Unknown Contact");
					callback.onAddContactResult(EAddContactResult.UnknownContact);
				}
				else
				{
					Log.i("login", "Unkown Error");
					callback.onAddContactResult(EAddContactResult.Failed);
				}
			} catch(IOException e) {
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
}

