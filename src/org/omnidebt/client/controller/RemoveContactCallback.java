
package org.omnidebt.client.controller;

import java.io.IOException;

import org.omnidebt.client.view.main.contact.RemoveContactListener;
import org.omnidebt.client.view.main.contact.RemoveContactListener.ERemoveContactResult;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.util.Log;

public class RemoveContactCallback implements Callback<Response> {

	private RemoveContactListener callback = null;

	public RemoveContactCallback(RemoveContactListener c) {
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
					ContactProvider.removeContact();
					callback.onRemoveContactResult(ERemoveContactResult.Success);
				}
				else if(res.equals("2"))
				{
					Log.i("login", "A Debt Still Exist");
					callback.onRemoveContactResult(ERemoveContactResult.DebtExist);
				}
				else if(res.equals("3"))
				{
					Log.i("login", "Unknown Contact");
					callback.onRemoveContactResult(ERemoveContactResult.UnknownContact);
				}
				else
				{
					Log.i("login", "Unkown Error");
					callback.onRemoveContactResult(ERemoveContactResult.Failure);
				}
			} catch(IOException e) {
				Log.i("login", "Unkown Error");
				callback.onRemoveContactResult(ERemoveContactResult.UnknownError);
			}
		}
		else
		{
			Log.e("login", "Unexpected error");
			callback.onRemoveContactResult(ERemoveContactResult.UnknownError);
		}
	}

	@Override
	public void failure(RetrofitError error) {

		if (error.isNetworkError())
		{
			Log.e("login", "Authentication failed : conenction with database problem");
			callback.onRemoveContactResult(ERemoveContactResult.Failure);
		}
		else
		{
			Log.e("login", "Unexpected error");
			callback.onRemoveContactResult(ERemoveContactResult.UnknownError);
		}
	}
}

