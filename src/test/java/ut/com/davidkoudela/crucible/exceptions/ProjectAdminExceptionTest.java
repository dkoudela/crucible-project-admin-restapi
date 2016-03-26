package ut.com.davidkoudela.crucible.exceptions;

import com.davidkoudela.crucible.exceptions.BadRequestException;
import com.davidkoudela.crucible.exceptions.InternalErrorException;
import com.davidkoudela.crucible.exceptions.ProjectAdminException;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description: Testing {@link ProjectAdminException}
 * Copyright (C) 2014 David Koudela
 *
 * @author dkoudela
 * @since 4.6.2014
 */
public class ProjectAdminExceptionTest extends TestCase
{
	@Test
	public void testBadRequestException()
	{
		String errorMessage = "My Error Message";
		String errorDetail = "Throw cause";
		ProjectAdminException projectAdminException = new BadRequestException(errorMessage, new Throwable(errorDetail, null));
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("code", "400");
		map.put("message", errorMessage);
		map.put("cause", errorDetail);

		assertEquals(map.toString(), projectAdminException.getResponse().getResponse().toString());
	}

	@Test
	public void testInternalErrorException()
	{
		String errorMessage = "My Error Message";
		String errorDetail = "Throw cause";
		ProjectAdminException projectAdminException = new InternalErrorException(errorMessage, new Throwable(errorDetail, null));
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("code", "500");
		map.put("message", errorMessage);
		map.put("cause", errorDetail);

		assertEquals(map.toString(), projectAdminException.getResponse().getResponse().toString());
	}
}
