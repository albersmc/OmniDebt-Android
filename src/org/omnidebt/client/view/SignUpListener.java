package org.omnidebt.client.view;


public interface SignUpListener {
	public enum ConnectResult {
		Succeed,
		WrongIDs,
		UsedLogin,
		Failed
	}
	public void onConnectResult(ConnectResult code);
}
