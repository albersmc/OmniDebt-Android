package org.omnidebt.client.view.main.dashboard;

import org.omnidebt.client.view.signup.SignUpListener.SignUpResult;

public interface PaiementListener {
	public enum PaiementResult {
		Succeed,
		Failed
	}
	public void onConnectResult(PaiementResult code);
}
