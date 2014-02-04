package org.omnidebt.client.view.main.dashboard;


public interface PaiementListener {
	public enum PaiementResult {
		Succeed,
		Failed
	}
	public void onConnectResult(PaiementResult code);
}
