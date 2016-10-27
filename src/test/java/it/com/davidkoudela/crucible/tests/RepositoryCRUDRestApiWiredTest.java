package it.com.davidkoudela.crucible.tests;

import com.davidkoudela.crucible.model.RepositoryAdminModel;
import com.davidkoudela.crucible.rest.response.ResponseRepositoryData;
import com.davidkoudela.crucible.rest.response.ResponseRepositoryNameList;
import com.davidkoudela.crucible.rest.response.ResponseRepositoryOperation;
import com.davidkoudela.crucible.stub.UserManagementStub;
import it.com.davidkoudela.crucible.framework.RepositoryRestDataService;
import org.junit.*;
import org.junit.runner.RunWith;
import com.atlassian.plugins.osgi.test.AtlassianPluginsTestRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Description: CRUD (Create Read Update Delete) Integration Wired tests
 *              for {@link RepositoryAdminModel}
 * Copyright (C) 2016 David Koudela
 *
 * @author dkoudela
 * @since 26.10.2016
 */
@RunWith(AtlassianPluginsTestRunner.class)
public class RepositoryCRUDRestApiWiredTest extends RepositoryCRUDRestApiWiredAbstract
{
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

	private void checkRepositoryAdminModelResponseStatus(Map<String, String> response) {
		assertEquals("operation succeeded", response.get("message"));
		assertEquals("200", response.get("code"));
	}

	private void checkRepositoryAdminModelResponseData(String repositoryName, String response) {
		ResponseRepositoryData responseRepositoryData = this.repositoryAdminModel.listRepository(repositoryName);
		Map<String, String> result = this.repositoryRestDataService.compareRepositoryData(response, responseRepositoryData.getRepositoryRestData());
		assertEquals("Comparison of expected and actual RepositoryRestData", result.get("expected"), result.get("actual"));
		checkRepositoryAdminModelResponseStatus(responseRepositoryData.getResponse());
	}

	private void executeCRUD(String repositoryName, String createRequest, String createResponse, String updateRequest, String updateResponse) {
		try {
			ResponseRepositoryOperation responseRepositoryOperation = this.repositoryAdminModel.newRepository(this.repositoryRestDataService.createRepositoryData(createRequest));
			checkRepositoryAdminModelResponseStatus(responseRepositoryOperation.getResponse());
			checkRepositoryAdminModelResponseData(repositoryName, createResponse);

			if (null != updateRequest && null != updateResponse) {
				responseRepositoryOperation = this.repositoryAdminModel.updateRepository(this.repositoryRestDataService.createRepositoryData(updateRequest));
				checkRepositoryAdminModelResponseStatus(responseRepositoryOperation.getResponse());
				checkRepositoryAdminModelResponseData(repositoryName, updateResponse);
			}
		} finally {
			this.repositoryAdminModel.deleteRepository(this.repositoryRestDataService.createRepositoryData(createRequest));
		}
	}

	@BeforeClass
	public void setClass() {
		this.userManagementStub.createAdminUserSession();
		this.userManagementStub.createGroup("testGroup01");
		this.userManagementStub.createGroup("testGroup02");
	}

	@Before
	public void verifyCleanEnvironment() {
		ResponseRepositoryNameList responseRepositoryNameList = this.repositoryAdminModel.listRepository();
		Map<String, String> response = responseRepositoryNameList.getResponse();
		assertEquals("operation succeeded", response.get("message"));
		assertEquals("200", response.get("code"));
		assertEquals("Repository name list does not match", "[django-piston, checkstyle, csvparser]", responseRepositoryNameList.getRepositoryNames().toString());
	}

	@After
	public void cleanup()
	{
		verifyCleanEnvironment();
	}


	/**
	 * Tests
	 */
	@Test
	public void testCreateDeleteOneCvsBasicRepository()
	{
		executeCRUD(CVS_NAME, CVS_BASIC_REQUEST, CVS_BASIC_RESPONSE, CVS_BASIC_UPDATE_REQUEST, CVS_BASIC_UPDATE_RESPONSE);
	}

	@Test
	public void testCreateDeleteOneCvsExtraRepository()
	{
		executeCRUD(CVS_NAME, CVS_EXTRA_REQUEST, CVS_EXTRA_RESPONSE, CVS_EXTRA_UPDATE_REQUEST, CVS_EXTRA_UPDATE_RESPONSE);
	}

	@Test
	public void testCreateDeleteOneGitBasicRepository()
	{
		executeCRUD(GIT_NAME, GIT_BASIC_REQUEST, GIT_BASIC_RESPONSE, GIT_BASIC_UPDATE_REQUEST, GIT_BASIC_UPDATE_RESPONSE);
	}

	@Test
	public void testCreateDeleteOneGitPasswordRepository()
	{
		executeCRUD(GIT_NAME, GIT_PASSWORD_REQUEST, GIT_PASSWORD_RESPONSE, null, null);
	}

	@Test
	public void testCreateDeleteOneGitKeypairRepository()
	{
		executeCRUD(GIT_NAME, GIT_KEYPAIR_REQUEST, GIT_KEYPAIR_RESPONSE, null, null);
	}

	@Test
	public void testCreateDeleteOneMercurialBasicRepository()
	{
		executeCRUD(MERCURIAL_NAME, MERCURIAL_BASIC_REQUEST, MERCURIAL_BASIC_RESPONSE, MERCURIAL_BASIC_UPDATE_REQUEST, MERCURIAL_BASIC_UPDATE_RESPONSE);
	}

	@Test
	public void testCreateDeleteOneMercurialPasswordRepository()
	{
		executeCRUD(MERCURIAL_NAME, MERCURIAL_PASSWORD_REQUEST, MERCURIAL_PASSWORD_RESPONSE, null, null);
	}

	@Test
	public void testCreateDeleteOneMercurialKeypairRepository()
	{
		executeCRUD(MERCURIAL_NAME, MERCURIAL_KEYPAIR_REQUEST, MERCURIAL_KEYPAIR_RESPONSE, null, null);
	}

	@Test
	public void testCreateDeleteOneP4BasicRepository()
	{
		executeCRUD(P4_NAME, P4_BASIC_REQUEST, P4_BASIC_RESPONSE, P4_BASIC_UPDATE_REQUEST, P4_BASIC_UPDATE_RESPONSE);
	}

	@Test
	public void testCreateDeleteOneSvnBasicRepository()
	{
		executeCRUD(SVN_NAME, SVN_BASIC_REQUEST, SVN_BASIC_RESPONSE, SVN_BASIC_UPDATE_REQUEST, SVN_BASIC_UPDATE_RESPONSE);
	}

	@Test
	public void testCreateDeleteOneSvnExtraRepository()
	{
		executeCRUD(SVN_NAME, SVN_EXTRA_REQUEST, SVN_EXTRA_RESPONSE, SVN_EXTRA_UPDATE_REQUEST, SVN_EXTRA_UPDATE_RESPONSE);
	}

}