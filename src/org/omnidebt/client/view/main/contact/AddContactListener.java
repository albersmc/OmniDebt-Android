package org.omnidebt.client.view.main.contact;

public interface AddContactListener {

	public enum	EAddContactResult {
		Success,
		Failed
	};
	
	public void onAddContactResult(EAddContactResult result);
	
}
