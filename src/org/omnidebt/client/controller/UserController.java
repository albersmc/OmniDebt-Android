package org.omnidebt.client.controller;

import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import org.omnidebt.client.R;
import org.omnidebt.client.controller.UserConnectCallback.ConnectResponse;
import org.omnidebt.client.controller.UserSignupCallback.SignupResponse;
import org.omnidebt.client.view.login.LoginListener;
import org.omnidebt.client.view.main.Debt;
import org.omnidebt.client.view.signup.SignUpListener;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

import android.content.Context;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;


public class UserController {

	private static String	strLogin	= null;
	private static String	strPasswd	= null;

	public interface ODLoginService {
		@POST("/connect")
		void tryConnect(@Header("name") String user, @Header("password") String pass, Callback<ConnectResponse> cb);
	}

	public interface ODSignupService {
		@POST("/user")
		void trySignup(@Header("name") String user, @Header("password") String pass, @Header("email") String email, Callback<SignupResponse> cb);
	}

	static public void tryLogin(String login, String passwd, LoginListener callback, Context context) {

		strLogin	= login;
		strPasswd	= passwd;

/*

		try
		{
			KeyStore keyStore = KeyStore.getInstance("BKS");
			InputStream in = context.getResources().openRawResource(R.raw.mystore);
			try
			{
				keyStore.load(in, null);
			} finally
			{
				in.close();
			}

			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(keyStore);

			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, tmf.getTrustManagers(), null);

			SSLSocketFactory sslFactory = sslContext.getSocketFactory();

			OkHttpClient client=new OkHttpClient()
				.setSslSocketFactory(sslFactory);

			RestAdapter restAdapter = new RestAdapter.Builder()
				.setServer("https://88.185.252.7:80")
				.setClient(new OkClient(client))
				.build();

			ODLoginService service = restAdapter.create(ODLoginService.class);

			service.tryConnect(strLogin, strPasswd, new UserConnectCallback(callback));
		}
		catch(Exception e)
		{
			Log.e("https", "fail !");
		}
*/
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

