package com.dps.jpa.existing.service.exception;

public class ServerErrorException extends RuntimeException
{
	private static final long serialVersionUID = 102356531852562738L;

	public ServerErrorException()
	{
		super();
	}
	
	public ServerErrorException(String message)
	{
		// DSC - add a custom response header in here for status, desc, etc., can display custom 
		//       fields in top level handler
		super(message);
	}
}
