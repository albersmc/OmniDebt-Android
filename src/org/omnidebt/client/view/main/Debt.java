package org.omnidebt.client.view.main;

public class Debt {

	public String	date	= "";
	public String	owner	= "";
	public Double	value	= 0.;
	public boolean	closed	= false;

	public Debt(String d, String o, Double v, boolean c)
	{
		this.date	= d;
		this.owner	= o;
		this.value	= v;
		this.closed	= c;
	}

}
