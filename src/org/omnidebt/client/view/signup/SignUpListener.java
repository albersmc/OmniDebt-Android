package org.omnidebt.client.view.signup;

public interface SignUpListener {

	public enum SignUpResult {
		Succeed,
		WrongIDs,
		UsedLogin,
		Failed,
		UnknownError
	}

	public void onConnectResult(SignUpResult code);

}
