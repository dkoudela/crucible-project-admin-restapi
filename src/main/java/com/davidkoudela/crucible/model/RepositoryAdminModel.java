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
			this.repositoryAdminService.setRepositoryOptions(repositoryRestData.name, setRepositoryOptions(options, repositoryRestData));

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
			setRepositoryRestData(options, repositoryData, repositoryRestData);
		}
		catch (Exception e)
		{
			System.out.println("repository list failed: " + e);
			return ResponseRepositoryFactory.constructResponseWithRepositoryData("400", "repository list failed", e.getMessage(), repositoryRestData);
		}
		return ResponseRepositoryFactory.constructResponseWithRepositoryData("200", "operation succeeded", "", repositoryRestData);
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
			if (null != repositoryRestData.extraOptions.simpleLinkers) {
				List<SimpleLinker> simpleLinkers = new ArrayList<SimpleLinker>();
				for (SimpleLinkerRestData simpleLinkerRestData : repositoryRestData.extraOptions.simpleLinkers) {
					SimpleLinker simpleLinker = new SimpleLinker(simpleLinkerRestData.getRegex(), simpleLinkerRestData.href);
					simpleLinker.setDescription(simpleLinkerRestData.description);
					simpleLinkers.add(simpleLinker);
				}
				options.setSimpleLinkers(simpleLinkers);
			}
			if (null != repositoryRestData.extraOptions.advancedLinkers) {
				List<AdvancedLinker> advancedLinkers = new ArrayList<AdvancedLinker>();
				for (AdvancedLinkerRestData advancedLinkerRestData : repositoryRestData.extraOptions.advancedLinkers) {
					AdvancedLinker advancedLinker = new AdvancedLinker(advancedLinkerRestData.syntaxDef);
					advancedLinker.setDescription(advancedLinkerRestData.description);
					advancedLinkers.add(advancedLinker);
				}
				options.setAdvancedLinkers(advancedLinkers);
			}
			if (null != repositoryRestData.extraOptions.hiddenDirectories) {
				List<CaseAwarePath> hiddenDirectories = new ArrayList<CaseAwarePath>();
				for (IncludeExcludeRestData includeExcludeRestData : repositoryRestData.extraOptions.hiddenDirectories) {
					CaseAwarePath caseAwarePath = new CaseAwarePath(includeExcludeRestData.path, includeExcludeRestData.caseSensitive);
					hiddenDirectories.add(caseAwarePath);
				}
				options.setHiddenDirectories(hiddenDirectories);
			}
			if (null != repositoryRestData.extraOptions.requiredGroups)
				options.setRequiredGroups(repositoryRestData.extraOptions.requiredGroups);
			if (null != repositoryRestData.extraOptions.showCheckoutURL)
				options.setShowCheckoutURL(repositoryRestData.extraOptions.showCheckoutURL);
			if (null != repositoryRestData.extraOptions.checkoutURL)
				options.setCheckoutURL(repositoryRestData.extraOptions.checkoutURL);
		}

		return options;
	}

	private RepositoryRestData setRepositoryRestData(RepositoryOptions options, RepositoryData repositoryData, RepositoryRestData repositoryRestData) {
		if (null == repositoryRestData.extraOptions)
			repositoryRestData.extraOptions = new RepositoryRestExtraData();
		repositoryRestData.extraOptions.usingDefaultsPermissions = options.isUsingDefaultsPermissions();
		repositoryRestData.extraOptions.allowAnon = options.isAllowAnon();
		repositoryRestData.extraOptions.allowLoggedUsers = options.isAllowLoggedUsers();
		repositoryRestData.extraOptions.watchesEnabled = options.isWatchesEnabled();
		repositoryRestData.extraOptions.changesetDiscussionsEnabled = options.isChangesetDiscussionsEnabled();
		repositoryRestData.extraOptions.allowIncludes = new ArrayList<IncludeExcludeRestData>();
		for (CaseAwarePath caseAwarePath : options.getAllowIncludes()) {
			IncludeExcludeRestData includeExcludeRestData = new IncludeExcludeRestData();
			includeExcludeRestData.caseSensitive = caseAwarePath.isCaseSensitive();
			includeExcludeRestData.path = caseAwarePath.getPath();
			repositoryRestData.extraOptions.allowIncludes.add(includeExcludeRestData);
		}
		repositoryRestData.extraOptions.allowExcludes = new ArrayList<IncludeExcludeRestData>();
		for (CaseAwarePathGlob caseAwarePathGlob : options.getAllowExcludes()) {
			IncludeExcludeRestData includeExcludeRestData = new IncludeExcludeRestData();
			includeExcludeRestData.caseSensitive = caseAwarePathGlob.isCaseSensitive();
			includeExcludeRestData.path = caseAwarePathGlob.getGlob();
			repositoryRestData.extraOptions.allowExcludes.add(includeExcludeRestData);
		}
		TarballSettings tarballSettings = options.getTarballSettings();
		if (null != tarballSettings) {
			repositoryRestData.extraOptions.tarballSettings = new TarballRestData();
			repositoryRestData.extraOptions.tarballSettings.enabled = tarballSettings.isEnabled();
			repositoryRestData.extraOptions.tarballSettings.maxFiles = tarballSettings.getMaxFiles();
			repositoryRestData.extraOptions.tarballSettings.excludes = new ArrayList<TarballExcludesRestData>();
			for (TarballSettings.Exclude exclude : tarballSettings.getExcludes()) {
				TarballExcludesRestData tarballExcludesRestData = new TarballExcludesRestData();
				tarballExcludesRestData.baseDirectory = exclude.getDirectoryPath();
				tarballExcludesRestData.excludeSubdirs = exclude.isExcludeSubDirs();
				repositoryRestData.extraOptions.tarballSettings.excludes.add(tarballExcludesRestData);
			}
		}
		CommitMessageSyntaxSettings commitMessageSyntaxSettings = options.getCommitMessageSyntaxSettings();
		if (null != commitMessageSyntaxSettings) {
			repositoryRestData.extraOptions.commitMessageSyntaxSettings = new CommitMessageSyntaxRestData();
			repositoryRestData.extraOptions.commitMessageSyntaxSettings.syntaxType = CommitMessageSyntaxRestData.SyntaxType.create(commitMessageSyntaxSettings.getSyntaxType().name());
			repositoryRestData.extraOptions.commitMessageSyntaxSettings.wikiSyntaxStartDate = commitMessageSyntaxSettings.getWikiSyntaxStartDate();
		}
		repositoryRestData.extraOptions.maxIndexableSize = options.getMaxIndexableSize();
		UpdateOptions updateOptions = options.getUpdateOptions();
		if (null != updateOptions) {
			repositoryRestData.extraOptions.updateOptions = new UpdateRestData();
			if (RepositoryData.Type.CVS == repositoryData.getType()) {
				repositoryRestData.extraOptions.updateOptions.cvs = new CvsUpdateRestData();
				CvsUpdateOptions cvsUpdateOptions = (CvsUpdateOptions) updateOptions;
				repositoryRestData.extraOptions.updateOptions.cvs.fullScanInterval = cvsUpdateOptions.getFullScanInterval();
				repositoryRestData.extraOptions.updateOptions.cvs.historyFile = cvsUpdateOptions.getHistoryFile();
				repositoryRestData.extraOptions.updateOptions.cvs.stripPrefix = cvsUpdateOptions.getStripPrefix();
				repositoryRestData.extraOptions.updateOptions.pollingInterval = cvsUpdateOptions.getPollInterval();
			} else {
				PolledUpdateOptions polledUpdateOptions = (PolledUpdateOptions) updateOptions;
				repositoryRestData.extraOptions.updateOptions.pollingInterval = polledUpdateOptions.getPollInterval();
			}
		}
		repositoryRestData.extraOptions.simpleLinkers = new ArrayList<SimpleLinkerRestData>();
		for (SimpleLinker simpleLinker : options.getSimpleLinkers()) {
			SimpleLinkerRestData simpleLinkerRestData = new SimpleLinkerRestData();
			simpleLinkerRestData.description = simpleLinker.getDescription();
			simpleLinkerRestData.regex = simpleLinker.getRegex().toString();
			simpleLinkerRestData.href = simpleLinker.getHref();
			repositoryRestData.extraOptions.simpleLinkers.add(simpleLinkerRestData);
		}
		repositoryRestData.extraOptions.advancedLinkers = new ArrayList<AdvancedLinkerRestData>();
		for (AdvancedLinker advancedLinker : options.getAdvancedLinkers()) {
			AdvancedLinkerRestData advancedLinkerRestData = new AdvancedLinkerRestData();
			advancedLinkerRestData.description = advancedLinker.getDescription();
			advancedLinkerRestData.syntaxDef = advancedLinker.getSyntaxDef();
			repositoryRestData.extraOptions.advancedLinkers.add(advancedLinkerRestData);
		}
		repositoryRestData.extraOptions.hiddenDirectories = new ArrayList<IncludeExcludeRestData>();
		for (CaseAwarePath caseAwarePath : options.getHiddenDirectories()) {
			IncludeExcludeRestData includeExcludeRestData = new IncludeExcludeRestData();
			includeExcludeRestData.caseSensitive = caseAwarePath.isCaseSensitive();
			includeExcludeRestData.path = caseAwarePath.getPath();
			repositoryRestData.extraOptions.hiddenDirectories.add(includeExcludeRestData);
		}
		repositoryRestData.extraOptions.requiredGroups = options.getRequiredGroups();
		repositoryRestData.extraOptions.showCheckoutURL = options.getShowCheckoutURL();
		repositoryRestData.extraOptions.checkoutURL = options.getCheckoutURL();

		return repositoryRestData;
	}
}
