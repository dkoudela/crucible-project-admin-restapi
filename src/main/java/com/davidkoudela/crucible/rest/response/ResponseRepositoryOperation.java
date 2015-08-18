package com.davidkoudela.crucible.rest.response;

import com.cenqua.crucible.model.Project;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description: Response Repository Operation holder used by Jackson JSON processor
 *              containing provided code, message and cause used in REST responses
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 13.8.2015
 */
@JsonAutoDetect
public class ResponseRepositoryOperation {
	@JsonProperty
	Map<String, String> response;

	/**
	 * Default constructor just for internal initialization
	 */
	public ResponseRepositoryOperation()
	{
		this.response = new LinkedHashMap<String, String>();
	}

	public ResponseRepositoryOperation(Map<String, String> response)
	{
		this.response = response;
	}

	/**
	 * Getter for Response used by Jackson JSON processor
	 *
	 * @return Map<String, String> containing key-value pairs used in REST responses indicating the operation result
	 */
	public Map<String, String> getResponse()
	{
		return response;
	}

	/**
	 * Setter for Response used by Jackson JSON processor
	 *
	 * @param response containing key-value pairs used in REST responses indicating the operation result
	 */
	public void setResponse(Map<String, String> response)
	{
		this.response = response;
	}
}
