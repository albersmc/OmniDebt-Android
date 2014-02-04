package org.omnidebt.client.controller;

import org.omnidebt.client.controller.CheckTokenCallback.CheckTokenResponse;
import org.omnidebt.client.controller.UserConnectCallback.ConnectResponse;
import org.omnidebt.client.controller.UserSignupCallback.SignupResponse;
import org.omnidebt.client.view.login.CheckTokenListener;
import org.omnidebt.client.view.login.LoginListener;
import org.omnidebt.client.view.main.Debt;
import org.omnidebt.client.view.signup.SignUpListener;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;

public class UserController {

	private static String	strLogin	= null;
	private static String	strPasswd	= null;

	public interface ODLoginService {
		@POST("/connect")
		void tryConnect(@Header("name") String user, @Header("password") String pass, Callback<ConnectResponse> cb);
	}

	public interface ODCheckTokenService {
		@GET("/token")
		void tryToken(@Header("name") String user, @Header("token") String token, Callback<CheckTokenResponse> cb);
	}

	public interface ODSignupService {
		@POST("/user")
		void trySignup(@Header("name") String user, @Header("password") String pass, @Header("email") String email, Callback<SignupResponse> cb);
	}

	static public void checkToken(String login, String token, CheckTokenListener callback) {
		RestAdapter restAdapter = new RestAdapter.Builder()
			.setServer("http://88.185.252.7:80")
			.build();

		ODCheckTokenService service = restAdapter.create(ODCheckTokenService.class);

		service.tryToken(login, token, new CheckTokenCallback(callback));
	}

	static public void tryLogin(String login, String passwd, LoginListener callback) {

		strLogin	= login;
		strPasswd	= passwd;

		RestAdapter restAdapter = new RestAdapter.Builder()
			.setServer("http://88.185.252.7:80")
			.build();

		ODLoginService service = restAdapter.create(ODLoginService.class);

		service.tryConnect(strLogin, strPasswd, new UserConnectCallback(callback));
	}
	
	static public void trySignUp(String strLogin, String strEmail, String strPasswd, String strConfirmPassword, SignUpListener callback) {

		RestAdapter restAdapter = new RestAdapter.Builder()
			.setServer("http://88.185.252.7:80")
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

