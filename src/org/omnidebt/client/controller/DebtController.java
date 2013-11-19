package org.omnidebt.client.controller;

import org.omnidebt.client.view.main.DebtCreateListener;

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
		
		@POST("/newDebt/{nameLender}/{nameOwner}/{debt}")
		void tryPay(@Path("nameLender") String nameLender, @Path("nameOwner") String nameOwner, @Path("debt") float debt, Callback<Response> cb);
		
		
	}
	
	public static void tryCreate(String nameLender, String nameOwner, float debt, DebtCreateListener cb)
	{
		RestAdapter ra=new RestAdapter.Builder().setServer("http://10.11.163.24:9000").build();
		DebtServer ds=ra.create(DebtServer.class);
		
		ds.tryCreate(nameLender, nameOwner, debt, new DebtCreateCallback(cb));
	}
	
	public static void tryPay(String nameLender, String nameOwner, float debt, DebtCreateListener cb)
	{
		RestAdapter ra=new RestAdapter.Builder().setServer("http://10.11.163.24:9000").build();
		DebtServer ds=ra.create(DebtServer.class);
		
		ds.tryPay(nameLender, nameOwner, debt, new DebtCreateCallback(cb));
	}
	
}