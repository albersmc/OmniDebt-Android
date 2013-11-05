package org.omnidebt.client.view;

public interface ContactListener {
	
	public enum	ERetreiveContactResult {
		Success,
		Failure
	};
	
	public void onRetreiveContactResult(ERetreiveContactResult result);
	
	public enum	EAddContactResult {
		Success,
		Failure
	};
	
	public void onAddContactResult(EAddContactResult result);
	
	public enum	ERemoveContactResult {
		Success,
		DebtExist,
		Failure
	};
	
	public void onRemoveContactResult(ERemoveContactResult result);

}
