package org.omnidebt.client.controller;

import java.util.ArrayList;
import java.util.List;

import org.omnidebt.client.view.Contact;
import org.omnidebt.client.view.ContactListener;

public class ContactProvider {
	
	static List<Contact>	lcData = new ArrayList<Contact>();
	
	static public List<Contact> getList() {
		return lcData;		
	}
	
	static public void tryRetreiveContact(ContactListener callback) {
		Contact contact = new Contact();
		contact.sName		= "Test";
		contact.dBalance	= 42.;
		contact.dPositive	= 66.;
		contact.dNegative	= 24.;
		
		lcData.clear();
		lcData.add(contact);
	}

	static public void tryAddContact(ContactListener callback) {
		
	}
	
	static public void tryAddRemove(ContactListener callback) {
		
	}

}
