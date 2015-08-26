package com.davidkoudela.crucible.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * Description: Model class containing REST data of FE's Cvs repository parameter.
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 21.8.2015
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown=true)
public class CvsRepositoryRestData {
	public String directory;
	public CharsetRestData charset;

	public void verify() throws Exception {
		if (null == directory)
			throw new Exception("Missing cvs.directory");
		if (null != charset) {
			charset.verify();
		}
	}
}
