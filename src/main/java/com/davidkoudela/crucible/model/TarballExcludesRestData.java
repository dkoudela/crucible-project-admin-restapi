package com.davidkoudela.crucible.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Description: Model class containing REST data of FE's Tarball exclude parameters.
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 16.8.2015
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown=true)
public class TarballExcludesRestData {
	public String baseDirectory;
	public Boolean excludeSubdirs;

	public void verify() throws Exception
	{
		if (null == baseDirectory)
			throw new Exception("Missing TarballExcludes.baseDirectory");
		if (null == excludeSubdirs)
			throw new Exception("Missing TarballExcludes.excludeSubdirs");
	}
}
