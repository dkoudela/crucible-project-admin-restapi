package com.davidkoudela.crucible.rest.response;

import java.util.Map;
import java.util.Set;

/**
 * Description: Response Repository Name List interface
 *              containing provided code, message, cause and repository names used in REST responses
 * Copyright (C) 2016 David Koudela
 *
 * @author dkoudela
 * @since 17.10.2016
 */
public interface ResponseRepositoryNameList {
	public Set<String> getRepositoryNames();
	public void setRepositoryNames(Set<String> repositoryNames);
	public Map<String, String> getResponse();
	public void setResponse(Map<String, String> response);
}
