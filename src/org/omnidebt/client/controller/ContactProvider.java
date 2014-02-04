package org.omnidebt.client.controller;

import java.util.ArrayList;
import java.util.List;

import org.omnidebt.client.controller.AddContactCallback.AddContactResponse;
import org.omnidebt.client.controller.RemoveContactCallback.RemoveContactResponse;
import org.omnidebt.client.controller.RetreiveContactCallback.RetreiveContactResponse;
import org.omnidebt.client.view.main.Contact;
import org.omnidebt.client.view.main.contact.AddContactListener;
import org.omnidebt.client.view.main.contact.RemoveContactListener;
import org.omnidebt.client.view.main.contact.RetreiveContactListener;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

import android.util.Log;

public class ContactProvider {
	
	static Contact			cUser		= null;
	static List<Contact>	lcData		= new ArrayList<Contact>();
	static List<Contact>	lcToRemove	= new ArrayList<Contact>();
	
	static public List<Contact> getList() {
		return lcData;		
	}

	public interface ODRetreiveContactService {
		@GET("/user/contact")
		void tryRetreive(@Header("name") String user, @Header("token") String token, Callback<RetreiveContactResponse> cb);
	}
	
	static public void tryRetreiveContact(String token, RetreiveContactListener callback) {

		RestAdapter restAdapter = new RestAdapter.Builder()
			.setServer("http://88.185.252.7:80")
			.build();

		ODRetreiveContactService service = restAdapter.create(ODRetreiveContactService.class);

		service.tryRetreive(UserController.getName(), token, new RetreiveContactCallback(callback));
	}

	public interface ODAddContactService {
		@POST("/user/contact/{contact}")
		void tryAdd(@Header("name") String user, @Header("token") String token, @Path("contact") String contact, Callback<AddContactResponse> cb);
	}

	static public void tryAddContact(String token, String name, AddContactListener callback) {

		RestAdapter restAdapter = new RestAdapter.Builder()
			.setServer("http://88.185.252.7")
			.build();

		ODAddContactService service = restAdapter.create(ODAddContactService.class);

		service.tryAdd(UserController.getName(), token, name, new AddContactCallback(callback));
	}
	
	public interface ODRemoveContactService {
		@DELETE("/user/contact/{contact}")
		void tryRemove(@Header("name") String user, @Header("token") String token, @Path("contact") String contact, Callback<RemoveContactResponse> cb);
	}

	static public void tryRemoveContact(String token, Integer position, RemoveContactListener callback) {
		RestAdapter restAdapter = new RestAdapter.Builder()
			.setServer("http://88.185.252.7")
			.build();

		ODRemoveContactService service = restAdapter.create(ODRemoveContactService.class);

		if(position < lcData.size())
		{
			lcToRemove.add(lcData.get(position));
			service.tryRemove(UserController.getName(), token, lcData.get(position).name, new RemoveContactCallback(callback));
		}
		else
			Log.e("contact", "Remove contact position out of bounds");
	}

	static public void addSelf(Contact c) {
		cUser = c;
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

	static public void resetContact() {
		lcData.clear();
	}

	static public Contact getContact(String strName) {
		Contact c = new Contact();
		c.name = strName;

		if(cUser != null && cUser.equals(c))
			return cUser;

		int i = lcData.indexOf(c);
		if(i > -1)
			return lcData.get(i);

		return null;
	}

}
