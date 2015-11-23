package com.davidkoudela.crucible.model;

import com.atlassian.fisheye.spi.admin.data.*;
import com.atlassian.fisheye.spi.admin.services.RepositoryAdminService;
import com.davidkoudela.crucible.rest.response.ResponseRepositoryData;
import com.davidkoudela.crucible.rest.response.ResponseRepositoryFactory;
import com.davidkoudela.crucible.rest.response.ResponseRepositoryNameList;
import com.davidkoudela.crucible.rest.response.ResponseRepositoryOperation;

import java.util.*;

/**
 * Description: Model class containing the logic functionality for Create / Modify / Delete Crucible Repositories.
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 12.8.2015
 */
@org.springframework.stereotype.Service("repositoryAdminModel")
public class RepositoryAdminModel
{
	private RepositoryAdminService repositoryAdminService;

	@org.springframework.beans.factory.annotation.Autowired
	public RepositoryAdminModel(RepositoryAdminService repositoryAdminService)
	{
		this.repositoryAdminService = repositoryAdminService;
	}

	public ResponseRepositoryOperation newRepository(RepositoryRestData repositoryRestData)
	{
		try {
			repositoryRestData.verify();

			RepositoryData repositoryData = RepositoryDataFactory.createRepositoryData(repositoryRestData);
			this.repositoryAdminService.create(repositoryData);

			RepositoryOptions options = this.repositoryAdminService.getRepositoryOptions(repositoryRestData.name);
			this.repositoryAdminService.setRepositoryOptions(repositoryRestData.name, RepositoryDataFactory.setRepositoryOptions(options, repositoryRestData));

			if (null != repositoryRestData.enabled && true == repositoryRestData.enabled) {
				this.repositoryAdminService.enable(repositoryRestData.name);
				if (null != repositoryRestData.started && true == repositoryRestData.started) {
					this.repositoryAdminService.start(repositoryRestData.name);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("repository creation failed: " + e);
			return ResponseRepositoryFactory.constructResponse("400", "repository creation failed", e.getMessage());
		}
		return ResponseRepositoryFactory.constructResponse("200", "operation succeeded", "");
	}

	public ResponseRepositoryOperation updateRepository(RepositoryRestData repositoryRestData) {
		try {
			repositoryRestData.verify();

			RepositoryData repositoryData = RepositoryDataFactory.modifyRepositoryData(repositoryRestData, this.repositoryAdminService.getRepositoryData(repositoryRestData.name));
			this.repositoryAdminService.update(repositoryData);

			RepositoryOptions options = this.repositoryAdminService.getRepositoryOptions(repositoryRestData.name);
			this.repositoryAdminService.setRepositoryOptions(repositoryRestData.name, RepositoryDataFactory.setRepositoryOptions(options, repositoryRestData));

			if (null != repositoryRestData.enabled) {
				if (true == repositoryRestData.enabled && false == this.repositoryAdminService.isEnabled(repositoryRestData.name)) {
					this.repositoryAdminService.enable(repositoryRestData.name);
				} else if (false == repositoryRestData.enabled && true == this.repositoryAdminService.isEnabled(repositoryRestData.name)) {
					this.repositoryAdminService.disable(repositoryRestData.name);
				}
			}
			if (null != repositoryRestData.started && true == this.repositoryAdminService.isEnabled(repositoryRestData.name)) {
				if (true == repositoryRestData.started && RepositoryState.RUNNING != this.repositoryAdminService.getState(repositoryRestData.name)) {
					this.repositoryAdminService.start(repositoryRestData.name);
				} else if (false == repositoryRestData.started && RepositoryState.STOPPED != this.repositoryAdminService.getState(repositoryRestData.name)) {
					this.repositoryAdminService.stop(repositoryRestData.name);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("repository modification failed: " + e);
			return ResponseRepositoryFactory.constructResponse("400", "repository modification failed", e.getMessage());
		}
		return ResponseRepositoryFactory.constructResponse("200", "operation succeeded", "");
	}

	public ResponseRepositoryOperation deleteRepository(RepositoryRestData repositoryRestData) {
		try {
			repositoryRestData.verifyOnDelete();

			this.repositoryAdminService.delete(repositoryRestData.name);
		}
		catch (Exception e)
		{
			System.out.println("repository delete failed: " + e);
			return ResponseRepositoryFactory.constructResponse("400", "repository delete failed", e.getMessage());
		}
		return ResponseRepositoryFactory.constructResponse("200", "operation succeeded", "");
	}

	public ResponseRepositoryNameList listRepository() {
		Set<String> names = new LinkedHashSet<String>();
		try {
			names.addAll(this.repositoryAdminService.getNames());
		}
		catch (Exception e)
		{
			System.out.println("repository list failed: " + e);
			return ResponseRepositoryFactory.constructResponseWithList("400", "repository list failed", e.getMessage(), names);
		}
		return ResponseRepositoryFactory.constructResponseWithList("200", "operation succeeded", "", names);
	}

	public ResponseRepositoryData listRepository(String repositoryName) {
		RepositoryRestData repositoryRestData = new RepositoryRestData();
		try {
			RepositoryData repositoryData = this.repositoryAdminService.getRepositoryData(repositoryName);
			RepositoryOptions options = this.repositoryAdminService.getRepositoryOptions(repositoryName);
			RepositoryDataFactory.setRepositoryRestData(options, repositoryData, repositoryRestData);
		}
		catch (Exception e)
		{
			System.out.println("repository list failed: " + e);
			return ResponseRepositoryFactory.constructResponseWithRepositoryData("400", "repository list failed", e.getMessage(), repositoryRestData);
		}
		return ResponseRepositoryFactory.constructResponseWithRepositoryData("200", "operation succeeded", "", repositoryRestData);
	}

}
