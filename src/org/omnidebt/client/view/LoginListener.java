package org.omnidebt.client.view;

public interface LoginListener {

	public enum ConnectResult {
		Succeed,
		WrongIDs,
		Failed
	}
	
	
	public void onConnectResult(ConnectResult code);

}
