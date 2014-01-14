package org.omnidebt.client.view.main;

public class Debt {

	public String	date	= "";
	public String	name	= "";
	public Double	value	= 0.;
	public boolean	closed	= false;
	public boolean	owed	= false;

	public Debt(String d, String o, Double v, boolean c)
	{
		this.date	= d;
		this.name	= o;
		this.value	= v;
		this.closed	= c;
	}

}
