package org.omnidebt.client.view.login;

public interface LoginListener {

	public enum ConnectResult {
		Succeed,
		WrongIDs,
		Failed,
		UnkownError
	}
	
	public void onConnectResult(ConnectResult code);
	public void onConnectSuccess(String token);

}
