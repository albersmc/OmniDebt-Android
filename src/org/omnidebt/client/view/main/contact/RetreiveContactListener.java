package org.omnidebt.client.view.main.contact;

public interface RetreiveContactListener {
	
	public enum	ERetreiveContactResult {
		Success,
		Failure
	};
	
	public void onRetreiveContactResult(ERetreiveContactResult result);

}
