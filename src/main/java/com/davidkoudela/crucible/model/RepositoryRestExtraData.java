package com.davidkoudela.crucible.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Description: Model class containing REST data of FE's extra repository parameters.
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 14.8.2015
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown=true)
public class RepositoryRestExtraData {
	public Boolean usingDefaultsPermissions;
	public Boolean allowAnon;
	public Boolean allowLoggedUsers;
	public Boolean watchesEnabled;
	public Boolean changesetDiscussionsEnabled;
	public List<IncludeExcludeRestData> allowIncludes;
	public List<IncludeExcludeRestData> allowExcludes;
	public TarballRestData tarballSettings;
	public CommitMessageSyntaxRestData commitMessageSyntaxSettings;
	public Long maxIndexableSize;

	public void verify() throws Exception
	{
		if (null != allowIncludes)
			for (IncludeExcludeRestData includeExcludeRestData : allowIncludes)
				includeExcludeRestData.verify();
		if (null != allowExcludes)
			for (IncludeExcludeRestData includeExcludeRestData : allowExcludes)
				includeExcludeRestData.verify();
		if (null != tarballSettings)
			tarballSettings.verify();
		if (null != commitMessageSyntaxSettings)
			commitMessageSyntaxSettings.verify();
	}
}
