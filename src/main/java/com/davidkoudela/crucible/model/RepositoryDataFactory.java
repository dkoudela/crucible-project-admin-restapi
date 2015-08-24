package com.davidkoudela.crucible.model;

import com.atlassian.fisheye.spi.admin.data.*;

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
		if (repositoryRestData.isGit())
		{
			repositoryData = createGitRepositoryData(repositoryRestData);
		}
		else if (repositoryRestData.isP4())
		{
			repositoryData = createP4RepositoryData(repositoryRestData);
		}
		else if (repositoryRestData.isCvs())
		{
			repositoryData = createCvsRepositoryData(repositoryRestData);
		}
		else if (repositoryRestData.isHg())
		{
			repositoryData = createHgRepositoryData(repositoryRestData);
		}
		else if (repositoryRestData.isSvn())
		{
			repositoryData = createSvnRepositoryData(repositoryRestData);
		}

		return modifyCommonRepositoryData(repositoryRestData, repositoryData);
	}

	public static RepositoryData modifyRepositoryData(RepositoryRestData repositoryRestData, RepositoryData repositoryData)
	{
		if (repositoryRestData.isGit() && RepositoryData.Type.GIT == repositoryData.getType())
		{
			repositoryData = modifyGitRepositoryData(repositoryRestData, (GitRepositoryData) repositoryData);
		}
		else if (repositoryRestData.isP4() && RepositoryData.Type.PERFORCE == repositoryData.getType())
		{
			repositoryData = modifyP4RepositoryData(repositoryRestData, (P4RepositoryData) repositoryData);
		}
		else if (repositoryRestData.isCvs() && RepositoryData.Type.CVS == repositoryData.getType())
		{
			repositoryData = modifyCvsRepositoryData(repositoryRestData, (CvsRepositoryData) repositoryData);
		}
		else if (repositoryRestData.isHg())
		{
			repositoryData = modifyHgRepositoryData(repositoryRestData, (HgRepositoryData) repositoryData);
		}
		else if (repositoryRestData.isSvn())
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
		if (null != repositoryRestData.p4.charset) p4RepositoryData.setCharset(repositoryRestData.p4.charset);
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
		if (null != repositoryRestData.cvs.charset) cvsRepositoryData.setCharset(repositoryRestData.cvs.getCharset());
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
		if (null != repositoryRestData.svn.charset) svnRepositoryData.setCharset(repositoryRestData.svn.getCharset());
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

}
