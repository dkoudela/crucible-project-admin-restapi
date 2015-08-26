package com.davidkoudela.crucible.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * Description: Model class containing REST data of FE's Charset parameter.
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 26.8.2015
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown=true)
public class CharsetRestData {
	public String charsetName;

	public void verify() throws Exception {
		Set<String> availableCharset = getAvailableCharset();
		if (null != charsetName && false == availableCharset.contains(charsetName)) {
			throw new Exception("Charset.charsetName is not supported. Supported types are: " + availableCharset.toString());
		}
	}

	public Set<String> getAvailableCharset() {
		return Charset.availableCharsets().keySet();
	}

	public Charset getCharset() {
		return Charset.forName(charsetName);
	}
}
