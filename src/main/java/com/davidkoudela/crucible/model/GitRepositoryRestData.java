package com.davidkoudela.crucible.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Description: Model class containing REST data of FE's Git repository parameter.
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 13.8.2015
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown=true)
public class GitRepositoryRestData implements Serializable
{
	public static final String NONE = "NONE";
	public static final String COPIES = "COPIES";
	public static final String MOVES = "MOVES";
	public static final String DETAILED = "DETAILED";

	public String location;
	public String path;
	public KeyAuthenticationRestData auth;
	public Integer blockSize;
	public String commandTimeout;
	public String renameDetection;

	public void verify() throws Exception{
		if (null == location)
			throw new Exception("Missing git.location");
	}
	public Integer getRenameDetectionInteger()
	{
		if (null != this.renameDetection) {
			if (0 == this.renameDetection.compareToIgnoreCase(NONE))
				return 1;
			if (0 == this.renameDetection.compareToIgnoreCase(COPIES))
				return 2;
			if (0 == this.renameDetection.compareToIgnoreCase(MOVES))
				return 3;
			if (0 == this.renameDetection.compareToIgnoreCase(DETAILED))
				return 4;
		}
		return 1;
	}
	public void setRenameDetectionInteger(Integer integer)
	{
		switch (integer) {
			case 1: this.renameDetection = NONE; break;
			case 2: this.renameDetection = COPIES; break;
			case 3: this.renameDetection = MOVES; break;
			case 4: this.renameDetection = DETAILED; break;
			default: this.renameDetection = NONE;
		}
	}
}
