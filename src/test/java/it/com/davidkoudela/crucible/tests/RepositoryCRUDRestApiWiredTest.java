package it.com.davidkoudela.crucible.tests;

import com.davidkoudela.crucible.model.RepositoryAdminModel;
import com.davidkoudela.crucible.rest.response.ResponseRepositoryNameList;
import it.com.davidkoudela.crucible.framework.RepositoryDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.atlassian.plugins.osgi.test.AtlassianPluginsTestRunner;
import com.atlassian.sal.api.ApplicationProperties;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(AtlassianPluginsTestRunner.class)
public class RepositoryCRUDRestApiWiredTest
{
	private final ApplicationProperties applicationProperties;
	private final RepositoryAdminModel repositoryAdminModel;
	private final RepositoryDataService repositoryDataService;

	public RepositoryCRUDRestApiWiredTest(ApplicationProperties applicationProperties, RepositoryAdminModel repositoryAdminModel, RepositoryDataService repositoryDataService)
	{
		this.applicationProperties = applicationProperties;
		this.repositoryAdminModel = repositoryAdminModel;
		this.repositoryDataService = repositoryDataService;
	}

	private void verifyClearEnvironment() {
		ResponseRepositoryNameList responseRepositoryNameList = this.repositoryAdminModel.listRepository();
		assertEquals(true, responseRepositoryNameList.getRepositoryNames().isEmpty());
		Map<String, String> response = responseRepositoryNameList.getResponse();
		assertEquals("200", response.get("code"));
		assertEquals("operation succeeded", response.get("message"));
	}

	@Test
	public void testCreateDeleteOneRepository()
	{
		verifyClearEnvironment();
		//this.repositoryAdminModel.newRepository();

		//this.repositoryAdminModel.deleteRepository();
		verifyClearEnvironment();
	}
}