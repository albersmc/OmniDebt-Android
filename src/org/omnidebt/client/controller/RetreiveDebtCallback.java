package org.omnidebt.client.controller;

import org.omnidebt.client.view.main.Debt;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RetreiveDebtCallback implements Callback<Response> {

	public RetreiveDebtCallback () {
	}

	@Override
	public void success(Response r, Response response) {

		if(response.getStatus() == 200)
		{
		}
		else
		{
		}

		createFakeDebts();
	}

	@Override
	public void failure(RetrofitError error) {

		if (error.isNetworkError())
		{
		}
		else
		{
		}

		createFakeDebts();
	}

	public void createFakeDebts() {
		DebtProvider.addDebt(new Debt("30/11/2013", "Fluttershy", 3.14, false));
		DebtProvider.addDebt(new Debt("06/12/2013", "RainbowDash", 3.14, false));
		DebtProvider.addDebt(new Debt("13/12/2013", "TwilightSparkle", 3.14, true));
		DebtProvider.addDebt(new Debt("20/12/2013", "Rarity", 3.14, true));
		DebtProvider.addDebt(new Debt("27/12/2013", "AppleJack", 3.14, true));
		DebtProvider.addDebt(new Debt("23/11/2013", "PinkiePie", 3.14, false));
	}

}

