package org.omnidebt.client.view.main;

public interface RetreiveDebtListener {

	public enum	ERetreiveDebtResult {
		Success,
		Failed,
		UnkownError
	};
	
	public void onRetreiveDebtResult(ERetreiveDebtResult result);

}

