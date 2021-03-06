package ut.com.davidkoudela.crucible.model;

import com.atlassian.fecru.user.EffectiveUserProvider;
import com.atlassian.fecru.user.FecruUser;
import com.cenqua.crucible.model.PermissionScheme;
import com.cenqua.crucible.model.Project;
import com.cenqua.crucible.model.Review;
import com.cenqua.crucible.model.managers.LogItemManager;
import com.cenqua.crucible.model.managers.PermissionManager;
import com.cenqua.crucible.model.managers.ProjectManager;
import com.cenqua.crucible.model.managers.ReviewManager;
import com.cenqua.crucible.view.reviewfilters.ReviewFilterDef;
import com.cenqua.crucible.view.reviewfilters.ReviewFilters;
import com.cenqua.fisheye.RepositoryConfig;
import com.cenqua.fisheye.config.RepositoryManager;
import com.cenqua.fisheye.rep.RepositoryHandle;
import com.cenqua.fisheye.user.UserManager;
import com.davidkoudela.crucible.model.ProjectAdminModelImpl;
import com.davidkoudela.crucible.model.StringConverter;
import com.davidkoudela.crucible.rest.response.ResponseProjectDataList;
import com.davidkoudela.crucible.rest.response.ResponseProjectDataProperties;
import com.davidkoudela.crucible.rest.response.ResponseProjectOperation;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.*;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Description: Testing {@link ProjectAdminModelImpl}
 * Copyright (C) 2014 David Koudela
 *
 * @author dkoudela
 * @since 6/4/14
 */
@RunWith(MockitoJUnitRunner.class)
public class ProjectAdminModelImplTest extends TestCase
{
	private static PermissionManager permissionManager;
	private static ProjectManager projectManager;
	private static RepositoryManager repositoryManager;
	private static UserManager userManager;
	private static ReviewManager reviewManager;
	private static LogItemManager logItemManager;
	private static EffectiveUserProvider effectiveUserProvider;
	public static Logger loggerMockStatic;

	private static ProjectAdminModelImpl projectAdminModelImpl;

	private static final int    id   = 1;
	private static final String name = "MyOwnProject";
	private static final String key  = "MOP";
	private static final String repo = "myRepo";
	private static final String store= "true";
	private static final String scheme="agile";
	private static final String emoder="true";
	private static final String dmoder="admin";
	private static final String dtime ="10";
	private static final String object="myObject";
	private static final String path="/";

	private static Project project;
	private static ReviewFilterDef filterDef;
	private static Collection<Review> reviewCollection;
	private static FecruUser fecruUser;

	@Before
	public void init() throws Exception
	{

		permissionManager = Mockito.mock(PermissionManager.class);
		projectManager = Mockito.mock(ProjectManager.class);
		repositoryManager = Mockito.mock(RepositoryManager.class);
		userManager = Mockito.mock(UserManager.class);
		reviewManager = Mockito.mock(ReviewManager.class);
		logItemManager = Mockito.mock(LogItemManager.class);
		effectiveUserProvider = Mockito.mock(EffectiveUserProvider.class);

		projectAdminModelImpl = new ProjectAdminModelImpl(permissionManager, projectManager, repositoryManager, userManager, reviewManager, logItemManager, effectiveUserProvider);

		project = new Project();

		// Correct setting: these are not changed by newProject() method
		project.setId(id);
		project.setName(name);
		project.setProjKey(key);
		// Incorrect setting: these must be changed by newProject() method - part of the verification
		project.setStoreRevisions(false);
		project.setPermissionScheme(new PermissionScheme("Development"));
		project.setModeratorDisabled(StringConverter.string2bool(emoder));
		project.setDefaultModerator(new FecruUser(666, "dkoudela"));
		project.setDefaultDuration(1000);
		project.setDefaultObjectives("I object !!!");

		ReviewFilters reviewFilters = new ReviewFilters(project);
		filterDef = reviewFilters.getCustomFilterDef();
		filterDef.project = project;

		reviewCollection = new ArrayList<Review>();
		Review review = new Review();
		review.setId(1);
		review.setName(project.getProjKey() + "-1");
		review.setProject(project);
		review.setCreateDate(new Date(0L));
		reviewCollection.add(review);

		fecruUser = new FecruUser("admin");
	}

	@Test
	public void testNewProjectValidParams()
	{
		Mockito.when(projectManager.createProject(name, key)).thenReturn(project);
		Mockito.when(projectManager.getProjectById(id)).thenReturn(project);
		PermissionScheme permissionScheme = new PermissionScheme(scheme);
		Mockito.when(permissionManager.findPermissionSchemeByName(scheme)).thenReturn(permissionScheme);
		FecruUser user = new FecruUser(1, dmoder);
		Mockito.when(userManager.getUser(dmoder)).thenReturn(user);
		// FECRU4.1.1
		//RepositoryHandle repositoryHandle = new RepositoryHandle(repo, null, null, null);
		// FECRU4.2
		RepositoryConfig repositoryConfig = Mockito.mock(RepositoryConfig.class);
		Mockito.when(repositoryConfig.getName()).thenReturn(repo);
		RepositoryHandle repositoryHandle = new RepositoryHandle(repositoryConfig, null, null);
		Mockito.when(repositoryManager.getRepository(repo)).thenReturn(repositoryHandle);

		ResponseProjectOperation responseProjectOperation = projectAdminModelImpl.newProject(name,key,repo,store,scheme,emoder,dmoder,dtime,object);
		Project relatedProject = responseProjectOperation.getRelatedProject();
		Map mapActual = responseProjectOperation.getResponse();

		ArgumentCaptor<String> argumentCaptorRepo = ArgumentCaptor.forClass(String.class);
		Mockito.verify(projectManager).removeRepositoryMappings(argumentCaptorRepo.capture());
		assertEquals(repo, argumentCaptorRepo.getValue());

		ArgumentCaptor<String> argumentCaptorProjectKey = ArgumentCaptor.forClass(String.class);
		argumentCaptorRepo = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> argumentCaptorRoot = ArgumentCaptor.forClass(String.class);
		Mockito.verify(projectManager).addPathToProject(argumentCaptorProjectKey.capture(), argumentCaptorRepo.capture(), argumentCaptorRoot.capture());
		assertEquals(key, argumentCaptorProjectKey.getValue());
		assertEquals(repo, argumentCaptorRepo.getValue());
		assertEquals(path, argumentCaptorRoot.getValue());

		assertEquals(name, relatedProject.getName());
		assertEquals(key, relatedProject.getProjKey());
		assertEquals(repo, relatedProject.getDefaultRepositoryName());
		assertEquals(scheme, relatedProject.getPermissionScheme().getName());
		assertEquals(dmoder, relatedProject.getDefaultModerator().getUsername());
		assertEquals(dtime, relatedProject.getDefaultDuration().toString());
		assertEquals(object, relatedProject.getDefaultObjectives());

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("code", "200");
		map.put("message", "operation succeeded");
		map.put("cause", "");

		assertEquals(map.toString(), mapActual.toString());
	}

	@Test
	public void testNewProjectCreateProjectException()
	{
		Mockito.when(projectManager.createProject(name, key)).thenThrow(new RuntimeException("test exception", null));
		Map mapActual = projectAdminModelImpl.newProject(name,key,repo,store,scheme,emoder,dmoder,dtime,object).getResponse();

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("code", "400");
		map.put("message", "project creation failed");
		map.put("cause", "test exception");

		assertEquals(map.toString(), mapActual.toString());
	}

	@Test
	public void testNewProjectModifyProjectException()
	{
		Mockito.when(projectManager.createProject(name, key)).thenReturn(project);
		Mockito.when(projectManager.getProjectById(id)).thenReturn(project);
		PermissionScheme permissionScheme = new PermissionScheme(scheme);
		Mockito.when(permissionManager.findPermissionSchemeByName(scheme)).thenReturn(permissionScheme);
		FecruUser user = new FecruUser(1, dmoder);
		Mockito.when(userManager.getUser(dmoder)).thenReturn(user);
		Mockito.when(repositoryManager.getRepository(repo)).thenThrow(new RuntimeException("test exception"));

		Map mapActual = projectAdminModelImpl.newProject(name,key,repo,store,scheme,emoder,dmoder,dtime,object).getResponse();

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("code", "400");
		map.put("message", "project modification failed");
		map.put("cause", "test exception");

		assertEquals(map.toString(), mapActual.toString());
	}

	@Test
	public void testUpdateProjectValidParams()
	{
		Mockito.when(projectManager.getProjectByKey(key)).thenReturn(project);
		Mockito.when(projectManager.getProjectById(id)).thenReturn(project);
		PermissionScheme permissionScheme = new PermissionScheme(scheme);
		Mockito.when(permissionManager.findPermissionSchemeByName(scheme)).thenReturn(permissionScheme);
		FecruUser user = new FecruUser(1, dmoder);
		Mockito.when(userManager.getUser(dmoder)).thenReturn(user);
		// FECRU4.1.1
		//RepositoryHandle repositoryHandle = new RepositoryHandle(repo, null, null, null);
		// FECRU4.2
		RepositoryConfig repositoryConfig = Mockito.mock(RepositoryConfig.class);
		Mockito.when(repositoryConfig.getName()).thenReturn(repo);
		RepositoryHandle repositoryHandle = new RepositoryHandle(repositoryConfig, null, null);
		Mockito.when(repositoryManager.getRepository(repo)).thenReturn(repositoryHandle);

		ResponseProjectOperation responseProjectOperation = projectAdminModelImpl.updateProject(name,key,repo,store,scheme,emoder,dmoder,dtime,object);
		Project relatedProject = responseProjectOperation.getRelatedProject();
		Map mapActual = responseProjectOperation.getResponse();

		ArgumentCaptor<String> argumentCaptorRepo = ArgumentCaptor.forClass(String.class);
		Mockito.verify(projectManager).removeRepositoryMappings(argumentCaptorRepo.capture());
		assertEquals(repo, argumentCaptorRepo.getValue());

		ArgumentCaptor<String> argumentCaptorProjectKey = ArgumentCaptor.forClass(String.class);
		argumentCaptorRepo = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> argumentCaptorRoot = ArgumentCaptor.forClass(String.class);
		Mockito.verify(projectManager).addPathToProject(argumentCaptorProjectKey.capture(), argumentCaptorRepo.capture(), argumentCaptorRoot.capture());
		assertEquals(key, argumentCaptorProjectKey.getValue());
		assertEquals(repo, argumentCaptorRepo.getValue());
		assertEquals(path, argumentCaptorRoot.getValue());

		assertEquals(name, relatedProject.getName());
		assertEquals(key, relatedProject.getProjKey());
		assertEquals(repo, relatedProject.getDefaultRepositoryName());
		assertEquals(scheme, relatedProject.getPermissionScheme().getName());
		assertEquals(dmoder, relatedProject.getDefaultModerator().getUsername());
		assertEquals(dtime, relatedProject.getDefaultDuration().toString());
		assertEquals(object, relatedProject.getDefaultObjectives());

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("code", "200");
		map.put("message", "operation succeeded");
		map.put("cause", "");

		assertEquals(map.toString(), mapActual.toString());
	}

	@Test
	public void testUpdateProjectInvalidNameKey()
	{
		Mockito.when(projectManager.getProjectByKey(key)).thenReturn(project);

		Map mapActual = projectAdminModelImpl.updateProject("wrong"+name,key,repo,store,scheme,emoder,dmoder,dtime,object).getResponse();

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("code", "400");
		map.put("message", "project retrieval failed");
		map.put("cause", "project name and key do not match: expected name: MyOwnProject provided name: wrongMyOwnProject");

		assertEquals(map.toString(), mapActual.toString());
	}

	@Test
	public void testUpdateProjectGetProjectByKeyException()
	{
		Mockito.when(projectManager.getProjectByKey(key)).thenThrow(new RuntimeException("test exception"));

		Map mapActual = projectAdminModelImpl.updateProject("wrong"+name,key,repo,store,scheme,emoder,dmoder,dtime,object).getResponse();

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("code", "400");
		map.put("message", "project retrieval failed");
		map.put("cause", "test exception");

		assertEquals(map.toString(), mapActual.toString());
	}

	@Test
	public void testDeleteEmptyProject()
	{
		Mockito.when(projectManager.getProjectByKey(key)).thenReturn(project);
		Mockito.when(projectManager.deleteProject(project)).thenReturn(true);

		Map mapActual = projectAdminModelImpl.deleteProject(key, "false").getResponse();

		Mockito.verify(projectManager).deleteProject(project);
		Mockito.verify(projectManager, Mockito.never()).deleteAllReviews(project, effectiveUserProvider.getEffectiveUser(), reviewManager);

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("code", "200");
		map.put("message", "operation succeeded");
		map.put("cause", "");

		assertEquals(map.toString(), mapActual.toString());
	}

	@Test
	public void testDeleteNotEmptyProject()
	{
		Mockito.when(projectManager.getProjectByKey(key)).thenReturn(project);
		Mockito.when(effectiveUserProvider.getEffectiveUser()).thenReturn(fecruUser);
		Mockito.doNothing().when(projectManager).deleteAllReviews(project, fecruUser, reviewManager);
		Mockito.when(projectManager.deleteProject(project)).thenReturn(true);

		Map mapActual = projectAdminModelImpl.deleteProject(key, "true").getResponse();

		Mockito.verify(projectManager).deleteProject(project);
		Mockito.verify(projectManager).deleteAllReviews(project, effectiveUserProvider.getEffectiveUser(), reviewManager);

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("code", "200");
		map.put("message", "operation succeeded");
		map.put("cause", "");

		assertEquals(map.toString(), mapActual.toString());
	}

	@Test
	public void testDeleteProjectDeleteProjectException()
	{
		Mockito.when(projectManager.getProjectByKey(key)).thenThrow(new RuntimeException("test exception"));

		Map mapActual = projectAdminModelImpl.deleteProject(key, "false").getResponse();

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("code", "400");
		map.put("message", "project delete failed");
		map.put("cause", "test exception");

		assertEquals(map.toString(), mapActual.toString());
	}

	@Test
	public void testListProjectValidParams()
	{
		List projects = new ArrayList<Project>();
		projects.add(project);
		Mockito.when(projectManager.getAllProjects()).thenReturn(projects);

		ResponseProjectDataList responseProjectDataList = projectAdminModelImpl.listProject(false);

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("code", "200");
		map.put("message", "operation succeeded");
		map.put("cause", "");

		assertEquals(map.toString(), responseProjectDataList.getResponse().toString());
		assertEquals("[{\"name\":\"MyOwnProject\",\"key\":\"MOP\",\"defaultRepositoryName\":\"null\"," +
					 "\"storeRevisions\":\"false\",\"permissionSchemeId\":\"null\",\"moderatorEnabled\":\"false\"," +
					 "\"defaultModerator\":\"dkoudela\",\"defaultDuration\":\"1000\"," +
					 "\"defaultObjectives\":\"I object !!!\"," +
					 "\"numberOfReviews\":null" +
					 "} ]",
					 responseProjectDataList.getProjectList().toString());
	}

	@Test
	public void testListProjectTwoProjectsValidParams()
	{
		List projects = new ArrayList<Project>();
		projects.add(project);
		projects.add(project);
		Mockito.when(projectManager.getAllProjects()).thenReturn(projects);

		ResponseProjectDataList responseProjectDataList = projectAdminModelImpl.listProject(false);

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("code", "200");
		map.put("message", "operation succeeded");
		map.put("cause", "");

		assertEquals(map.toString(), responseProjectDataList.getResponse().toString());
		assertEquals("[{\"name\":\"MyOwnProject\",\"key\":\"MOP\",\"defaultRepositoryName\":\"null\"," +
					  "\"storeRevisions\":\"false\",\"permissionSchemeId\":\"null\",\"moderatorEnabled\":\"false\"," +
					  "\"defaultModerator\":\"dkoudela\",\"defaultDuration\":\"1000\"," +
					  "\"defaultObjectives\":\"I object !!!\"," +
					  "\"numberOfReviews\":null" +
					  "} , " +
					  "{\"name\":\"MyOwnProject\",\"key\":\"MOP\",\"defaultRepositoryName\":\"null\"," +
					  "\"storeRevisions\":\"false\",\"permissionSchemeId\":\"null\",\"moderatorEnabled\":\"false\"," +
					  "\"defaultModerator\":\"dkoudela\",\"defaultDuration\":\"1000\"," +
					  "\"defaultObjectives\":\"I object !!!\"," +
					  "\"numberOfReviews\":null" +
					  "} ]",
					 responseProjectDataList.getProjectList().toString());
	}

	@Test
	public void testListProjectValidDetailedParams() throws IOException {
		List projects = new ArrayList<Project>();
		projects.add(project);
		Mockito.when(projectManager.getProjectByKey(project.getProjKey())).thenReturn(project);
		Mockito.when(reviewManager.getMatchingReviews(filterDef, "")).thenReturn(reviewCollection);

		ResponseProjectDataProperties responseProjectDataProperties = projectAdminModelImpl.listProject(project.getProjKey());

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("code", "200");
		map.put("message", "operation succeeded");
		map.put("cause", "");

		ObjectMapper objectMapper = new ObjectMapper();
		assertEquals(map.toString(), responseProjectDataProperties.getResponse().toString());
		assertEquals("{\"name\":\"MyOwnProject\",\"key\":\"MOP\",\"defaultRepositoryName\":null," +
						"\"storeRevisions\":false,\"permissionSchemeId\":null,\"moderatorEnabled\":false," +
						"\"defaultModerator\":\"dkoudela\",\"defaultDuration\":1000," +
						"\"defaultObjectives\":\"I object !!!\"," +
						"\"lastUpdatedReview\":\"\",\"lastUpdatedReviewDate\":\"\",\"numberOfReviews\":0," +
						"\"reviewsInStateDraft\":0,\"reviewsInStateApproval\":0,\"reviewsInStateReview\":0," +
						"\"reviewsInStateSummarize\":0,\"reviewsInStateClosed\":0,\"reviewsInStateDead\":0," +
						"\"reviewsInStateRejected\":0,\"reviewsInStateUnknown\":0,\"reviewsInStateOpenSnippet\":0," +
						"\"reviewsInStateClosedSnippet\":0" +
						"}",
				objectMapper.writeValueAsString(responseProjectDataProperties.projectProperties()));
	}

	@Test
	public void testListProjectProjectException()
	{
		Mockito.when(projectManager.getAllProjects()).thenThrow(new RuntimeException("test exception"));

		Map mapActual = projectAdminModelImpl.listProject(false).getResponse();

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("code", "400");
		map.put("message", "project list failed");
		map.put("cause", "test exception");

		assertEquals(map.toString(), mapActual.toString());
	}
}
