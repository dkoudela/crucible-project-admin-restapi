package com.davidkoudela.crucible.rest.response;

import com.cenqua.crucible.model.Project;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description: Response Project Operation holder used by Jackson JSON processor
 *              containing provided code, message and cause used in REST responses
 * Copyright (C) 2014 David Koudela
 *
 * @author dkoudela
 * @since 17.6.2014
 */
@JsonIgnoreProperties(ignoreUnknown=true, value = { "relatedProject" })
@JsonAutoDetect
public class ResponseProjectOperation
{
	Project relatedProject;

	@JsonProperty
	Map<String, String> response;

	/**
	 * Default constructor just for internal initialization
	 */
	public ResponseProjectOperation()
	{
		this.response = new LinkedHashMap<String, String>();
		this.relatedProject = new Project();
	}

	/**
	 * Constructor assigning the provided response
	 *
	 * @param response contains key-value pairs used in REST responses indicating the operation result
	 * @param relatedProject contains project related data
	 */
	public ResponseProjectOperation(Map<String, String> response, Project relatedProject)
	{
		this.response = response;
		this.relatedProject = relatedProject;
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

	/**
	 * Getter for Project used by Unit Tests
	 *
	 * @return Project containing project related data
	 */
	public Project getRelatedProject()
	{
	    return relatedProject;
	}
	
	/**
	 * Setter for Project
	 *
	 * @param relatedProject containing containing project related data
	 */
	public void setRelatedProject(Project relatedProject)
	{
	    this.relatedProject = relatedProject;
	}
}
