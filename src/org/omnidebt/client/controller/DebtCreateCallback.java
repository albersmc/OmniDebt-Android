package org.omnidebt.client.controller;

import org.omnidebt.client.view.main.DebtCreateListener;
import org.omnidebt.client.view.main.DebtCreateListener.DebtCreateResult;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.util.Log;

public class DebtCreateCallback implements Callback<Response>{

	private DebtCreateListener callback = null;

	public DebtCreateCallback(DebtCreateListener c) {
		callback = c;
	}
	
	@Override
	public void success(Response r, Response response) {
		if(response.getStatus() == 200)
		{
			Log.i("login", "Authentication Succeed");
			callback.onConnectResult(DebtCreateResult.success);
		}
		else
		{
			Log.w("login", "Unexpected error");
			callback.onConnectResult(DebtCreateResult.failure);
		}
	}

	@Override
	public void failure(RetrofitError error) {

		if (error.isNetworkError())
		{
			Log.w("login", "Authentication failed : conenction with database problem");
			callback.onConnectResult(DebtCreateResult.failure);
		}
		else
		{
			Log.w("login", "Unexpected error");
			callback.onConnectResult(DebtCreateResult.failure);
		}
	}
}
