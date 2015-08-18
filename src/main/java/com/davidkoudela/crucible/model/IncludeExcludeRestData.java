package com.davidkoudela.crucible.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Description: Model class containing REST data of FE's Include or Exclude parameter.
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 18.8.2015
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown=true)
public class IncludeExcludeRestData {
	String path;
	Boolean caseSensitive;

	public void verify() throws Exception
	{
		if (null == path)
			throw new Exception("IncludeExclude.path is not specified");
		if (null == caseSensitive)
			throw new Exception("IncludeExclude.caseSensitive is not specified");
	}
}
