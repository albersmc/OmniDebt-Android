package org.omnidebt.client.controller;

import java.util.ArrayList;
import java.util.List;

import org.omnidebt.client.view.main.Debt;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;

public class DebtProvider {

	static private List<Debt> ldData = new ArrayList<Debt>();

	public interface ODRetreiveDebtService {
		@GET("/getDebts/{user}")
		void tryRetreive(@Path("user") String user, Callback<Response> cb);
	}

	static public void retreiveAll() {
		RestAdapter restAdapter = new RestAdapter.Builder()
			.setServer("http://88.185.252.7")
			.build();

		ODRetreiveDebtService service = restAdapter.create(ODRetreiveDebtService.class);

		service.tryRetreive(UserController.getName(), new RetreiveDebtCallback());
	}

	static public List<Debt> getAll() {
		return ldData;
	}

	static public List<Debt> getOpen() {
		List<Debt> open = new ArrayList<Debt>();

		for(Debt d : ldData)
		{
			if(!d.closed)
				open.add(d);
		}

		return open;
	}

	static public List<Debt> getContact(String name) {
		List<Debt> open = new ArrayList<Debt>();

		for(Debt d : ldData)
		{
			if(d.owner.equals(name))
				open.add(d);
		}

		return open;
	}

	static public List<Debt> getContactOpen(String name) {
		List<Debt> open = new ArrayList<Debt>();

		for(Debt d : ldData)
		{
			if(d.owner.equals(name) && !d.closed)
				open.add(d);
		}

		return open;
	}

	static public void addDebt(Debt d) {
		ldData.add(d);
	}

	static public void resetDebt() {
		ldData.clear();
	}

}
