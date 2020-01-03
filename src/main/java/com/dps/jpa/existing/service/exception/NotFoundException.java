package com.dps.jpa.existing.service.exception;

public class NotFoundException extends RuntimeException
{
	private static final long serialVersionUID = -5226234780636076881L;

	public NotFoundException()
	{
		super();
	}
	
	public NotFoundException(String message)
	{
		super(message);
	}
}
