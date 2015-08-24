package com.davidkoudela.crucible.model;

/**
 * Description: Model class containing REST data of FE's AdvancedLinker parameter.
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 24.8.2015
 */
public class AdvancedLinkerRestData {
	public String description;
	public String syntaxDef;

	public void verify() throws Exception {
		if (null == description)
			throw new Exception("AdvancedLinkers.description is not specified");
		}
}
