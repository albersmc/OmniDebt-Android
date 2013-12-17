package org.omnidebt.client.controller;

import java.util.List;

import org.omnidebt.client.view.main.Debt;
import org.omnidebt.client.view.main.RetreiveDebtListener;
import org.omnidebt.client.view.main.RetreiveDebtListener.ERetreiveDebtResult;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.util.Log;

public class RetreiveDebtCallback implements Callback<RetreiveDebtCallback.RetreiveDebtResponse> {

	RetreiveDebtListener callback = null;

	public RetreiveDebtCallback (RetreiveDebtListener c) {
		callback = c;
	}

	@Override
	public void success(RetreiveDebtResponse r, Response response) {

		if(response.getStatus() == 200)
		{

			Log.i("debts", "Retreived Debts");
			DebtProvider.resetDebt();
			for(Debt d : r.in)
			{
				DebtProvider.addDebt(d);
			}
			for(Debt c : r.out)
			{
				DebtProvider.addDebt(c);
			}
			
			callback.onRetreiveDebtResult(ERetreiveDebtResult.Success);
		}
		else
		{
			Log.e("debts", "Unkown error retreiving debts");
			callback.onRetreiveDebtResult(ERetreiveDebtResult.UnkownError);
		}
	}

	@Override
	public void failure(RetrofitError error) {

		if (error.isNetworkError())
		{
			Log.e("debts", "Network error retreiving debts");
			callback.onRetreiveDebtResult(ERetreiveDebtResult.Failed);
		}
		else
		{
			Log.e("debts", "Unkown error retreiving debts");
			callback.onRetreiveDebtResult(ERetreiveDebtResult.UnkownError);
		}
	}

	public class RetreiveDebtResponse {

		public String status = "";
		public List<Debt> in;
		public List<Debt> out;
	}

}

