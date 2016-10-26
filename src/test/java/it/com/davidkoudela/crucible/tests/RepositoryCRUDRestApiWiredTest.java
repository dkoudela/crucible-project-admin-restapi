package it.com.davidkoudela.crucible.tests;

import com.davidkoudela.crucible.model.RepositoryAdminModel;
import com.davidkoudela.crucible.rest.response.ResponseRepositoryData;
import com.davidkoudela.crucible.rest.response.ResponseRepositoryNameList;
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

	private void executeCRD(String name, String request, String response) {
		try {
			this.repositoryAdminModel.newRepository(this.repositoryRestDataService.createRepositoryData(request));
			ResponseRepositoryData responseRepositoryData = this.repositoryAdminModel.listRepository(name);
			Map<String, String> result = this.repositoryRestDataService.compareRepositoryData(response, responseRepositoryData.getRepositoryRestData());
			assertEquals("Comparison of expected and actual RepositoryRestData", result.get("expected"), result.get("actual"));
			Map<String, String> listRepositoryResponse = responseRepositoryData.getResponse();
			assertEquals("200", listRepositoryResponse.get("code"));
			assertEquals("operation succeeded", listRepositoryResponse.get("message"));
		} finally {
			this.repositoryAdminModel.deleteRepository(this.repositoryRestDataService.createRepositoryData(request));
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
		assertEquals("Repository name list does not match", "[django-piston, checkstyle, csvparser]", responseRepositoryNameList.getRepositoryNames().toString());
		Map<String, String> response = responseRepositoryNameList.getResponse();
		assertEquals("200", response.get("code"));
		assertEquals("operation succeeded", response.get("message"));
	}

	@After
	public void cleanup()
	{
		verifyCleanEnvironment();
	}

	@Test
	public void testCreateDeleteOneCvsBasicRepository()
	{
		executeCRD(CVS_NAME, CVS_BASIC_REQUEST, CVS_BASIC_RESPONSE);
	}

	@Test
	public void testCreateDeleteOneCvsExtraRepository()
	{
		executeCRD(CVS_NAME, CVS_EXTRA_REQUEST, CVS_EXTRA_RESPONSE);
	}

	@Test
	public void testCreateDeleteOneGitBasicRepository()
	{
		executeCRD(GIT_NAME, GIT_BASIC_REQUEST, GIT_BASIC_RESPONSE);
	}

	@Test
	public void testCreateDeleteOneGitPasswordRepository()
	{
		executeCRD(GIT_NAME, GIT_PASSWORD_REQUEST, GIT_PASSWORD_RESPONSE);
	}

	@Test
	public void testCreateDeleteOneGitKeypairRepository()
	{
		executeCRD(GIT_NAME, GIT_KEYPAIR_REQUEST, GIT_KEYPAIR_RESPONSE);
	}

	@Test
	public void testCreateDeleteOneMercurialBasicRepository()
	{
		executeCRD(MERCURIAL_NAME, MERCURIAL_BASIC_REQUEST, MERCURIAL_BASIC_RESPONSE);
	}

	@Test
	public void testCreateDeleteOneMercurialPasswordRepository()
	{
		executeCRD(MERCURIAL_NAME, MERCURIAL_PASSWORD_REQUEST, MERCURIAL_PASSWORD_RESPONSE);
	}

	@Test
	public void testCreateDeleteOneMercurialKeypairRepository()
	{
		executeCRD(MERCURIAL_NAME, MERCURIAL_KEYPAIR_REQUEST, MERCURIAL_KEYPAIR_RESPONSE);
	}

	@Test
	public void testCreateDeleteOneP4BasicRepository()
	{
		executeCRD(P4_NAME, P4_BASIC_REQUEST, P4_BASIC_RESPONSE);
	}

	@Test
	public void testCreateDeleteOneSvnBasicRepository()
	{
		executeCRD(SVN_NAME, SVN_BASIC_REQUEST, SVN_BASIC_RESPONSE);
	}

	@Test
	public void testCreateDeleteOneSvnExtraRepository()
	{
		executeCRD(SVN_NAME, SVN_EXTRA_REQUEST, SVN_EXTRA_RESPONSE);
	}

}