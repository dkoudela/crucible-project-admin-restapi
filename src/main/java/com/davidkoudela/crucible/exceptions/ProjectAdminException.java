package com.davidkoudela.crucible.exceptions;

import com.davidkoudela.crucible.rest.response.ResponseProjectFactory;
import com.davidkoudela.crucible.rest.response.ResponseProjectOperation;

/**
 * Description: Exception base class used for construction of the response message.
 * Copyright (C) 2014 David Koudela
 *
 * @author dkoudela
 * @since 29.5.2014
 */
abstract public class ProjectAdminException extends Throwable
{
	private String code;

	/**
	 * ProjectAdminException constructor
	 *
	 * @param code contains result code a.k.a. http result code
	 * @param message contains info about failed operation
	 * @param cause contains root cause of the failure
	 */
	public ProjectAdminException(String code, String message, Throwable cause)
	{
		super(message, cause);
		this.code = code;
	}

	/**
	 * Returns ResponseProjectOperation containing code, message and cause key-value pairs used in REST responses
	 *
	 * @return ResponseProjectOperation containing code, message and cause key-value pairs used in REST responses
	 */
	public ResponseProjectOperation getResponse()
	{
		Throwable cause = getCause();
		if (null == cause)
			return ResponseProjectFactory.constructResponse(code, getMessage(), "");
		else
			return ResponseProjectFactory.constructResponse(code, getMessage(), cause.getMessage());
	}
}
