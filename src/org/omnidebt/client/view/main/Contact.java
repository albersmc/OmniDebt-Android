package org.omnidebt.client.view.main;

public class Contact {
	
	public String	sName		= "";
	public Double	dBalance	= 0.;
	public Double	dPositive	= 0.;
	public Double	dNegative	= 0.;

	@Override
    public boolean equals(Object object)
    {
		boolean sameSame = false;

        if (object != null && object instanceof Contact)
        {
            sameSame = sName.equals(((Contact) object).sName);
        }

        return sameSame;
    }

}
