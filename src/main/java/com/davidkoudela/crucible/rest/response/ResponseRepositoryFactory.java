package com.davidkoudela.crucible.rest.response;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description: Factory method for Response Repository construction containing result code, result message and result cause
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 13.8.2015
 */
public class ResponseRepositoryFactory {
	public static ResponseRepositoryOperation constructResponse(String code, String message, String cause)
	{
		Map<String, String> response = new LinkedHashMap<String, String>();
		response.put("code", code);
		response.put("message", message);
		response.put("cause", cause);
		ResponseRepositoryOperation responseRepositoryOperation = new ResponseRepositoryOperation(response);
		return responseRepositoryOperation;
	}
}
