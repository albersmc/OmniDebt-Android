package org.omnidebt.client.view.main.history;

import org.omnidebt.client.view.login.LoginListener.ConnectResult;

public interface HistoryListener {
	
	public interface LoginListener {

		public enum ConnectResult {
			Succeed,
			Failed
		}
		
		
		public void onRetreiveHistoryResult(ConnectResult code);

	}
}
