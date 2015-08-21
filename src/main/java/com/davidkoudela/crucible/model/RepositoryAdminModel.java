package com.davidkoudela.crucible.model;

import com.atlassian.fisheye.spi.admin.data.*;
import com.atlassian.fisheye.spi.admin.services.RepositoryAdminService;
import com.davidkoudela.crucible.rest.response.ResponseRepositoryFactory;
import com.davidkoudela.crucible.rest.response.ResponseRepositoryOperation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
			this.repositoryAdminService.setRepositoryOptions(repositoryRestData.name, setRepositoryOptions(options, repositoryRestData));

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
			return ResponseRepositoryFactory.constructResponse("400", "repository modification failed", e.getMessage());
		}
		return ResponseRepositoryFactory.constructResponse("200", "operation succeeded", "");
	}

	private RepositoryOptions setRepositoryOptions(RepositoryOptions options, RepositoryRestData repositoryRestData) {
		if (null != repositoryRestData.extraOptions) {
			if (null != repositoryRestData.extraOptions.usingDefaultsPermissions)
				options.setUsingDefaultsPermissions(repositoryRestData.extraOptions.usingDefaultsPermissions);
			if (null != repositoryRestData.extraOptions.allowAnon)
				options.setAllowAnon(repositoryRestData.extraOptions.allowAnon);
			if (null != repositoryRestData.extraOptions.allowLoggedUsers)
				options.setAllowLoggedUsers(repositoryRestData.extraOptions.allowLoggedUsers);
			if (null != repositoryRestData.extraOptions.watchesEnabled)
				options.setWatchesEnabled(repositoryRestData.extraOptions.watchesEnabled);
			if (null != repositoryRestData.extraOptions.changesetDiscussionsEnabled)
				options.setChangesetDiscussionsEnabled(repositoryRestData.extraOptions.changesetDiscussionsEnabled);
			if (null != repositoryRestData.extraOptions.allowIncludes) {
				List<CaseAwarePath> allowIncludes = new ArrayList<CaseAwarePath>();
				for (IncludeExcludeRestData includeExcludeRestData : repositoryRestData.extraOptions.allowIncludes) {
					CaseAwarePath caseAwarePath = new CaseAwarePath(includeExcludeRestData.path, includeExcludeRestData.caseSensitive);
					allowIncludes.add(caseAwarePath);
				}
				options.setAllowIncludes(allowIncludes);
			}
			if (null != repositoryRestData.extraOptions.allowExcludes) {
				List<CaseAwarePathGlob> allowExcludes = new ArrayList<CaseAwarePathGlob>();
				for (IncludeExcludeRestData includeExcludeRestData : repositoryRestData.extraOptions.allowExcludes) {
					CaseAwarePathGlob caseAwarePathGlob = new CaseAwarePathGlob(includeExcludeRestData.path, includeExcludeRestData.caseSensitive);
					allowExcludes.add(caseAwarePathGlob);
				}
				options.setAllowExcludes(allowExcludes);
			}
			if (null != repositoryRestData.extraOptions.tarballSettings) {
				if (null != repositoryRestData.extraOptions.tarballSettings.enabled)
				{
					TarballSettings tarballSettings = new TarballSettings();
					if (true == repositoryRestData.extraOptions.tarballSettings.enabled) {
						tarballSettings.setEnabled(true);
						if (null != repositoryRestData.extraOptions.tarballSettings.maxFiles)
							tarballSettings.setMaxFiles(repositoryRestData.extraOptions.tarballSettings.maxFiles);
						if (null != repositoryRestData.extraOptions.tarballSettings.excludes) {
							List<TarballSettings.Exclude> excludes = new ArrayList<TarballSettings.Exclude>();
							for (TarballExcludesRestData restExclude : repositoryRestData.extraOptions.tarballSettings.excludes) {
								TarballSettings.Exclude exclude = new TarballSettings.Exclude(restExclude.baseDirectory, restExclude.excludeSubdirs);
								excludes.add(exclude);
							}
							tarballSettings.setExcludes(excludes);
						}
					} else {
						tarballSettings.setEnabled(false);
					}
					options.setTarballSettings(tarballSettings);
				}
			}
			if (null != repositoryRestData.extraOptions.commitMessageSyntaxSettings) {
				CommitMessageSyntaxSettings commitMessageSyntaxSettings = null;
				if (CommitMessageSyntaxRestData.SyntaxType.WIKI == repositoryRestData.extraOptions.commitMessageSyntaxSettings.syntaxType)
				{
					commitMessageSyntaxSettings = new CommitMessageSyntaxSettings(CommitMessageSyntaxSettings.SyntaxType.WIKI,
							repositoryRestData.extraOptions.commitMessageSyntaxSettings.wikiSyntaxStartDate);
				} else {
					commitMessageSyntaxSettings = new CommitMessageSyntaxSettings(CommitMessageSyntaxSettings.SyntaxType.PLAIN, new Date());
				}
				options.setCommitMessageSyntaxSettings(commitMessageSyntaxSettings);
			}
		}
		if (null != repositoryRestData.extraOptions.maxIndexableSize) options.setMaxIndexableSize(repositoryRestData.extraOptions.maxIndexableSize);
		if (null != repositoryRestData.extraOptions.updateOptions) {
			UpdateOptions updateOptions = null;
			if (repositoryRestData.isCvs()) {
				CvsUpdateRestData cvs = repositoryRestData.extraOptions.updateOptions.cvs;
				if (null != cvs)
					updateOptions = new CvsUpdateOptions(
							repositoryRestData.extraOptions.updateOptions.pollingInterval, cvs.fullScanInterval,
							cvs.historyFile, cvs.stripPrefix);
				else
					updateOptions = CvsUpdateOptions.DEFAULT;
			} else {
				updateOptions = new PolledUpdateOptions(repositoryRestData.extraOptions.updateOptions.pollingInterval);
			}
			options.setUpdateOptions(updateOptions);
		}

		return options;
	}
}
