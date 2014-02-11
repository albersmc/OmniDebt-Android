package org.omnidebt.client.controller;

import org.omnidebt.client.view.main.DebtCreateListener;
import org.omnidebt.client.view.main.dashboard.PaiementListener;

import android.content.Context;
import android.support.v4.app.Fragment;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

public class DebtController {
	
	public interface DebtServer
	{
		@POST("/debt/contact/{nameLender}/{debt}")
		void tryCreate(@Header("name") String user, @Header("token") String token, @Path("nameLender") String nameLender, @Path("debt") float debt, Callback<Response> cb);
		

		@POST("/debt/user/contact/{nameContact}/{debt}")
		void tryPay(@Header("name") String user, @Header("token") String token, @Path("nameContact") String nameContact, @Path("debt") Double debt, Callback<Response> cb);
	}
	
	public static void tryCreate(Fragment frag, String token, String nameLender, float debt, DebtCreateListener cb)
	{
		RestAdapter ra=new RestAdapter.Builder().setServer("http://88.185.252.7").build();
		DebtServer ds=ra.create(DebtServer.class);
		
		ds.tryCreate(UserController.getName(), token ,nameLender,debt, new DebtCreateCallback(frag, cb));
	}
	

	public static void tryPay(Fragment frag, String token, String nameContact, Double debt, PaiementListener cb)

	{
		RestAdapter ra=new RestAdapter.Builder().setServer("http://88.185.252.7").build();
		DebtServer ds=ra.create(DebtServer.class);

		ds.tryPay(UserController.getName(), token ,nameContact, debt, new DebtPayCallback(frag, cb));

	}
	
}
