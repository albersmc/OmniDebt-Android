package org.omnidebt.client.controller;

import org.omnidebt.client.view.main.DebtCreateListener;
import org.omnidebt.client.view.main.DebtCreateListener.DebtCreateResult;
import org.omnidebt.client.view.main.dashboard.PaiementListener;
import org.omnidebt.client.view.main.dashboard.PaiementListener.PaiementResult;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.util.Log;

public class DebtPayCallback implements Callback<Response>{
	private PaiementListener callback = null;

	public DebtPayCallback(PaiementListener c) {
		callback = c;
	}
	
	@Override
	public void success(Response r, Response response) {
		if(response.getStatus() == 200)
		{
			Log.i("login", "Authentication Succeed");
			callback.onConnectResult(PaiementResult.Succeed);
		}
		else
		{
			Log.w("login", "Unexpected error");
			callback.onConnectResult(PaiementResult.Failed);
		}
	}

	@Override
	public void failure(RetrofitError error) {

		if (error.isNetworkError())
		{
			Log.w("login", "Authentication failed : conenction with database problem");
			callback.onConnectResult(PaiementResult.Failed);
		}
		else
		{
			Log.w("login", "Unexpected error");
			callback.onConnectResult(PaiementResult.Failed);
		}
	}
}
