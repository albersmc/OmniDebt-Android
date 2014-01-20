package org.omnidebt.client.controller;

import org.omnidebt.client.view.main.DebtCreateListener;
import org.omnidebt.client.view.main.dashboard.PaiementListener;

import retrofit.http.POST;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Path;
import retrofit.client.Response;

public class DebtController {
	
	public interface DebtServer
	{
		@POST("/newDebt/{nameLender}/{nameOwner}/{debt}")
		void tryCreate(@Path("nameLender") String nameLender, @Path("nameOwner") String nameOwner, @Path("debt") float debt, Callback<Response> cb);
		
		@POST("/closeDebt/{nameUser}/{nameContact}")
		void tryPay(@Path("nameUser") String nameUser, @Path("nameContact") String nameContact, Callback<Response> cb);
		
		
	}
	
	public static void tryCreate(String nameLender, String nameOwner, float debt, DebtCreateListener cb)
	{
		RestAdapter ra=new RestAdapter.Builder().setServer("https://88.185.252.7").build();
		DebtServer ds=ra.create(DebtServer.class);
		
		ds.tryCreate(nameLender, nameOwner, debt, new DebtCreateCallback(cb));
	}
	
	public static void tryPay(String nameUser, String nameContact, PaiementListener cb)
	{
		RestAdapter ra=new RestAdapter.Builder().setServer("https://88.185.252.7").build();
		DebtServer ds=ra.create(DebtServer.class);
		
		ds.tryPay(nameUser, nameContact, new DebtPayCallback(cb));
	}
	
}