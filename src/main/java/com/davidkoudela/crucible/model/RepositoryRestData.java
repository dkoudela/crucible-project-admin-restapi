package com.davidkoudela.crucible.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Description: Model class containing REST data of all FE's repository parameters (superclass).
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 13.8.2015
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown=true)
public class RepositoryRestData implements Serializable
{
	public String name;
	public String description;
	public Boolean storeDiff;
	public Boolean enabled;
	public Boolean started;
	public RepositoryRestExtraData extraOptions;
	public GitRepositoryRestData git;
	public P4RepositoryRestData p4;

	public void verify() throws Exception
	{
		if (null == name)
			throw new Exception("Missing name");
		if (null == git && null == p4)
			throw new Exception("Only git and p4 supported and not specified");
		if (null != git)
			git.verify();
		if (null != p4)
			p4.verify();
		if (null != extraOptions)
			extraOptions.verify();
	}

	public boolean isGit() { return null != this.git; }
	public boolean isP4() { return null != this.p4; }
}
