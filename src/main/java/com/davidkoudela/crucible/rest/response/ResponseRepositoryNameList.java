package com.davidkoudela.crucible.rest.response;

import java.util.Map;
import java.util.Set;

/**
 * Created by dkoudela on 17 Oct 16.
 */
public interface ResponseRepositoryNameList {
	public Set<String> getRepositoryNames();
	public void setRepositoryNames(Set<String> repositoryNames);
	public Map<String, String> getResponse();
	public void setResponse(Map<String, String> response);
}
