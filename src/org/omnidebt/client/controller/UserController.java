package org.omnidebt.client.controller;

import org.omnidebt.client.view.login.LoginListener;
import org.omnidebt.client.view.login.LoginListener.ConnectResult;
import org.omnidebt.client.view.main.Debt;
import org.omnidebt.client.view.signup.SignUpListener;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.http.POST;
import retrofit.http.Path;

public class UserController {

	private static String	strLogin	= null;
	private static String	strPasswd	= null;

	public interface ODLoginService {
		@POST("/connect/{user}/{pass}")
		void tryConnect(@Path("user") String user, @Path("pass") String pass, Callback<Response> cb);
	}

	public interface ODSignupService {
		@POST("/signup/{user}/{pass}/{email}")
		void trySignup(@Path("user") String user, @Path("pass") String pass, @Path("email") String email, Callback<Response> cb);
	}

	static public void tryLogin(String login, String passwd, LoginListener callback) {

		strLogin	= login;
		strPasswd	= passwd;

		// Used to login even without server
		if(strLogin.equals("test") && strPasswd.equals("pass"))
		{
			ContactProvider.retreiveUser();
			callback.onConnectResult(ConnectResult.Succeed);
		}

		RestAdapter restAdapter = new RestAdapter.Builder()
			.setServer("http://88.185.252.7")
			.build();

		ODLoginService service = restAdapter.create(ODLoginService.class);

		service.tryConnect(strLogin, strPasswd, new UserConnectCallback(callback));
	}
	
	static public void trySignUp(String strLogin, String strEmail, String strPasswd, String strConfirmPassword, SignUpListener callback) {

		RestAdapter restAdapter = new RestAdapter.Builder()
			.setServer("http://88.185.252.7")
			.build();

		ODSignupService service = restAdapter.create(ODSignupService.class);

		service.trySignup(strLogin, strPasswd, strEmail, new UserSignupCallback(callback));
	}
	
	
	static public Debt[] getDebtList()
	{
		Debt[] list=new Debt[]{
				
				new Debt("potato","potatu", 0., false),
				new Debt("potito","potitu", 0., false),
				new Debt("poteto","potetu", 0., false),
				new Debt("pototo","pototu", 0., false),
		};
		
		return list;
	}
	
	static public Debt[] getDebtHistoric()
	{
		Debt[] list=new Debt[]{
				
				new Debt("potato","potatu", 0., false),
				new Debt("potito","potitu", 0., false),
				new Debt("poteto","potetu", 0., false),
				new Debt("pototo","pototu", 0., true),
		};
		
		return list;
	}

	static public String getName() {
		return strLogin;
	}

}

