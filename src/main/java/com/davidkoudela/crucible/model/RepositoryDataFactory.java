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

		if (null != repositoryRestData.description) repositoryData.setDescription(repositoryRestData.description);
		if (null != repositoryRestData.storeDiff) repositoryData.setStoreDiff(repositoryRestData.storeDiff);

		return repositoryData;
	}

	private static RepositoryData createGitRepositoryData(RepositoryRestData repositoryRestData)
	{
		GitRepositoryData gitRepositoryData = new GitRepositoryData(repositoryRestData.name, repositoryRestData.git.location);

		if (null != repositoryRestData.git.auth) {
			AuthenticationData authenticationData = new AuthenticationData();
			if (null != repositoryRestData.git.auth.privateKey && null != repositoryRestData.git.auth.publicKey) {
				if (null != repositoryRestData.git.auth.passPhrase) {
					authenticationData.setAuthenticationStyle(AuthenticationStyle.SSH_KEY_WITH_PASSPHRASE);
					authenticationData.setPassphrase(repositoryRestData.git.auth.passPhrase);
				} else {
					authenticationData.setAuthenticationStyle(AuthenticationStyle.SSH_KEY_WITHOUT_PASSPHRASE);
				}
				authenticationData.setPublicKey(repositoryRestData.git.auth.publicKey);
				authenticationData.setPrivateKey(repositoryRestData.git.auth.privateKey);
			} else if (null != repositoryRestData.git.auth.password) {
				authenticationData.setAuthenticationStyle(AuthenticationStyle.PASSWORD);
				authenticationData.setPassword(repositoryRestData.git.auth.password);
			}
			gitRepositoryData.setAuthentication(authenticationData);
		}

		if (null != repositoryRestData.git.path) gitRepositoryData.setPath(repositoryRestData.git.path);
		if (null != repositoryRestData.git.blockSize) gitRepositoryData.setBlockSize(repositoryRestData.git.blockSize);
		if (null != repositoryRestData.git.commandTimeout) gitRepositoryData.setCommandTimeout(repositoryRestData.git.commandTimeout);
		if (null != repositoryRestData.git.renameDetection) gitRepositoryData.setRenameOption(repositoryRestData.git.getRenameDetectionInteger());

		return gitRepositoryData;
	}

	private static RepositoryData createP4RepositoryData(RepositoryRestData repositoryRestData)
	{
		P4RepositoryData p4RepositoryData = new P4RepositoryData(repositoryRestData.name, repositoryRestData.p4.server, repositoryRestData.p4.path);

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
}
