package org.omnidebt.client.controller;

import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.util.List;

import org.omnidebt.client.controller.UserConnectCallback.ConnectResponse;
import org.omnidebt.client.controller.UserSignupCallback.SignupResponse;
import org.omnidebt.client.view.login.LoginListener;
import org.omnidebt.client.view.login.LoginListener.ConnectResult;
import org.omnidebt.client.view.main.Debt;
import org.omnidebt.client.view.signup.SignUpListener;

import com.squareup.okhttp.OkAuthenticator;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.POST;
import retrofit.http.Path;

public class UserController {

	private static String	strLogin	= null;
	private static String	strPasswd	= null;

	public interface ODLoginService {
		@POST("/connect/{user}/{pass}")
		void tryConnect(@Path("user") String user, @Path("pass") String pass, Callback<ConnectResponse> cb);
	}

	public interface ODSignupService {
		@POST("/signup/{user}/{pass}/{email}")
		void trySignup(@Path("user") String user, @Path("pass") String pass, @Path("email") String email, Callback<SignupResponse> cb);
	}

	static public void tryLogin(String login, String passwd, LoginListener callback) {

		strLogin	= login;
		strPasswd	= passwd;

		// Used to login even without server
		if(strLogin.equals("test") && strPasswd.equals("pass"))
		{
			callback.onConnectResult(ConnectResult.Succeed);
		}
		
		OkHttpClient client=new OkHttpClient();
		
		OkAuthenticator authenticator= new OkAuthenticator(){

			@Override
			public Credential authenticate(Proxy arg0, URL arg1,
					List<Challenge> arg2) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Credential authenticateProxy(Proxy arg0, URL arg1,
					List<Challenge> arg2) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
		client.setAuthenticator(authenticator);
		
		RestAdapter restAdapter = new RestAdapter.Builder()
			.setServer("https://88.185.252.7")
			.setClient(new OkClient(client))
			.build();

		ODLoginService service = restAdapter.create(ODLoginService.class);

		service.tryConnect(strLogin, strPasswd, new UserConnectCallback(callback));
	}
	
	static public void trySignUp(String strLogin, String strEmail, String strPasswd, String strConfirmPassword, SignUpListener callback) {

		RestAdapter restAdapter = new RestAdapter.Builder()
			.setServer("https://88.185.252.7")
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

