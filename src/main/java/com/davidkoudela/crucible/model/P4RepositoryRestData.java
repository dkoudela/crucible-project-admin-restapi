package com.davidkoudela.crucible.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.nio.charset.Charset;

/**
 * Description: Model class containing REST data of FE's Perforce repository parameter.
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 16.8.2015
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown=true)
public class P4RepositoryRestData {
	public String server;
	public String path;
	public String username;
	public String password;
	public Integer blockSize;
	public Boolean caseSensitive;
	public Boolean disableMutli;
	public Charset charset;
	public String commandTimeout;
	public Float connectionsPerSecond;
	public Integer fileLogLimit;
	public Boolean initialImport;
	public Integer port;
	public Boolean skipLabels;
	public Long startRevision;
	public Boolean unicode;

	public void verify() throws Exception
	{
		if (null == server)
			throw new Exception("Missing p4.server");
		if (null == path)
			throw new Exception("Missing p4.path");
	}
}
