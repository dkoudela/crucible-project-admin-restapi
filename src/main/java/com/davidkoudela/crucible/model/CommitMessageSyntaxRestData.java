package com.davidkoudela.crucible.model;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonValue;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Description: Model class containing REST data of FE's CommitMessageSyntax parameter.
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 16.8.2015
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown=true)
public class CommitMessageSyntaxRestData {
	public SyntaxType syntaxType;
	public java.util.Date wikiSyntaxStartDate;

	public void verify() throws Exception
	{
		if (null == syntaxType)
			throw new Exception("CommitMessageSyntax.syntaxType is not specified");
		if (syntaxType == SyntaxType.WIKI && null == wikiSyntaxStartDate)
			throw new Exception("CommitMessageSyntax.syntaxType is WIKI and CommitMessageSyntax.wikiSyntaxStartDate is not specified");
	}

	public static enum SyntaxType {
		PLAIN("PLAIN"),
		WIKI("WIKI");

		private String value;

		SyntaxType(String value)
		{
			this.value = value;
		}

		@JsonValue
		public String getValue() { return this.value; }

		@JsonCreator
		public static SyntaxType create(String val) {
			SyntaxType[] syntaxTypes = SyntaxType.values();
			for (SyntaxType syntaxType : syntaxTypes) {
				if (syntaxType.getValue().equals(val)) {
					return syntaxType;
				}
			}
			return PLAIN;
		}
	}
}
