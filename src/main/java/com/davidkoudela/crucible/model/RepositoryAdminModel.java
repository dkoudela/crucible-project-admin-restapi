package com.davidkoudela.crucible.model;

import com.davidkoudela.crucible.rest.response.ResponseRepositoryDataImpl;
import com.davidkoudela.crucible.rest.response.ResponseRepositoryNameListImpl;
import com.davidkoudela.crucible.rest.response.ResponseRepositoryOperationImpl;

/**
 * Description: Model class containing the logic functionality for Create / Modify / Delete Crucible Repositories.
 * Copyright (C) 2016 David Koudela
 *
 * @author dkoudela
 * @since 14.10.2016
 */
public interface RepositoryAdminModel {
	public ResponseRepositoryOperationImpl newRepository(RepositoryRestData repositoryRestData);
	public ResponseRepositoryOperationImpl updateRepository(RepositoryRestData repositoryRestData);
	public ResponseRepositoryOperationImpl deleteRepository(RepositoryRestData repositoryRestData);
	public ResponseRepositoryNameListImpl listRepository();
	public ResponseRepositoryDataImpl listRepository(String repositoryName);
}
