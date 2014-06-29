package com.davidkoudela.crucible.exceptions;

/**
 * Description: Exception class representing Internal error response when unexpected behavior happened in the plugin.
 * Copyright (C) 2014 David Koudela
 *
 * @author dkoudela
 * @since 30.5.2014
 */
public class InternalErrorException extends ProjectAdminException
{
	/**
	 * Internal Error constructor
	 *
	 * @param message contains info about failed operation
	 * @param cause contains root cause of the failure
	 */
	public InternalErrorException(String message, Throwable cause)
	{
		super("500", message, cause);
	}
}
