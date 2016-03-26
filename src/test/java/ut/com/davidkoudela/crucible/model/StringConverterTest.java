package ut.com.davidkoudela.crucible.model;

import com.davidkoudela.crucible.model.StringConverter;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Description: Testing {@link StringConverter}
 * Copyright (C) 2014 David Koudela
 *
 * @author dkoudela
 * @since 4.6.2014
 */
public class StringConverterTest extends TestCase
{
	@Test
	public void testString2boolTrue()
	{
		assertEquals(StringConverter.string2bool("true"), true);
		assertEquals(StringConverter.string2bool("True"), true);
		assertEquals(StringConverter.string2bool("truE"), true);
		assertEquals(StringConverter.string2bool("tRue"), true);
	}

	@Test
	public void testString2boolFalse()
	{
		assertEquals(StringConverter.string2bool("false"), false);
		assertEquals(StringConverter.string2bool("False"), false);
		assertEquals(StringConverter.string2bool("falsE"), false);
		assertEquals(StringConverter.string2bool("fAlse"), false);
		assertEquals(StringConverter.string2bool("anyth"), false);
	}

	@Test
	public void testString2IntegerValidInt()
	{
		assertEquals(StringConverter.string2Integer("1234"), new Integer(1234));
		assertEquals(StringConverter.string2Integer("4321"), new Integer(4321));
	}

	@Test
	public void testString2IntegerInvalidInt()
	{
		assertEquals(StringConverter.string2Integer(""), null);
		assertEquals(StringConverter.string2Integer("1234.0"), null);
		assertEquals(StringConverter.string2Integer("this is not int"), null);
	}
}
