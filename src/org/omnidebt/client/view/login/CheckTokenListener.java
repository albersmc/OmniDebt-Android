package org.omnidebt.client.view.login;

public interface CheckTokenListener {

	public enum ConnectResult {
		Succeed,
		WrongToken,
		Failed,
		UnkownError
	}
	
	public void onConnectResult(ConnectResult code);

}

