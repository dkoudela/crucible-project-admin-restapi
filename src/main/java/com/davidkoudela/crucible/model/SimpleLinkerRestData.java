package com.davidkoudela.crucible.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.regex.Pattern;

/**
 * Description: Model class containing REST data of FE's SimpleLinker parameter.
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 21.8.2015
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SimpleLinkerRestData {
	public String description;
	public String href;
	public String regex;

	public void verify() throws Exception {
		if (null == description)
			throw new Exception("SimpleLinkers.description is not specified");
		if (null == href)
			throw new Exception("SimpleLinkers.href is not specified");
		if (null == regex)
			throw new Exception("SimpleLinkers.regex is not specified");
	}

	public Pattern getRegex() {
		return Pattern.compile(regex);
	}
}
