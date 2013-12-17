package org.omnidebt.client.controller;

import org.omnidebt.client.view.login.LoginListener;
import org.omnidebt.client.view.login.LoginListener.ConnectResult;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.util.Log;

public class UserConnectCallback implements Callback<UserConnectCallback.ConnectResponse> {

	private LoginListener callback = null;

	public UserConnectCallback(LoginListener c) {
		callback = c;
	}
	
	@Override
	public void success(ConnectResponse r, Response response) {
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
			callback.onConnectResult(ConnectResult.WrongIDs);
		}
		else
		{
			Log.e("login", "Error : Unexpected error");
			callback.onConnectResult(ConnectResult.UnkownError);
		}
	}

	public class ConnectResponse {

		public String status = null;
		public String message = null;

	}

}
