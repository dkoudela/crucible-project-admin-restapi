package it.com.davidkoudela.crucible.tests;

import com.davidkoudela.crucible.model.RepositoryAdminModel;
import com.davidkoudela.crucible.rest.response.ResponseRepositoryData;
import com.davidkoudela.crucible.rest.response.ResponseRepositoryNameList;
import com.davidkoudela.crucible.stub.UserManagementStub;
import it.com.davidkoudela.crucible.framework.RepositoryRestDataService;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.atlassian.plugins.osgi.test.AtlassianPluginsTestRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(AtlassianPluginsTestRunner.class)
public class RepositoryCRUDRestApiWiredTest
{
	private final static String USER = "admin";
	private final static String PASSWORD = "admin";
	private final static String CVS_NAME = "corp-cvs";
	private final static String CVS_BASIC = "{\n" +
			"  \"cvs\" : { \"directory\" : \"c:\\\\\", \"charset\" : { \"charsetName\" : \"ISO-8859-1\" } },\n" +
			"  \"name\" : \"corp-cvs\",\n" +
			"  \"description\" : \"corp-cvs description\",\n" +
			"  \"storeDiff\" : false,\n" +
			"  \"enabled\" : false,\n" +
			"  \"started\" : false\n" +
			"}";
	private final static String CVS_BASIC_RESULT = "{" +
			"\"name\":\"corp-cvs\"," +
			"\"description\":\"corp-cvs description\"," +
			"\"storeDiff\":false," +
			"\"extraOptions\":{" +
				"\"usingDefaultsPermissions\":true," +
				"\"allowAnon\":false," +
				"\"allowLoggedUsers\":false," +
				"\"allowIncludes\":[]," +
				"\"allowExcludes\":[]," +
				"\"simpleLinkers\":[]," +
				"\"advancedLinkers\":[]," +
				"\"hiddenDirectories\":[]," +
				"\"requiredGroups\":[]," +
				"\"checkoutURL\":{}" +
			"}," +
			"\"cvs\":{\"directory\":\"c:\\\\\",\"charset\":{\"charsetName\":\"ISO-8859-1\"}}" +
			"}";
	private final RepositoryAdminModel repositoryAdminModel;
	private final RepositoryRestDataService repositoryRestDataService;
	private final UserManagementStub userManagementStub;

	public RepositoryCRUDRestApiWiredTest(RepositoryAdminModel repositoryAdminModel, RepositoryRestDataService repositoryRestDataService,
										  UserManagementStub userManagementStub)
	{
		this.repositoryAdminModel = repositoryAdminModel;
		this.repositoryRestDataService = repositoryRestDataService;
		this.userManagementStub = userManagementStub;
	}

	@BeforeClass
	public void setClass() {
		this.userManagementStub.createAdminUserSession();
	}

	@Before
	public void verifyCleanEnvironment() {
		ResponseRepositoryNameList responseRepositoryNameList = this.repositoryAdminModel.listRepository();
		assertEquals("Repository name list does not match", "[django-piston, checkstyle, csvparser]", responseRepositoryNameList.getRepositoryNames().toString());
		Map<String, String> response = responseRepositoryNameList.getResponse();
		assertEquals("200", response.get("code"));
		assertEquals("operation succeeded", response.get("message"));
	}

	@After
	public void cleanup()
	{
		this.repositoryAdminModel.deleteRepository(this.repositoryRestDataService.createRepositoryData(CVS_BASIC));
		verifyCleanEnvironment();
	}

	@Test
	public void testCreateDeleteOneCvsBasicRepository()
	{
		this.repositoryAdminModel.newRepository(this.repositoryRestDataService.createRepositoryData(CVS_BASIC));
		ResponseRepositoryData responseRepositoryData = this.repositoryAdminModel.listRepository(CVS_NAME);
		Map<String, String> result = this.repositoryRestDataService.compareRepositoryData(CVS_BASIC_RESULT, responseRepositoryData.getRepositoryRestData());
		assertEquals("Comparison of expected and actual RepositoryRestData", result.get("expected"), result.get("actual"));
	}
}