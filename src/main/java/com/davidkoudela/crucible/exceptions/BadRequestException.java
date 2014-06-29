package com.davidkoudela.crucible.exceptions;

/**
 * Description: Exception class representing Bad Request response when wrong REST parameters were identified.
 * Copyright (C) 2014 David Koudela
 *
 * @author dkoudela
 * @since 30.5.2014
 */
public class BadRequestException extends ProjectAdminException
{
	/**
	 * Bad Request constructor
	 *
	 * @param message contains info about failed operation
	 * @param cause contains root cause of the failure
	 */
	public BadRequestException(String message, Throwable cause)
	{
		super("400", message, cause);
	}
}
