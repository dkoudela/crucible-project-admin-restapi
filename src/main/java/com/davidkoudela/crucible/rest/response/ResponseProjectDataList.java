package com.davidkoudela.crucible.rest.response;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: Response Project Data List holder used by Jackson JSON processor
 *              containing provided code, message, cause and Project Properties used in REST responses
 * Copyright (C) 2014 David Koudela
 *
 * @author dkoudela
 * @since 11.6.2014
 */
@JsonAutoDetect
public class ResponseProjectDataList
{
	@JsonProperty
	Map<String, String> response;
	@JsonProperty
	List<ProjectProperties> projectList;

	/**
	 * Default constructor just for internal initialization
	 */
	public ResponseProjectDataList()
	{
		this.response = new LinkedHashMap<String, String>();
		this.projectList = new ArrayList<ProjectProperties>();
	}

	/**
	 * Constructor assigning the provided response and projectList
	 *
	 * @param response contains key-value pairs used in REST responses indicating the operation result
	 * @param projectList contains list of Project Properties used in REST responses requested by the client
	 */
	public ResponseProjectDataList(Map<String, String> response, List<ProjectProperties> projectList)
	{
		this.response = response;
		this.projectList = projectList;
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
	 * Getter for Project List used by Jackson JSON processor
	 *
	 * @return List<ProjectProperties> containing list of Project Properties used in REST responses requested by the client
	 */
	public List<ProjectProperties> getProjectList()
	{
		return projectList;
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
	 * Setter for Project List used by Jackson JSON processor
	 *
	 * @param projectPropertiesList containing list of Project Properties used in REST responses requested by the client
	 */
	public void setProjectList(List<ProjectProperties> projectPropertiesList)
	{
		this.projectList = projectPropertiesList;
	}
}
