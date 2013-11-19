package org.omnidebt.client.view.main;

import org.omnidebt.client.view.login.LoginListener.ConnectResult;

public interface DebtCreateListener {
	public enum DebtCreateResult
	{
		success,
		failure
	}
	public void onConnectResult(DebtCreateResult code);
}
