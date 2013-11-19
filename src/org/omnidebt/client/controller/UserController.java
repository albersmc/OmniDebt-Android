package org.omnidebt.client.controller;

import org.omnidebt.client.view.login.LoginListener;
import org.omnidebt.client.view.login.LoginListener.ConnectResult;
import org.omnidebt.client.view.main.Debt;
import org.omnidebt.client.view.signup.SignUpListener;
import org.omnidebt.client.view.signup.SignUpListener.SignUpResult;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.http.POST;
import retrofit.http.Path;

import android.util.Log;

public class UserController {

	private static String	strLogin	= null;
	private static String	strPasswd	= null;

	public interface ODUserService {
		@POST("/connect/{user}/{pass}")
		void tryConnect(@Path("user") String user, @Path("pass") String pass, Callback<Response> cb);
	}

	static public void tryLogin(String login, String passwd, LoginListener callback) {

		strLogin	= login;
		strPasswd	= passwd;

		// Used to login even without server
		if(strLogin.equals("test") && strPasswd.equals("pass"))
		{
			callback.onConnectResult(ConnectResult.Succeed);
		}

		RestAdapter restAdapter = new RestAdapter.Builder()
			.setServer("http://10.11.163.24:9000")
			.build();

		ODUserService service = restAdapter.create(ODUserService.class);

		service.tryConnect(strLogin, strPasswd, new UserConnectCallback(callback));
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
	
	static public Debt[] getDebtHistoric()
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

