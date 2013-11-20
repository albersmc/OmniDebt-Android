package org.omnidebt.client.view.main.contact;

public interface AddContactListener {

	public enum	EAddContactResult {
		Success,
		ContactAlready,
		UnknownContact,
		Failed,
		UnknownError

	};
	
	public void onAddContactResult(EAddContactResult result);
	
}
