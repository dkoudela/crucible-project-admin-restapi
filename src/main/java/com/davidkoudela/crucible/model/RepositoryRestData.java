package com.davidkoudela.crucible.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;
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
@JsonIgnoreProperties(ignoreUnknown=true, value = { "itGit","itP4","itCvs","itHg","itSvn" })
@JsonAutoDetect
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
	public CvsRepositoryRestData cvs;
	public HgRepositoryRestData hg;
	public SvnRepositoryRestData svn;

	public void verify() throws Exception
	{
		verifyOnDelete();
		if (null == git && null == p4 && null == cvs && null == hg && null == svn)
			throw new Exception("Only git, p4, cvs, hg and svn supported and not specified");
		if (null != git)
			git.verify();
		if (null != p4)
			p4.verify();
		if (null != cvs)
			cvs.verify();
		if (null != hg)
			hg.verify();
		if (null != svn)
			svn.verify();
		if (null != extraOptions)
			extraOptions.verify();
	}

	public void verifyOnDelete() throws Exception
	{
		if (null == name)
			throw new Exception("Missing name");
	}

	public boolean isItGit() { return null != this.git; }
	public boolean isItP4() { return null != this.p4; }
	public boolean isItCvs() { return null != this.cvs; }
	public boolean isItHg() { return null != this.hg; }
	public boolean isItSvn() { return null != this.svn; }

	public GitRepositoryRestData getGit() { return this.git; }
	public P4RepositoryRestData getP4() { return this.p4; }
	public CvsRepositoryRestData getCvs() { return this.cvs; }
	public HgRepositoryRestData getHg() { return this.hg; }
	public SvnRepositoryRestData getSvn() { return this.svn; }
}
