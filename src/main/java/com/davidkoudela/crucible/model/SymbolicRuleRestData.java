package com.davidkoudela.crucible.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Description: Model class containing REST data of FE's SymbolicRule parameter.
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 24.8.2015
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SymbolicRuleRestData {
	public String regex;
	public String name;
	public String logicalPathPrefix;
}
