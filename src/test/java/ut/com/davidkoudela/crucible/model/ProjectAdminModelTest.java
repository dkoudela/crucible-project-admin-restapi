package com.davidkoudela.crucible.model;

import com.atlassian.fecru.user.User;
import com.cenqua.crucible.model.PermissionScheme;
import com.cenqua.crucible.model.Project;
import com.cenqua.crucible.model.managers.PermissionManager;
import com.cenqua.crucible.model.managers.ProjectManager;
import com.cenqua.fisheye.config.RepositoryManager;
import com.cenqua.fisheye.rep.RepositoryHandle;
import com.cenqua.fisheye.user.UserManager;
import com.davidkoudela.crucible.exceptions.BadRequestException;
import com.davidkoudela.crucible.rest.response.ResponseFactory;
import com.davidkoudela.crucible.rest.response.ResponseProjectDataList;
import com.davidkoudela.crucible.rest.response.ResponseProjectOperation;
import junit.framework.TestCase;
import org.apache.log4j.spi.LoggerFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: Testing {@link ProjectAdminModel}
 * Copyright (C) 2014 David Koudela
 *
 * @author dkoudela
 * @since 6/4/14
 */
@RunWith(MockitoJUnitRunner.class)
public class ProjectAdminModelTest extends TestCase
{
	private static PermissionManager permissionManager;
	private static ProjectManager projectManager;
	private static RepositoryManager repositoryManager;
	private static UserManager userManager;
	public static Logger loggerMockStatic;

	private static ProjectAdminModel projectAdminModel;

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

	@Before
	public void init() throws Exception
	{

		permissionManager = Mockito.mock(PermissionManager.class);
		projectManager = Mockito.mock(ProjectManager.class);
		repositoryManager = Mockito.mock(RepositoryManager.class);
		userManager = Mockito.mock(UserManager.class);

		projectAdminModel = new ProjectAdminModel(permissionManager, projectManager, repositoryManager, userManager);

		project = new Project();

		// Correct setting: these are not changed by newProject() method
		project.setId(id);
		project.setName(name);
		project.setProjKey(key);
		// Incorrect setting: these must be changed by newProject() method - part of the verification
		project.setStoreRevisions(false);
		project.setPermissionScheme(new PermissionScheme("Development"));
		project.setModeratorDisabled(StringConverter.string2bool(emoder));
		project.setDefaultModerator(new User(666, "dkoudela"));
		project.setDefaultDuration(1000);
		project.setDefaultObjectives("I object !!!");
	}

	@Test
	public void testNewProjectValidParams()
	{
		Mockito.when(projectManager.createProject(name, key)).thenReturn(project);
		Mockito.when(projectManager.getProjectById(id)).thenReturn(project);
		PermissionScheme permissionScheme = new PermissionScheme(scheme);
		Mockito.when(permissionManager.findPermissionSchemeByName(scheme)).thenReturn(permissionScheme);
		User user = new User(1, dmoder);
		Mockito.when(userManager.getUser(dmoder)).thenReturn(user);
		RepositoryHandle repositoryHandle = new RepositoryHandle(repo, null, null, null);
		Mockito.when(repositoryManager.getRepository(repo)).thenReturn(repositoryHandle);

		ResponseProjectOperation responseProjectOperation = projectAdminModel.newProject(name,key,repo,store,scheme,emoder,dmoder,dtime,object);
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
		Map mapActual = projectAdminModel.newProject(name,key,repo,store,scheme,emoder,dmoder,dtime,object).getResponse();

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
		User user = new User(1, dmoder);
		Mockito.when(userManager.getUser(dmoder)).thenReturn(user);
		Mockito.when(repositoryManager.getRepository(repo)).thenThrow(new RuntimeException("test exception"));

		Map mapActual = projectAdminModel.newProject(name,key,repo,store,scheme,emoder,dmoder,dtime,object).getResponse();

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
		User user = new User(1, dmoder);
		Mockito.when(userManager.getUser(dmoder)).thenReturn(user);
		RepositoryHandle repositoryHandle = new RepositoryHandle(repo, null, null, null);
		Mockito.when(repositoryManager.getRepository(repo)).thenReturn(repositoryHandle);

		ResponseProjectOperation responseProjectOperation = projectAdminModel.updateProject(name,key,repo,store,scheme,emoder,dmoder,dtime,object);
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

		Map mapActual = projectAdminModel.updateProject("wrong"+name,key,repo,store,scheme,emoder,dmoder,dtime,object).getResponse();

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

		Map mapActual = projectAdminModel.updateProject("wrong"+name,key,repo,store,scheme,emoder,dmoder,dtime,object).getResponse();

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("code", "400");
		map.put("message", "project retrieval failed");
		map.put("cause", "test exception");

		assertEquals(map.toString(), mapActual.toString());
	}

	@Test
	public void testDeleteProject()
	{
		Mockito.when(projectManager.getProjectByKey(key)).thenReturn(project);

		Map mapActual = projectAdminModel.deleteProject(key).getResponse();

		Mockito.verify(projectManager).deleteProject(project);

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

		Map mapActual = projectAdminModel.deleteProject(key).getResponse();

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

		ResponseProjectDataList responseProjectDataList = projectAdminModel.listProject();

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("code", "200");
		map.put("message", "operation succeeded");
		map.put("cause", "");

		assertEquals(map.toString(), responseProjectDataList.getResponse().toString());
		assertEquals("[{\"name\":\"MyOwnProject\",\"key\":\"MOP\",\"defaultRepositoryName\":\"null\"," +
					 "\"storeRevisions\":\"false\",\"permissionSchemeId\":\"null\",\"moderatorEnabled\":\"false\"," +
					 "\"defaultModerator\":\"dkoudela\",\"defaultDuration\":\"1000\"," +
					 "\"defaultObjectives\":\"I object !!!\n\"} ]",
					 responseProjectDataList.getProjectList().toString());
	}

	@Test
	public void testListProjectTwoProjectsValidParams()
	{
		List projects = new ArrayList<Project>();
		projects.add(project);
		projects.add(project);
		Mockito.when(projectManager.getAllProjects()).thenReturn(projects);

		ResponseProjectDataList responseProjectDataList = projectAdminModel.listProject();

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("code", "200");
		map.put("message", "operation succeeded");
		map.put("cause", "");

		assertEquals(map.toString(), responseProjectDataList.getResponse().toString());
		assertEquals("[{\"name\":\"MyOwnProject\",\"key\":\"MOP\",\"defaultRepositoryName\":\"null\"," +
					  "\"storeRevisions\":\"false\",\"permissionSchemeId\":\"null\",\"moderatorEnabled\":\"false\"," +
					  "\"defaultModerator\":\"dkoudela\",\"defaultDuration\":\"1000\"," +
					  "\"defaultObjectives\":\"I object !!!\n\"} , " +
					  "{\"name\":\"MyOwnProject\",\"key\":\"MOP\",\"defaultRepositoryName\":\"null\"," +
					  "\"storeRevisions\":\"false\",\"permissionSchemeId\":\"null\",\"moderatorEnabled\":\"false\"," +
					  "\"defaultModerator\":\"dkoudela\",\"defaultDuration\":\"1000\"," +
					  "\"defaultObjectives\":\"I object !!!\n\"} ]",
					 responseProjectDataList.getProjectList().toString());
	}

	@Test
	public void testListProjectProjectException()
	{
		Mockito.when(projectManager.getAllProjects()).thenThrow(new RuntimeException("test exception"));

		Map mapActual = projectAdminModel.listProject().getResponse();

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("code", "400");
		map.put("message", "project list failed");
		map.put("cause", "test exception");

		assertEquals(map.toString(), mapActual.toString());
	}
}
