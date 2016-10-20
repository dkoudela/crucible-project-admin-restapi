package it.com.davidkoudela.crucible.framework;

import com.davidkoudela.crucible.model.RepositoryRestData;

import java.util.Map;

/**
 * Description: Repository Rest Data service managing JSON (de)serialization for Repository Rest Data
 * Copyright (C) 2016 David Koudela
 *
 * @author dkoudela
 * @since 18.10.2016
 */
public interface RepositoryRestDataService {
	public RepositoryRestData createRepositoryData(String json);
	public Map<String, String> compareRepositoryData(String json, RepositoryRestData repositoryRestData);
}
