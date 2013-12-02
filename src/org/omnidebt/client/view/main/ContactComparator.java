package org.omnidebt.client.view.main;

import java.util.Comparator;

public class ContactComparator implements Comparator<Contact> {

	public int compare(Contact l, Contact r) {
		return l.name.compareTo(r.name);
	}

}
