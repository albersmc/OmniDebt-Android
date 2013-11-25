package org.omnidebt.client.view.main.contact;

public interface RemoveContactListener {

	public enum	ERemoveContactResult {
		Success,
		DebtExist,
		UnknownContact,
		Failure,
		UnknownError
	};
	
	public void onRemoveContactResult(ERemoveContactResult result);

}
