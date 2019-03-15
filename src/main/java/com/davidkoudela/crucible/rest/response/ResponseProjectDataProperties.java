package com.davidkoudela.crucible.rest.response;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description: Response Project Data Properties holder used by Jackson JSON processor
 *              containing provided code, message, cause and Project Properties used in REST responses
 * Copyright (C) 2019 David Koudela
 *
 * @author dkoudela
 * @since 14.3.2019
 */
@JsonAutoDetect
public class ResponseProjectDataProperties
{
	@JsonProperty
	Map<String, String> response;
	@JsonProperty
	ProjectDetailedProperties projectProperties;

	/**
	 * Default constructor just for internal initialization
	 */
	public ResponseProjectDataProperties()
	{
		this.response = new LinkedHashMap<String, String>();
		this.projectProperties = new ProjectDetailedProperties();
	}

	/**
	 * Constructor assigning the provided response and projectList
	 *
	 * @param response contains key-value pairs used in REST responses indicating the operation result
	 * @param projectProperties contains Project Properties used in REST responses requested by the client
	 */
	public ResponseProjectDataProperties(Map<String, String> response, ProjectDetailedProperties projectProperties)
	{
		this.response = response;
		this.projectProperties = projectProperties;
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
	 * Getter for Project Properties used by Jackson JSON processor
	 *
	 * @return List<ProjectDetailedProperties> containing list of Project Properties used in REST responses requested by the client
	 */
	public ProjectDetailedProperties projectProperties()
	{
		return projectProperties;
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

	/**
	 * Setter for Project Properties used by Jackson JSON processor
	 *
	 * @param projectProperties containing list of Project Properties used in REST responses requested by the client
	 */
	public void setProjectProperties(ProjectDetailedProperties projectProperties)
	{
		this.projectProperties = projectProperties;
	}
}
