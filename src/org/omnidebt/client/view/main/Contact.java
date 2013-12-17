package org.omnidebt.client.view.main;

public class Contact {
	
	public String	name		= "";
	public Double	balance		= 0.;
	public Double	pos	= 0.;
	public Double	neg	= 0.;

	@Override
    public boolean equals(Object object)
    {
		boolean sameSame = false;

        if (object != null && object instanceof Contact)
        {
            sameSame = name.equalsIgnoreCase(((Contact) object).name);
        }

        return sameSame;
    }

}
