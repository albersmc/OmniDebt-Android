
package org.omnidebt.client.controller;

import java.io.IOException;

import org.omnidebt.client.view.main.contact.RemoveContactListener;
import org.omnidebt.client.view.main.contact.RemoveContactListener.ERemoveContactResult;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.util.Log;

public class RemoveContactCallback implements Callback<RemoveContactCallback.RemoveContactResponse> {

	private RemoveContactListener callback = null;

	public RemoveContactCallback(RemoveContactListener c) {
		callback = c;
	}
	
	@Override
	public void success(RemoveContactResponse r, Response response) {

		if(response.getStatus() == 200)
		{
			if(r.status.equals("OK"))
			{
				Log.i("login", "Authentication Succeed");
				ContactProvider.removeContact();
				callback.onRemoveContactResult(ERemoveContactResult.Success);
			}
			else if(r.status.equals("EXIST"))
			{
				Log.i("login", "A Debt Still Exist");
				callback.onRemoveContactResult(ERemoveContactResult.DebtExist);
			}
			else if(r.status.equals("KO"))
			{
				Log.i("login", "Unknown Contact");
				callback.onRemoveContactResult(ERemoveContactResult.UnknownContact);
			}
			else
			{
				Log.i("login", "Unkown Error");
				callback.onRemoveContactResult(ERemoveContactResult.Failure);
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

	public class RemoveContactResponse {
		public String status;
		public String message;
	}

}

