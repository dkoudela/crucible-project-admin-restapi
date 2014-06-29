package com.davidkoudela.crucible.model;

/**
 * Description: Utility class providing conversion of string values to other data types
 * Copyright (C) 2014 David Koudela
 *
 * @author dkoudela
 * @since 4.6.2014
 */
public class StringConverter
{
	/**
	 * Converts string containing "true" / "false" value to boolean
	 *
	 * @param string containing "true" / "false" value
	 * @return true if the string contains "true" (case ignored) otherwise false
	 */
	public static boolean string2bool(String string)
	{
		return string.equalsIgnoreCase("true");
	}

	/**
	 * Converts string containing numeric value to integer
	 *
	 * @param string containing numeric value
	 * @return integer if the string represents numeric value otherwise null
	 */
	public static Integer string2Integer(String string)
	{
		try
		{
			return Integer.valueOf(string);
		}
		catch (Exception e)
		{
			return null;
		}
	}
}
