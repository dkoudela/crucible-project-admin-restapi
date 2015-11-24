package com.davidkoudela.crucible.model;

import com.atlassian.fisheye.spi.admin.data.ImportMode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.atlassian.fisheye.spi.admin.data.SvnRepositoryData;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Description: Model class containing REST data of FE's Svn repository parameter.
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 24.8.2015
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SvnRepositoryRestData {
	public String url;
	public String path;
	public String username;
	public String password;
	public Integer blockSize;
	public String commandTimeout;
	public Float connectionsPerSecond;
	public CharsetRestData charset;
	public String accessCode;
	public Long startRevision;
	public ImportMode initialImport;
	public Boolean followBase;
	public Boolean usingInbuiltSymbolicRules;
	public List<SymbolicRuleRestData> trunks;
	public List<SymbolicRuleRestData> branches;
	public List<SymbolicRuleRestData> tags;

	public void verify() throws Exception {
		if (null == url)
			throw new Exception("Missing svn.url");
		if (null != charset) {
			charset.verify();
		}
	}

	public Collection<SvnRepositoryData.SymbolicRule> getTrunks()
	{
		return getSymbolicRule(this.trunks);
	}
	public Collection<SvnRepositoryData.SymbolicRule> getBranches()
	{
		return getSymbolicRule(this.branches);
	}
	public Collection<SvnRepositoryData.SymbolicRule> getTags()
	{
		return getSymbolicRule(this.tags);
	}

	public void setTrunks(Collection<SvnRepositoryData.SymbolicRule> symbolicRuleCollection)
	{
		this.trunks = new ArrayList<SymbolicRuleRestData>();
		setSymbolicRule(symbolicRuleCollection, this.trunks);
	}
	public void setBranches(Collection<SvnRepositoryData.SymbolicRule> symbolicRuleCollection)
	{
		this.branches = new ArrayList<SymbolicRuleRestData>();
		setSymbolicRule(symbolicRuleCollection, this.branches);
	}
	public void setTags(Collection<SvnRepositoryData.SymbolicRule> symbolicRuleCollection)
	{
		this.tags = new ArrayList<SymbolicRuleRestData>();
		setSymbolicRule(symbolicRuleCollection, this.tags);
	}

	private Collection<SvnRepositoryData.SymbolicRule> getSymbolicRule(List<SymbolicRuleRestData> symbolicRuleRestDataList) {
		Collection<SvnRepositoryData.SymbolicRule> symbolicRules = new ArrayList<SvnRepositoryData.SymbolicRule>();
		for (SymbolicRuleRestData symbolicRuleRestData : symbolicRuleRestDataList)
		{
			SvnRepositoryData.SymbolicRule symbolicRule = new SvnRepositoryData.SymbolicRule(symbolicRuleRestData.regex, symbolicRuleRestData.name, symbolicRuleRestData.logicalPathPrefix);
			symbolicRules.add(symbolicRule);
		}
		return symbolicRules;
	}
	private void setSymbolicRule(Collection<SvnRepositoryData.SymbolicRule> symbolicRuleCollection, List<SymbolicRuleRestData> symbolicRuleRestDataList) {
		for (SvnRepositoryData.SymbolicRule symbolicRule : symbolicRuleCollection) {
			SymbolicRuleRestData symbolicRuleRestData = new SymbolicRuleRestData();
			symbolicRuleRestData.logicalPathPrefix = symbolicRule.getLogicalPathPrefix();
			symbolicRuleRestData.name = symbolicRule.getName();
			symbolicRuleRestData.regex = symbolicRule.getRegex();
			symbolicRuleRestDataList.add(symbolicRuleRestData);
		}
	}
}
