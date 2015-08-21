package com.davidkoudela.crucible.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Description: Model class containing REST data of FE's Update option parameters.
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 18.8.2015
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateRestData {
	public Boolean disablePolling;
	public String pollingInterval;
	public CvsUpdateRestData cvs;

	public void verify() throws Exception {
		if (null != disablePolling){
			if (true == disablePolling)
				pollingInterval = "never";
			else if (null == pollingInterval)
				throw new Exception("UpdateOptions.pollingInterval is not specified");
		} else if (null == pollingInterval)
			throw new Exception("UpdateOptions.pollingInterval is not specified");
		if (null != cvs)
			cvs.verify();
	}
}
