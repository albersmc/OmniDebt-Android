package org.omnidebt.client.controller;

import org.omnidebt.client.view.main.DebtCreateListener;
import org.omnidebt.client.view.main.dashboard.PaiementListener;

import retrofit.http.DELETE;
import retrofit.http.POST;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Path;
import retrofit.client.Response;

public class DebtController {
	
	public interface DebtServer
	{
		@POST("/debt/contact/{nameLender}/{debt}")
		void tryCreate(@Path("nameLender") String nameLender, @Path("debt") float debt, Callback<Response> cb);
		

		@POST("/debt/user/contact/{nameContact}/{debt}")
		void tryPay(@Path("nameContact") String nameContact, @Path("debt") Double debt, Callback<Response> cb);
	}
	
	public static void tryCreate(String nameLender, float debt, DebtCreateListener cb)
	{
		RestAdapter ra=new RestAdapter.Builder().setServer("https://88.185.252.7").build();
		DebtServer ds=ra.create(DebtServer.class);
		
		ds.tryCreate(nameLender,debt, new DebtCreateCallback(cb));
	}
	

	public static void tryPay(String nameContact, Double debt, PaiementListener cb)

	{
		RestAdapter ra=new RestAdapter.Builder().setServer("https://88.185.252.7").build();
		DebtServer ds=ra.create(DebtServer.class);

		ds.tryPay(nameContact, debt, new DebtPayCallback(cb));

	}
	
}
