package com.davidkoudela.crucible.rest.response;

import com.davidkoudela.crucible.model.RepositoryRestData;

import java.util.Map;

/**
 * Description: Response Repository Name List interface
 *              containing provided code, message, cause and repository data used in REST responses
 * Copyright (C) 2016 David Koudela
 *
 * @author dkoudela
 * @since 18.10.2016
 */
public interface ResponseRepositoryData {
	public Map<String, String> getResponse();
	public void setResponse(Map<String, String> response);
	public RepositoryRestData getRepositoryRestData();
	public void setRepositoryRestData(RepositoryRestData repositoryRestData);
}
