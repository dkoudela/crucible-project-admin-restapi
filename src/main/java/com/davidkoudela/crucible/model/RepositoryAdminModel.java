package com.davidkoudela.crucible.model;

import com.davidkoudela.crucible.rest.response.ResponseRepositoryNameListImpl;
import com.davidkoudela.crucible.rest.response.ResponseRepositoryOperation;

/**
 * Description: Model class containing the logic functionality for Create / Modify / Delete Crucible Repositories.
 * Copyright (C) 2016 David Koudela
 *
 * @author dkoudela
 * @since 14.10.2016
 */
public interface RepositoryAdminModel {
	public ResponseRepositoryOperation newRepository(RepositoryRestData repositoryRestData);
	public ResponseRepositoryOperation updateRepository(RepositoryRestData repositoryRestData);
	public ResponseRepositoryOperation deleteRepository(RepositoryRestData repositoryRestData);
	public ResponseRepositoryNameListImpl listRepository();
}
