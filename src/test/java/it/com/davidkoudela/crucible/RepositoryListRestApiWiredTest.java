package it.com.davidkoudela.crucible;

import com.davidkoudela.crucible.model.RepositoryAdminModel;
import com.davidkoudela.crucible.rest.response.ResponseRepositoryNameList;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.atlassian.plugins.osgi.test.AtlassianPluginsTestRunner;
import com.atlassian.sal.api.ApplicationProperties;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(AtlassianPluginsTestRunner.class)
public class RepositoryListRestApiWiredTest
{
	private final ApplicationProperties applicationProperties;
	private final RepositoryAdminModel repositoryAdminModel;

	public RepositoryListRestApiWiredTest(ApplicationProperties applicationProperties, RepositoryAdminModel repositoryAdminModel)
	{
		this.applicationProperties = applicationProperties;
		this.repositoryAdminModel = repositoryAdminModel;
	}

	@Test
	public void testListEmptyRepository()
	{
		ResponseRepositoryNameList responseRepositoryNameList = repositoryAdminModel.listRepository();
		assertEquals(true, responseRepositoryNameList.getRepositoryNames().isEmpty());
		Map<String, String> response = responseRepositoryNameList.getResponse();
		assertEquals("200", response.get("code"));
		assertEquals("operation succeeded", response.get("message"));
	}
}