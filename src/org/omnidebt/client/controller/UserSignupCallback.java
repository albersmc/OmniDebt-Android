package org.omnidebt.client.controller;

import java.io.IOException;

import org.omnidebt.client.view.signup.SignUpListener;
import org.omnidebt.client.view.signup.SignUpListener.SignUpResult;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.support.v4.app.Fragment;
import android.util.Log;

public class UserSignupCallback implements Callback<UserSignupCallback.SignupResponse> {

	private SignUpListener callback = null;

	public UserSignupCallback(SignUpListener c) {
		callback = c;
	}
	
	@Override
	public void success(SignupResponse r, Response response) {
		if(response.getStatus() == 200)
		{
			if(r.status.equals("OK"))
			{
				Log.i("login", "Authentication Succeed");
				callback.onConnectResult(SignUpResult.Succeed);
			}
			else if(r.status.equals("EXIST"))
			{
				Log.i("login", "Already used login");
				callback.onConnectResult(SignUpResult.UsedLogin);
			}
			else
			{
				Log.i("login", "Unkown Error");
				callback.onConnectResult(SignUpResult.UnknownError);
			}
		}
		else
		{
			Log.e("login", "Unexpected error");
			callback.onConnectResult(SignUpResult.UnknownError);
		}
	}

	@Override
	public void failure(RetrofitError error) {
		if (error.isNetworkError())
		{
			Log.e("login", "Authentication failed : conenction with database problem");
			callback.onConnectResult(SignUpResult.Failed);
		}
		else
		{
			Log.e("login", "Unexpected error");
			callback.onConnectResult(SignUpResult.UnknownError);
		}
	}

	public class SignupResponse {

		public String status = null;
		public String message = null;

	}

}
