package com.davidkoudela.crucible.model;

import com.atlassian.fisheye.spi.admin.data.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description: Factory method for creating {@link com.atlassian.fisheye.spi.admin.data.RepositoryData}.
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 14.8.2015
 */
public class RepositoryDataFactory {
	public static RepositoryData createRepositoryData(RepositoryRestData repositoryRestData)
	{
		RepositoryData repositoryData = null;
		if (repositoryRestData.isItGit())
		{
			repositoryData = createGitRepositoryData(repositoryRestData);
		}
		else if (repositoryRestData.isItP4())
		{
			repositoryData = createP4RepositoryData(repositoryRestData);
		}
		else if (repositoryRestData.isItCvs())
		{
			repositoryData = createCvsRepositoryData(repositoryRestData);
		}
		else if (repositoryRestData.isItHg())
		{
			repositoryData = createHgRepositoryData(repositoryRestData);
		}
		else if (repositoryRestData.isItSvn())
		{
			repositoryData = createSvnRepositoryData(repositoryRestData);
		}

		return modifyCommonRepositoryData(repositoryRestData, repositoryData);
	}

	public static RepositoryData modifyRepositoryData(RepositoryRestData repositoryRestData, RepositoryData repositoryData)
	{
		if (repositoryRestData.isItGit() && RepositoryData.Type.GIT == repositoryData.getType())
		{
			repositoryData = modifyGitRepositoryData(repositoryRestData, (GitRepositoryData) repositoryData);
		}
		else if (repositoryRestData.isItP4() && RepositoryData.Type.PERFORCE == repositoryData.getType())
		{
			repositoryData = modifyP4RepositoryData(repositoryRestData, (P4RepositoryData) repositoryData);
		}
		else if (repositoryRestData.isItCvs() && RepositoryData.Type.CVS == repositoryData.getType())
		{
			repositoryData = modifyCvsRepositoryData(repositoryRestData, (CvsRepositoryData) repositoryData);
		}
		else if (repositoryRestData.isItHg())
		{
			repositoryData = modifyHgRepositoryData(repositoryRestData, (HgRepositoryData) repositoryData);
		}
		else if (repositoryRestData.isItSvn())
		{
			repositoryData = modifySvnRepositoryData(repositoryRestData, (SvnRepositoryData) repositoryData);
		}

		return modifyCommonRepositoryData(repositoryRestData, repositoryData);
	}

	private static RepositoryData modifyCommonRepositoryData(RepositoryRestData repositoryRestData, RepositoryData repositoryData)
	{
		if (null != repositoryRestData.description) repositoryData.setDescription(repositoryRestData.description);
		if (null != repositoryRestData.storeDiff) repositoryData.setStoreDiff(repositoryRestData.storeDiff);

		return repositoryData;
	}

	private static AuthenticationData getAuthenticationData(KeyAuthenticationRestData auth)
	{
		AuthenticationData authenticationData = new AuthenticationData();
		if (null != auth.privateKey && null != auth.publicKey) {
			if (null != auth.passPhrase) {
				authenticationData.setAuthenticationStyle(AuthenticationStyle.SSH_KEY_WITH_PASSPHRASE);
				authenticationData.setPassphrase(auth.passPhrase);
			} else {
				authenticationData.setAuthenticationStyle(AuthenticationStyle.SSH_KEY_WITHOUT_PASSPHRASE);
			}
			authenticationData.setPublicKey(auth.publicKey);
			authenticationData.setPrivateKey(auth.privateKey);
		} else if (null != auth.password) {
			authenticationData.setAuthenticationStyle(AuthenticationStyle.PASSWORD);
			authenticationData.setPassword(auth.password);
		}
		return authenticationData;
	}
	private static KeyAuthenticationRestData getKeyAuthenticationRestData(AuthenticationData auth)
	{
		KeyAuthenticationRestData keyAuthenticationRestData = new KeyAuthenticationRestData();
		return keyAuthenticationRestData;
	}

	private static RepositoryData createGitRepositoryData(RepositoryRestData repositoryRestData)
	{
		GitRepositoryData gitRepositoryData = new GitRepositoryData(repositoryRestData.name, repositoryRestData.git.location);
		return modifyGitRepositoryData(repositoryRestData, gitRepositoryData);
	}
	private static RepositoryData modifyGitRepositoryData(RepositoryRestData repositoryRestData, GitRepositoryData gitRepositoryData) {
		if (null != repositoryRestData.git.location) gitRepositoryData.setLocation(repositoryRestData.git.location);
		if (null != repositoryRestData.git.auth) gitRepositoryData.setAuthentication(getAuthenticationData(repositoryRestData.git.auth));
		if (null != repositoryRestData.git.path) gitRepositoryData.setPath(repositoryRestData.git.path);
		if (null != repositoryRestData.git.blockSize) gitRepositoryData.setBlockSize(repositoryRestData.git.blockSize);
		if (null != repositoryRestData.git.commandTimeout) gitRepositoryData.setCommandTimeout(repositoryRestData.git.commandTimeout);
		if (null != repositoryRestData.git.renameDetection) gitRepositoryData.setRenameOption(repositoryRestData.git.getRenameDetectionInteger());

		return gitRepositoryData;
	}

	private static RepositoryData createP4RepositoryData(RepositoryRestData repositoryRestData)
	{
		P4RepositoryData p4RepositoryData = new P4RepositoryData(repositoryRestData.name, repositoryRestData.p4.server, repositoryRestData.p4.path);
		return modifyP4RepositoryData(repositoryRestData, p4RepositoryData);
	}
	private static RepositoryData modifyP4RepositoryData(RepositoryRestData repositoryRestData, P4RepositoryData p4RepositoryData)
	{
		if (null != repositoryRestData.p4.server) p4RepositoryData.setServer(repositoryRestData.p4.server);
		if (null != repositoryRestData.p4.path) p4RepositoryData.setPath(repositoryRestData.p4.path);
		if (null != repositoryRestData.p4.username) p4RepositoryData.setUsername(repositoryRestData.p4.username);
		if (null != repositoryRestData.p4.password) p4RepositoryData.setPassword(repositoryRestData.p4.password);
		if (null != repositoryRestData.p4.blockSize) p4RepositoryData.setBlockSize(repositoryRestData.p4.blockSize);
		if (null != repositoryRestData.p4.caseSensitive) p4RepositoryData.setCaseSensitive(repositoryRestData.p4.caseSensitive);
		if (null != repositoryRestData.p4.disableMutli) p4RepositoryData.setDisableMutli(repositoryRestData.p4.disableMutli);
		if (null != repositoryRestData.p4.charset) p4RepositoryData.setCharset(repositoryRestData.p4.charset.getCharset());
		if (null != repositoryRestData.p4.commandTimeout) p4RepositoryData.setCommandTimeout(repositoryRestData.p4.commandTimeout);
		if (null != repositoryRestData.p4.connectionsPerSecond) p4RepositoryData.setConnectionsPerSecond(repositoryRestData.p4.connectionsPerSecond);
		if (null != repositoryRestData.p4.fileLogLimit) p4RepositoryData.setFileLogLimit(repositoryRestData.p4.fileLogLimit);
		if (null != repositoryRestData.p4.initialImport) p4RepositoryData.setInitialImport(repositoryRestData.p4.initialImport);
		if (null != repositoryRestData.p4.port) p4RepositoryData.setPort(repositoryRestData.p4.port);
		if (null != repositoryRestData.p4.skipLabels) p4RepositoryData.setSkipLabels(repositoryRestData.p4.skipLabels);
		if (null != repositoryRestData.p4.startRevision) p4RepositoryData.setStartRevision(repositoryRestData.p4.startRevision);
		if (null != repositoryRestData.p4.unicode) p4RepositoryData.setUnicode(repositoryRestData.p4.unicode);

		return p4RepositoryData;
	}

	private static RepositoryData createCvsRepositoryData(RepositoryRestData repositoryRestData)
	{
		CvsRepositoryData cvsRepositoryData = new CvsRepositoryData(repositoryRestData.name, repositoryRestData.cvs.directory);
		return modifyCvsRepositoryData(repositoryRestData, cvsRepositoryData);
	}
	private static RepositoryData modifyCvsRepositoryData(RepositoryRestData repositoryRestData, CvsRepositoryData cvsRepositoryData)
	{
		if (null != repositoryRestData.cvs.directory) cvsRepositoryData.setDirectory(repositoryRestData.cvs.directory);
		if (null != repositoryRestData.cvs.charset) cvsRepositoryData.setCharset(repositoryRestData.cvs.charset.getCharset());
		return cvsRepositoryData;
	}

	private static RepositoryData createHgRepositoryData(RepositoryRestData repositoryRestData)
	{
		HgRepositoryData hgRepositoryData = new HgRepositoryData(repositoryRestData.name, repositoryRestData.hg.location);
		return modifyHgRepositoryData(repositoryRestData, hgRepositoryData);
	}
	private static RepositoryData modifyHgRepositoryData(RepositoryRestData repositoryRestData, HgRepositoryData hgRepositoryData)
	{
		if (null != repositoryRestData.hg.location) hgRepositoryData.setLocation(repositoryRestData.hg.location);
		if (null != repositoryRestData.hg.auth) hgRepositoryData.setAuthentication(getAuthenticationData(repositoryRestData.hg.auth));
		if (null != repositoryRestData.hg.path) hgRepositoryData.setPath(repositoryRestData.hg.path);
		if (null != repositoryRestData.hg.blockSize) hgRepositoryData.setBlockSize(repositoryRestData.hg.blockSize);
		if (null != repositoryRestData.hg.commandTimeout) hgRepositoryData.setCommandTimeout(repositoryRestData.hg.commandTimeout);
		return hgRepositoryData;
	}

	private static RepositoryData createSvnRepositoryData(RepositoryRestData repositoryRestData)
	{
		SvnRepositoryData svnRepositoryData = new SvnRepositoryData(repositoryRestData.name, repositoryRestData.svn.url);
		return modifySvnRepositoryData(repositoryRestData, svnRepositoryData);
	}
	private static RepositoryData modifySvnRepositoryData(RepositoryRestData repositoryRestData, SvnRepositoryData svnRepositoryData)
	{
		if (null != repositoryRestData.svn.url) svnRepositoryData.setUrl(repositoryRestData.svn.url);
		if (null != repositoryRestData.svn.path) svnRepositoryData.setPath(repositoryRestData.svn.path);
		if (null != repositoryRestData.svn.username) svnRepositoryData.setUsername(repositoryRestData.svn.username);
		if (null != repositoryRestData.svn.password) svnRepositoryData.setPassword(repositoryRestData.svn.password);
		if (null != repositoryRestData.svn.blockSize) svnRepositoryData.setBlockSize(repositoryRestData.svn.blockSize);
		if (null != repositoryRestData.svn.commandTimeout) svnRepositoryData.setCommandTimeout(repositoryRestData.svn.commandTimeout);
		if (null != repositoryRestData.svn.connectionsPerSecond) svnRepositoryData.setConnectionsPerSecond(repositoryRestData.svn.connectionsPerSecond);
		if (null != repositoryRestData.svn.charset) svnRepositoryData.setCharset(repositoryRestData.svn.charset.getCharset());
		if (null != repositoryRestData.svn.accessCode) svnRepositoryData.setAccessCode(repositoryRestData.svn.accessCode);
		if (null != repositoryRestData.svn.startRevision) svnRepositoryData.setStartRevision(repositoryRestData.svn.startRevision);
		if (null != repositoryRestData.svn.initialImport) svnRepositoryData.setInitialImport(repositoryRestData.svn.initialImport);
		if (null != repositoryRestData.svn.followBase) svnRepositoryData.setFollowBase(repositoryRestData.svn.followBase);
		if (null != repositoryRestData.svn.usingInbuiltSymbolicRules) svnRepositoryData.setUsingInbuiltSymbolicRules(repositoryRestData.svn.usingInbuiltSymbolicRules);
		if (null != repositoryRestData.svn.trunks) svnRepositoryData.setTrunks(repositoryRestData.svn.getTrunks());
		if (null != repositoryRestData.svn.branches) svnRepositoryData.setBranches(repositoryRestData.svn.getBranches());
		if (null != repositoryRestData.svn.tags) svnRepositoryData.setTags(repositoryRestData.svn.getTags());
		return svnRepositoryData;
	}

	public static RepositoryOptions setRepositoryOptions(RepositoryOptions options, RepositoryRestData repositoryRestData) {
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
				if (repositoryRestData.isItCvs()) {
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

	public static RepositoryRestData setRepositoryRestData(RepositoryOptions options, RepositoryData repositoryData, RepositoryRestData repositoryRestData) {
		setScmDetails(repositoryData, repositoryRestData);

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

	private static RepositoryRestData setScmDetails(RepositoryData repositoryData, RepositoryRestData repositoryRestData) {
		repositoryRestData.description = repositoryData.getDescription();
		repositoryRestData.storeDiff = repositoryData.isStoreDiff();
		if (RepositoryData.Type.GIT == repositoryData.getType()) {
			GitRepositoryData gitRepositoryData = (GitRepositoryData) repositoryData;
			repositoryRestData.name = gitRepositoryData.getName();
			repositoryRestData.git = new GitRepositoryRestData();
			repositoryRestData.git.location = gitRepositoryData.getLocation();
			repositoryRestData.git.auth = getKeyAuthenticationRestData(gitRepositoryData.getAuthentication());
			repositoryRestData.git.path = gitRepositoryData.getPath();
			repositoryRestData.git.blockSize = gitRepositoryData.getBlockSize();
			repositoryRestData.git.commandTimeout = gitRepositoryData.getCommandTimeout();
			repositoryRestData.git.setRenameDetectionInteger(gitRepositoryData.getRenameOption());
		} else if (RepositoryData.Type.PERFORCE == repositoryData.getType()) {
			P4RepositoryData p4RepositoryData = (P4RepositoryData) repositoryData;
			repositoryRestData.name = p4RepositoryData.getName();
			repositoryRestData.p4 = new P4RepositoryRestData();
			repositoryRestData.p4.path = p4RepositoryData.getPath();
			repositoryRestData.p4.server = p4RepositoryData.getServer();
			repositoryRestData.p4.username = p4RepositoryData.getUsername();
			repositoryRestData.p4.blockSize = p4RepositoryData.getBlockSize();
			repositoryRestData.p4.caseSensitive = p4RepositoryData.isCaseSensitive();
			repositoryRestData.p4.disableMutli = p4RepositoryData.isDisableMutli();
			if (null != p4RepositoryData.getCharset()) {
				repositoryRestData.p4.charset = new CharsetRestData();
				repositoryRestData.p4.charset.charsetName = p4RepositoryData.getCharset().name();
			}
			repositoryRestData.p4.commandTimeout = p4RepositoryData.getCommandTimeout();
			repositoryRestData.p4.connectionsPerSecond = p4RepositoryData.getConnectionsPerSecond();
			repositoryRestData.p4.fileLogLimit = p4RepositoryData.getFileLogLimit();
			repositoryRestData.p4.initialImport = p4RepositoryData.isInitialImport();
			repositoryRestData.p4.port = p4RepositoryData.getPort();
			repositoryRestData.p4.skipLabels = p4RepositoryData.isSkipLabels();
			repositoryRestData.p4.startRevision = p4RepositoryData.getStartRevision();
			repositoryRestData.p4.unicode = p4RepositoryData.isUnicode();
		} else if (RepositoryData.Type.CVS == repositoryData.getType()) {
			CvsRepositoryData cvsRepositoryData = (CvsRepositoryData) repositoryData;
			repositoryRestData.name = cvsRepositoryData.getName();
			repositoryRestData.cvs = new CvsRepositoryRestData();
			repositoryRestData.cvs.directory = cvsRepositoryData.getDirectory();
			if (null != cvsRepositoryData.getCharset()) {
				repositoryRestData.cvs.charset = new CharsetRestData();
				repositoryRestData.cvs.charset.charsetName = cvsRepositoryData.getCharset().name();
			}
		} else if (RepositoryData.Type.HG == repositoryData.getType()) {
			HgRepositoryData hgRepositoryData = (HgRepositoryData) repositoryData;
			repositoryRestData.name = hgRepositoryData.getName();
			repositoryRestData.hg = new HgRepositoryRestData();
			repositoryRestData.hg.location = hgRepositoryData.getLocation();
			repositoryRestData.hg.auth = getKeyAuthenticationRestData(hgRepositoryData.getAuthentication());
			repositoryRestData.hg.blockSize = hgRepositoryData.getBlockSize();
			repositoryRestData.hg.commandTimeout = hgRepositoryData.getCommandTimeout();
		} else if (RepositoryData.Type.SUBVERSION == repositoryData.getType()) {
			SvnRepositoryData svnRepositoryData = (SvnRepositoryData) repositoryData;
			repositoryRestData.name = svnRepositoryData.getName();
			repositoryRestData.svn = new SvnRepositoryRestData();
			repositoryRestData.svn.url = svnRepositoryData.getUrl();
			repositoryRestData.svn.path = svnRepositoryData.getPath();
			repositoryRestData.svn.username = svnRepositoryData.getUsername();
			repositoryRestData.svn.blockSize = svnRepositoryData.getBlockSize();
			repositoryRestData.svn.commandTimeout = svnRepositoryData.getCommandTimeout();
			repositoryRestData.svn.connectionsPerSecond = svnRepositoryData.getConnectionsPerSecond();
			if (null != svnRepositoryData.getCharset()) {
				repositoryRestData.svn.charset = new CharsetRestData();
				repositoryRestData.svn.charset.charsetName = svnRepositoryData.getCharset().name();
			}
			repositoryRestData.svn.accessCode = svnRepositoryData.getAccessCode();
			repositoryRestData.svn.startRevision = svnRepositoryData.getStartRevision();
			repositoryRestData.svn.initialImport = svnRepositoryData.getInitialImport();
			repositoryRestData.svn.followBase = svnRepositoryData.isFollowBase();
			repositoryRestData.svn.usingInbuiltSymbolicRules = svnRepositoryData.isUsingInbuiltSymbolicRules();
			repositoryRestData.svn.setTrunks(svnRepositoryData.getTrunks());
			repositoryRestData.svn.setBranches(svnRepositoryData.getBranches());
			repositoryRestData.svn.setTags(svnRepositoryData.getTags());
		}

		return repositoryRestData;
	}
}
