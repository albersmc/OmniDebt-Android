package org.omnidebt.client.controller;

import java.util.ArrayList;
import java.util.List;

import org.omnidebt.client.view.main.Contact;
import org.omnidebt.client.view.main.contact.AddContactListener;
import org.omnidebt.client.view.main.contact.RemoveContactListener;
import org.omnidebt.client.view.main.contact.RetreiveContactListener;

import android.util.Log;

public class ContactProvider {
	
	static List<Contact>	lcData = new ArrayList<Contact>();
	
	static public List<Contact> getList() {
		return lcData;		
	}
	
	static public void tryRetreiveContact(RetreiveContactListener callback) {
		Contact contact1	= new Contact();
		contact1.sName		= "Test";
		contact1.dBalance	= 42.;
		contact1.dPositive	= 66.;
		contact1.dNegative	= 24.;
		
		Contact contact2	= new Contact();
		contact2.sName		= "Stupidly long name for the sake of tests";
		contact2.dBalance	= 420.;
		contact2.dPositive	= 660.;
		contact2.dNegative	= 240.;
		
		Contact contact3	= new Contact();
		contact3.sName		= "Other Name";
		contact3.dBalance	= 42000.;
		contact3.dPositive	= 66000.;
		contact3.dNegative	= 24000.;
		
		Contact contact4	= new Contact();
		contact4.sName		= "Yet another Name";
		contact4.dBalance	= 4200.;
		contact4.dPositive	= 6600.;
		contact4.dNegative	= 2400.;
		
		addContact(contact1);
		addContact(contact1);
		addContact(contact2);
		addContact(contact3);
		addContact(contact4);
		
		callback.onRetreiveContactResult(RetreiveContactListener.ERetreiveContactResult.Success);
	}

	static public void tryAddContact(String name, AddContactListener callback) {

		Contact contact		= new Contact();
		contact.sName		= name;
		contact.dBalance	= 0.0;
		contact.dPositive	= 3.14;
		contact.dNegative	= 3.14;
		addContact(contact);

		callback.onAddContactResult(AddContactListener.EAddContactResult.Success);

		Log.d("contact", ((Integer)lcData.size()).toString());
	}
	
	static public void tryRemoveContact(Integer position, RemoveContactListener callback) {
		if(lcData.size() > position) {
			lcData.remove(lcData.get(position));
			Log.d("contact", "remove " + position.toString());
		}

		Log.d("contact", ((Integer) lcData.size()).toString());
		callback.onRemoveContactResult(RemoveContactListener.ERemoveContactResult.Success);
	}

	static private void addContact(Contact c) {
		if(!lcData.contains(c))
			lcData.add(c);
	}

}
