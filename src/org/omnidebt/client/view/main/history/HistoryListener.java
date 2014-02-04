package org.omnidebt.client.view.main.history;


public interface HistoryListener {
	
	public interface LoginListener {

		public enum ConnectResult {
			Succeed,
			Failed
		}
		
		
		public void onRetreiveHistoryResult(ConnectResult code);

	}
}
