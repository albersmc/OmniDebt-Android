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
		Contact contact1 = new Contact();
		contact1.sName		= "Test";
		contact1.dBalance	= 42.;
		contact1.dPositive	= 66.;
		contact1.dNegative	= 24.;
		
		Contact contact2 = new Contact();
		contact2.sName		= "Stupidly long name for the sake of tests";
		contact2.dBalance	= 420.;
		contact2.dPositive	= 660.;
		contact2.dNegative	= 240.;
		
		Contact contact3 = new Contact();
		contact3.sName		= "Other Name";
		contact3.dBalance	= 42000.;
		contact3.dPositive	= 66000.;
		contact3.dNegative	= 24000.;
		
		Contact contact4 = new Contact();
		contact4.sName		= "Yet another Name";
		contact4.dBalance	= 4200.;
		contact4.dPositive	= 6600.;
		contact4.dNegative	= 2400.;
		
		lcData.clear();
		lcData.add(contact1);
		lcData.add(contact2);
		lcData.add(contact3);
		lcData.add(contact4);
		lcData.add(contact1);
		lcData.add(contact2);
		lcData.add(contact3);
		lcData.add(contact4);
		
	}

	static public void tryAddContact(ContactListener callback) {
		
	}
	
	static public void tryAddRemove(ContactListener callback) {
		
	}

}
