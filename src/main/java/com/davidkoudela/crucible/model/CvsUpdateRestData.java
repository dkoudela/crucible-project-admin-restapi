package com.davidkoudela.crucible.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Description: Model class containing REST data of FE's CVS Update option parameters.
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 20.8.2015
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown=true)
public class CvsUpdateRestData {
	public String fullScanInterval;
	public String historyFile;
	public String stripPrefix;

	public void verify() throws Exception
	{
		if (null == fullScanInterval)
			throw new Exception("CvsUpdateOptions.fullScanInterval is not specified");
		if (null == historyFile)
			throw new Exception("CvsUpdateOptions.historyFile is not specified");
		if (null == stripPrefix)
			throw new Exception("CvsUpdateOptions.stripPrefix is not specified");
	}
}
