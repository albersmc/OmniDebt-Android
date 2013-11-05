package org.omnidebt.client.controller;

import java.util.ArrayList;
import java.util.List;

import org.omnidebt.client.view.Contact;

public class ContactProvider {
	
	static List<Contact>	lcData = new ArrayList<Contact>() {
	};
	
	static public void tryRetreiveContact() {
		Contact contact = new Contact();
		contact.sName		= "Test";
		contact.dBalance	= 42.;
		contact.dPositive	= 66.;
		contact.dNegative	= 24.;
		
		lcData.clear();
		lcData.add(contact);
	}
	
	static public List<Contact> getList() {
		if(lcData.isEmpty())
			tryRetreiveContact();
		
		return lcData;		
	}

}
