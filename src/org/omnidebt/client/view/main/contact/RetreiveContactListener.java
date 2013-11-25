package org.omnidebt.client.view.main.contact;

public interface RetreiveContactListener {
	
	public enum	ERetreiveContactResult {
		Success,
		Failed
	};
	
	public void onRetreiveContactResult(ERetreiveContactResult result);

}
