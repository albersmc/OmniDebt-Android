package org.omnidebt.client.view.main.contact;

public interface RemoveContactListener {

	public enum	ERemoveContactResult {
		Success,
		DebtExist,
		Failure
	};
	
	public void onRemoveContactResult(ERemoveContactResult result);

}
