package org.omnidebt.client.controller;

import java.util.ArrayList;
import java.util.List;

import org.omnidebt.client.view.main.Contact;
import org.omnidebt.client.view.main.contact.AddContactListener;
import org.omnidebt.client.view.main.contact.RemoveContactListener;
import org.omnidebt.client.view.main.contact.RetreiveContactListener;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

import android.util.Log;

public class ContactProvider {
	
	static List<Contact>	lcData		= new ArrayList<Contact>();
	static List<Contact>	lcToRemove	= new ArrayList<Contact>();
	
	static public List<Contact> getList() {
		return lcData;		
	}

	public interface ODRetreiveContactService {
		@GET("/getContacts/{user}")
		void tryRetreive(@Path("user") String user, Callback<Response> cb);
	}
	
	static public void tryRetreiveContact(RetreiveContactListener callback) {

		RestAdapter restAdapter = new RestAdapter.Builder()
			.setServer("http://88.185.252.7")
			.build();

		ODRetreiveContactService service = restAdapter.create(ODRetreiveContactService.class);

		service.tryRetreive(UserController.getName(), new RetreiveContactCallback(callback));
	}

	public interface ODAddContactService {
		@POST("/newContact/{user}/{contact}")
		void tryAdd(@Path("user") String user, @Path("contact") String contact, Callback<Response> cb);
	}

	static public void tryAddContact(String name, AddContactListener callback) {

		RestAdapter restAdapter = new RestAdapter.Builder()
			.setServer("http://88.185.252.7")
			.build();

		ODAddContactService service = restAdapter.create(ODAddContactService.class);

		service.tryAdd(UserController.getName(), name, new AddContactCallback(callback));
	}
	
	public interface ODRemoveContactService {
		@POST("/deleteContact/{user}/{contact}")
		void tryRemove(@Path("user") String user, @Path("contact") String contact, Callback<Response> cb);
	}

	static public void tryRemoveContact(Integer position, RemoveContactListener callback) {
		RestAdapter restAdapter = new RestAdapter.Builder()
			.setServer("http://88.185.252.7")
			.build();

		ODRemoveContactService service = restAdapter.create(ODRemoveContactService.class);

		if(position < lcData.size())
		{
			lcToRemove.add(lcData.get(position));
			service.tryRemove(UserController.getName(), lcData.get(position).sName, new RemoveContactCallback(callback));
		}
		else
			Log.e("contact", "Remove contact position out of bounds");
	}

	static public void addContact(Contact c) {
		if(!lcData.contains(c))
			lcData.add(c);
	}

	static public void removeContact() {
		if(!lcToRemove.isEmpty())
		{
			lcData.remove(lcToRemove.get(0));
			lcToRemove.remove(lcToRemove.get(0));
		}
	}

}
