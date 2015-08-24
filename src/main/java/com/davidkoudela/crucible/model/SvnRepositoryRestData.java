package com.davidkoudela.crucible.model;

import com.atlassian.fisheye.spi.admin.data.ImportMode;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
	public String charset;
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
		Set<String> availableCharset = getAvailableCharset();
		if (null != charset && false == availableCharset.contains(charset)) {
			throw new Exception("svn.charset is not supported. Supported types are: " + availableCharset.toString());
		}
	}

	public Set<String> getAvailableCharset() {
		return Charset.availableCharsets().keySet();
	}

	public Charset getCharset() {
		return Charset.forName(charset);
	}

	public Collection<SvnRepositoryData.SymbolicRule> getTrunks()
	{
		return getSymbolicRule(trunks);
	}
	public Collection<SvnRepositoryData.SymbolicRule> getBranches()
	{
		return getSymbolicRule(branches);
	}
	public Collection<SvnRepositoryData.SymbolicRule> getTags()
	{
		return getSymbolicRule(tags);
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

}
