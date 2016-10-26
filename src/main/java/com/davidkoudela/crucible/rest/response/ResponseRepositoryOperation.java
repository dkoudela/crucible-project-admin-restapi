package com.davidkoudela.crucible.rest.response;

import java.util.Map;

/**
 * Description: Response Repository Operation holder used by Jackson JSON processor
 *              containing provided code, message and cause used in REST responses
 * Copyright (C) 2016 David Koudela
 *
 * @author dkoudela
 * @since 26.10.2016
 */
public interface ResponseRepositoryOperation {
	/**
	 * Getter for Response used by Jackson JSON processor
	 *
	 * @return Map<String, String> containing key-value pairs used in REST responses indicating the operation result
	 */
	public Map<String, String> getResponse();

	/**
	 * Setter for Response used by Jackson JSON processor
	 *
	 * @param response containing key-value pairs used in REST responses indicating the operation result
	 */
	public void setResponse(Map<String, String> response);
}
