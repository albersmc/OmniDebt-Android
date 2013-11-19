package org.omnidebt.client.controller;

import java.io.IOException;

import org.omnidebt.client.view.signup.SignUpListener;
import org.omnidebt.client.view.signup.SignUpListener.SignUpResult;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.util.Log;

public class UserSignupCallback implements Callback<Response> {

	private SignUpListener callback = null;

	public UserSignupCallback(SignUpListener c) {
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
					callback.onConnectResult(SignUpResult.Succeed);
				}
				else if(res.equals("2"))
				{
					Log.i("login", "Already used login");
					callback.onConnectResult(SignUpResult.UsedLogin);
				}
				else
				{
					Log.i("login", "Unkown Error");
					callback.onConnectResult(SignUpResult.UnknownError);
				}
			} catch(IOException e) {
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
}
