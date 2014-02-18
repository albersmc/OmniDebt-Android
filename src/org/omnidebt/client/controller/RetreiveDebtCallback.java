package org.omnidebt.client.controller;

import java.util.List;

import org.omnidebt.client.view.main.Debt;
import org.omnidebt.client.view.main.RetreiveDebtListener;
import org.omnidebt.client.view.main.RetreiveDebtListener.ERetreiveDebtResult;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.support.v4.app.Fragment;
import android.util.Log;

public class RetreiveDebtCallback implements Callback<RetreiveDebtCallback.RetreiveDebtResponse> {

	RetreiveDebtListener callback = null;
	private Fragment frag;

	public RetreiveDebtCallback (Fragment frag, RetreiveDebtListener c) {
		callback = c;
		this.frag=frag;
	}

	@Override
	public void success(RetreiveDebtResponse r, Response response) {
		if(frag!=null)
		{
			if(response.getStatus() == 200)
			{
	
				Log.i("debts", "Retreived Debts");
				DebtProvider.resetDebt();
				if(r.in != null)
				{
					for(Debt d : r.in)
					{
						d.owed = false;
						DebtProvider.addDebt(d);
					}
				}
				if(r.out != null)
				{
					for(Debt d : r.out)
					{
						d.owed = true;
						DebtProvider.addDebt(d);
					}
				}
				
				callback.onRetreiveDebtResult(ERetreiveDebtResult.Success);
			}
			else
			{
				Log.e("debts", "Unkown error retreiving debts (not 200)");
				callback.onRetreiveDebtResult(ERetreiveDebtResult.UnkownError);
			}
		}
	}

	@Override
	public void failure(RetrofitError error) {
		if(frag!=null)
		{
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
	}

	public class RetreiveDebtResponse {

		public String status = "";
		public List<Debt> in;
		public List<Debt> out;
	}

}

