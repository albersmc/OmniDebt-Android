package org.omnidebt.client.controller;

import org.omnidebt.client.view.login.LoginListener;
import org.omnidebt.client.view.login.LoginListener.ConnectResult;
import org.omnidebt.client.view.main.Debt;
import org.omnidebt.client.view.signup.SignUpListener;
import org.omnidebt.client.view.signup.SignUpListener.SignUpResult;

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
	
	static public void trySignUp(String strLogin, String strEmail, String strPasswd, String strConfirmPassword, SignUpListener callback) {
		if(strLogin.equals("nonet")) {
			Log.w("login", "Authentication failed : conenction with database problem");
			callback.onConnectResult(SignUpResult.Failed);
		}
		else if(strLogin.equals("test")) {
			Log.i("login", "Authentication Succeed");
			callback.onConnectResult(SignUpResult.Succeed);
		}
		else {
			Log.w("login", "Authentication failed : wrong ids");
			callback.onConnectResult(SignUpResult.UsedLogin);
		}
	}
	
	
	static public Debt[] getDebtList()
	{
		Debt[] list=new Debt[]{
				
				new Debt("potato","potatu","potata"),
				new Debt("potito","potitu","potita"),
				new Debt("poteto","potetu","poteta"),
				new Debt("pototo","pototu","potota"),
		};
		
		return list;
	}
	

}
