package com.davidkoudela.crucible.rest.response;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Description: Response Repository Name List holder used by Jackson JSON processor
 *              containing provided code, message, cause and repository names used in REST responses
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 21.11.2015
 */
@JsonAutoDetect
public class ResponseRepositoryNameListImpl implements ResponseRepositoryNameList {
	@JsonProperty
	Map<String, String> response;
	@JsonProperty
	Set<String> repositoryNames;

	public ResponseRepositoryNameListImpl() {
		this.response = new LinkedHashMap<String, String>();
		this.repositoryNames = new LinkedHashSet<String>();
	}

	public ResponseRepositoryNameListImpl(Map<String, String> response, Set<String> repositoryNames) {
		this.response = response;
		this.repositoryNames = repositoryNames;
	}

	public Set<String> getRepositoryNames() {
		return repositoryNames;
	}

	public void setRepositoryNames(Set<String> repositoryNames) {
		this.repositoryNames = repositoryNames;
	}

	public Map<String, String> getResponse() {
		return response;
	}

	public void setResponse(Map<String, String> response) {
		this.response = response;
	}
}
