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
	
	static List<Contact>	lcData = new ArrayList<Contact>();
	
	static public List<Contact> getList() {
		return lcData;		
	}

	public interface ODRetreiveContactService {
		@GET("/getContacts/{user}")
		void tryRetreive(@Path("user") String user, Callback<Response> cb);
	}
	
	static public void tryRetreiveContact(RetreiveContactListener callback) {

		RestAdapter restAdapter = new RestAdapter.Builder()
			.setServer("http://10.11.163.24:9000")
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
			.setServer("http://10.11.163.24:9000")
			.build();

		ODAddContactService service = restAdapter.create(ODAddContactService.class);

		service.tryAdd(UserController.getName(), name, new AddContactCallback(callback));
	}
	
	static public void tryRemoveContact(Integer position, RemoveContactListener callback) {
		if(lcData.size() > position) {
			lcData.remove(lcData.get(position));
			Log.d("contact", "remove " + position.toString());
		}

		Log.d("contact", ((Integer) lcData.size()).toString());
		callback.onRemoveContactResult(RemoveContactListener.ERemoveContactResult.Success);
	}

	static public void addContact(Contact c) {
		if(!lcData.contains(c))
			lcData.add(c);
	}

}
