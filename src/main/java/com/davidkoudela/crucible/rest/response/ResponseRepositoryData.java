package com.davidkoudela.crucible.rest.response;

import com.davidkoudela.crucible.model.RepositoryRestData;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description: Response Repository Name List holder used by Jackson JSON processor
 *              containing provided code, message, cause and repository data used in REST responses
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 21.11.2015
 */
@JsonAutoDetect
public class ResponseRepositoryData {
	@JsonProperty
	Map<String, String> response;
	@JsonProperty
	RepositoryRestData repositoryRestData;

	public ResponseRepositoryData(Map<String, String> response, RepositoryRestData repositoryRestData) {
		this.response = response;
		this.repositoryRestData = repositoryRestData;
	}

	public ResponseRepositoryData() {
		this.response = new LinkedHashMap<String, String>();
		this.repositoryRestData = null;
	}

	public Map<String, String> getResponse() {
		return response;
	}

	public void setResponse(Map<String, String> response) {
		this.response = response;
	}

	public RepositoryRestData getRepositoryRestData() {
		return repositoryRestData;
	}

	public void setRepositoryRestData(RepositoryRestData repositoryRestData) {
		this.repositoryRestData = repositoryRestData;
	}
}
