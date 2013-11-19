package org.omnidebt.client.view.main.contact;

public interface AddContactListener {

	public enum	EAddContactResult {
		Success,
		Failure
	};
	
	public void onAddContactResult(EAddContactResult result);
	
}
