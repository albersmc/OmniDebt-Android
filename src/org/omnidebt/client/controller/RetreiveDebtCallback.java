package org.omnidebt.client.controller;

import org.omnidebt.client.view.main.RetreiveDebtListener;
import org.omnidebt.client.view.main.RetreiveDebtListener.ERetreiveDebtResult;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.util.Log;

public class RetreiveDebtCallback implements Callback<Response> {

	RetreiveDebtListener callback = null;

	public RetreiveDebtCallback (RetreiveDebtListener c) {
		callback = c;
	}

	@Override
	public void success(Response r, Response response) {

		if(response.getStatus() == 200)
		{

			Log.i("debts", "Retreived Debts");
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

	public class RetreiveDebtResult {

		public String status = "";
		//public List<> contacts = "";
	}

}

