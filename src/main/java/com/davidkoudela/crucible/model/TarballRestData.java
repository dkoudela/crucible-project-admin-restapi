package com.davidkoudela.crucible.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Description: Model class containing REST data of FE's Tarball parameters.
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 16.8.2015
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown=true)
public class TarballRestData {
	public Long maxFiles;
	public Boolean enabled;
	public List<TarballExcludesRestData> excludes;

	public void verify() throws Exception {
		if (null != excludes)
		{
			for (TarballExcludesRestData exclude : excludes)
			{
				exclude.verify();
			}
		}
	}
}
