package org.omnidebt.client.controller;

import org.omnidebt.client.view.LoginListener;
import org.omnidebt.client.view.LoginListener.ConnectResult;

import android.util.Log;

public class UserController {

	static public void tryLogin(String strLogin, String strPasswd, LoginListener callback) {
		if(strLogin.equals("nonet")) {
			Log.w("login", "Authentication failed : conenction with database problem");
			callback.onConnectResult(ConnectResult.Failed);
		}
		else if(strLogin.equals("test") && strPasswd.equals("pass")) {
			Log.i("login", "Authentication Succeed");
			callback.onConnectResult(ConnectResult.Succeed);
		}
		else {
    		Log.w("login", "Authentication failed : wrong ids");
			callback.onConnectResult(ConnectResult.WrongIDs);
		}
	}

}
