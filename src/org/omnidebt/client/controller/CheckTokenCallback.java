package org.omnidebt.client.controller;

import org.omnidebt.client.view.login.CheckTokenListener;
import org.omnidebt.client.view.login.CheckTokenListener.ConnectResult;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.support.v4.app.Fragment;
import android.util.Log;

public class CheckTokenCallback implements Callback<CheckTokenCallback.CheckTokenResponse> {

	private CheckTokenListener callback = null;

	public CheckTokenCallback(CheckTokenListener c) {
		callback = c;
	}
	
	@Override
	public void success(CheckTokenResponse r, Response response) {
		if(response.getStatus() == 200)
		{
			Log.i("login", "Authentication Succeed");
			callback.onConnectResult(ConnectResult.Succeed);
		}
		else
		{
			Log.e("login", "Success : Unexpected error");
			callback.onConnectResult(ConnectResult.UnkownError);
		}
	}

	@Override
	public void failure(RetrofitError error) {

		if (error.isNetworkError())
		{
			Log.e("login", "Authentication failed : conenction with database problem");
			callback.onConnectResult(ConnectResult.Failed);
		}
		else if(error.getResponse().getStatus() == 401)
		{
			Log.w("login", "Authentication failed : wrong ids");
			callback.onConnectResult(ConnectResult.WrongToken);
		}
		else
		{
			Log.e("login", "Error : Unexpected error");
			callback.onConnectResult(ConnectResult.UnkownError);
		}
	}

	public class CheckTokenResponse {

		public String status = null;
		public String token = null;
		public String message = null;

	}

}
