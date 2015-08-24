package com.davidkoudela.crucible.model;

/**
 * Description: Model class containing REST data of FE's Hg repository parameter.
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 24.8.2015
 */
public class HgRepositoryRestData {
	public String location;
	public java.lang.String path;
	public KeyAuthenticationRestData auth;
	public java.lang.String commandTimeout;
	public java.lang.Integer blockSize;

	public void verify() throws Exception{
		if (null == location)
			throw new Exception("Missing hg.location");
	}
}
