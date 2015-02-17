package com.davidkoudela.crucible.model;

import com.atlassian.crucible.actions.admin.project.ProjectData;
import com.atlassian.fecru.user.User;
import com.cenqua.crucible.model.Project;
import com.cenqua.crucible.model.managers.PermissionManager;
import com.cenqua.crucible.model.managers.ProjectManager;
import com.cenqua.fisheye.config.RepositoryManager;
import com.cenqua.fisheye.rep.RepositoryHandle;
import com.cenqua.fisheye.user.UserManager;
import com.davidkoudela.crucible.rest.response.ProjectProperties;
import com.davidkoudela.crucible.rest.response.ResponseFactory;
import com.davidkoudela.crucible.rest.response.ResponseProjectDataList;
import com.davidkoudela.crucible.rest.response.ResponseProjectOperation;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

/**
 * Description: Model class containing the logic functionality for Create / Modify / Delete Crucible Projects.
 * Copyright (C) 2014 David Koudela
 *
 * @author dkoudela
 * @since 15.5.2014
 */
@org.springframework.stereotype.Service("projectAdminModel")
public class ProjectAdminModel
{
	private static final Logger log = LoggerFactory.getLogger(ProjectAdminModel.class);

	private PermissionManager permissionManager;
	private ProjectManager projectManager;
	private RepositoryManager repositoryManager;
	private UserManager userManager;

	/**
	 * Constructor for Spring class injection.
	 *
	 * @param permissionManager used for getting permission scheme
	 * @param projectManager used for Crucible project create and delete
	 * @param repositoryManager used for getting info about FishEye repositories
	 * @param userManager used for getting info about Crucible users
	 */
	@org.springframework.beans.factory.annotation.Autowired
	public ProjectAdminModel(PermissionManager permissionManager, ProjectManager projectManager, RepositoryManager repositoryManager, UserManager userManager)
	{
		this.permissionManager = permissionManager;
		this.projectManager = projectManager;
		this.repositoryManager = repositoryManager;
		this.userManager = userManager;
	}

	/**
	 * Creates a new Crucible project
	 *
	 * @param name the plain language name as displayed in the Crucible interface
	 * @param key the project key used when giving reviews their unique code names
	 * @param defaultRepositoryName default FishEye repository name
	 * @param storeRevisions retains a copy of all the source files under review even if the repository is disconnected
	 * @param permissionSchemeName permission scheme applied to this project
	 * @param moderatorEnabled clear to have reviews run by the author only
	 * @param defaultModerator the user who will be set as the moderator for all new reviews created in the project
	 * @param defaultDuration the default length of time (in week days) for reviews in this project
	 * @param defaultObjectives specify some text that will appear by default in the Review Objectives field of each new review
	 * @return ResponseProjectOperation containing code, message and cause key-value pairs used in REST responses
	 */
	public ResponseProjectOperation newProject(String name,
							  String key,
							  String defaultRepositoryName,
							  String storeRevisions,
							  String permissionSchemeName,
							  String moderatorEnabled,
							  String defaultModerator,
							  String defaultDuration,
							  String defaultObjectives)
	{
		Project project;
		try
		{
			log.info("Creating project: name: " + name + " key: " + key);
			project = this.projectManager.createProject(name, key);
		}
		catch (Exception e)
		{
			log.info("Project creation failed: name: " + name + " key: " + key + " message: " + e.getMessage());
			return ResponseFactory.constructResponse("400", "project creation failed", e.getMessage());
		}
		return submitProject(project, defaultRepositoryName, storeRevisions, permissionSchemeName, moderatorEnabled, defaultModerator, defaultDuration, defaultObjectives);
	}

	/**
	 * Modifies an existing Crucible project
	 *
	 * @param name the plain language name as displayed in the Crucible interface
	 * @param key the project key used when giving reviews their unique code names
	 * @param defaultRepositoryName default FishEye repository name
	 * @param storeRevisions retains a copy of all the source files under review even if the repository is disconnected
	 * @param permissionSchemeName permission scheme applied to this project
	 * @param moderatorEnabled clear to have reviews run by the author only
	 * @param defaultModerator the user who will be set as the moderator for all new reviews created in the project
	 * @param defaultDuration the default length of time (in week days) for reviews in this project
	 * @param defaultObjectives specify some text that will appear by default in the Review Objectives field of each new review
	 * @return ResponseProjectOperation containing code, message and cause key-value pairs used in REST responses
	 */
	public ResponseProjectOperation updateProject(String name,
							  String key,
							  String defaultRepositoryName,
							  String storeRevisions,
							  String permissionSchemeName,
							  String moderatorEnabled,
							  String defaultModerator,
							  String defaultDuration,
							  String defaultObjectives)
	{
		Project project;
		try
		{
			log.info("Updating project: name: " + name + " key: " + key);
			project = this.projectManager.getProjectByKey(key);
			if (project.getName().compareTo(name) != 0)
			{
				log.info("Project name and key do not match: name: " + name + " key: " + key);
				return ResponseFactory.constructResponse("400", "project retrieval failed", "project name and key do not match: expected name: "+project.getName()+" provided name: "+name);
			}
		}
		catch (Exception e)
		{
			log.info("Project retrieval failed: name: " + name + " key: " + key + " message: " + e.getMessage());
			return ResponseFactory.constructResponse("400", "project retrieval failed", e.getMessage());
		}
		return submitProject(project, defaultRepositoryName, storeRevisions, permissionSchemeName, moderatorEnabled, defaultModerator, defaultDuration, defaultObjectives);
	}

	/**
	 * Deletes an existing Crucible project
	 *
	 * @param key the project key used when giving reviews their unique code names
	 * @return ResponseProjectOperation containing code, message and cause key-value pairs used in REST responses
	 */
	public ResponseProjectOperation deleteProject(String key)
	{
		Project project;
		try
		{
			log.info("Deleting project: key: " + key);
			project = this.projectManager.getProjectByKey(key);
			this.projectManager.deleteProject(project);
		}
		catch (Exception e)
		{
			log.info("Project deleting failed: key: " + key + " message: " + e.getMessage());
			return ResponseFactory.constructResponse("400", "project delete failed", e.getMessage());
		}
		return ResponseFactory.constructResponse("200", "operation succeeded", "");
	}

	/**
	 * Lists existing Crucible projects
	 *
	 * @return ResponseProjectDataList containing code, message, cause and project list key-value pairs used in REST responses
	 */
	public ResponseProjectDataList listProject()
	{
		List<Project> projectList;
		List<ProjectProperties> projectPropertiesList = new ArrayList<ProjectProperties>();
		try
		{
			log.info("Listing projects");
			projectList = this.projectManager.getAllProjects();
			log.info("Project list size: " + projectList.size());
			for (Project project : projectList)
			{
				projectPropertiesList.add(new ProjectProperties(project));
			}
		}
		catch (Exception e)
		{
			log.info("Project listing failed: message: " + e.getMessage());
			return ResponseFactory.constructResponseWithList("400", "project list failed", e.getMessage(), projectPropertiesList);
		}
		return ResponseFactory.constructResponseWithList("200", "operation succeeded", "", projectPropertiesList);
	}

	/**
	 * Applies provides changes on the given project and submits them to the Crucible
	 *
	 * @param project existing project instance to be modified
	 * @param defaultRepositoryName default FishEye repository name
	 * @param storeRevisions retains a copy of all the source files under review even if the repository is disconnected
	 * @param permissionSchemeName permission scheme applied to this project
	 * @param moderatorEnabled clear to have reviews run by the author only
	 * @param defaultModerator the user who will be set as the moderator for all new reviews created in the project
	 * @param defaultDuration the default length of time (in week days) for reviews in this project
	 * @param defaultObjectives specify some text that will appear by default in the Review Objectives field of each new review
	 * @return ResponseProjectOperation containing code, message and cause key-value pairs used in REST responses
	 */
	private ResponseProjectOperation submitProject(Project project, String defaultRepositoryName, String storeRevisions, String permissionSchemeName,
				String moderatorEnabled, String defaultModerator, String defaultDuration, String defaultObjectives)
	{
		try
		{
			log.error("Submitting project: defaultRepositoryName: " + defaultRepositoryName + " storeRevisions: " + storeRevisions +
					  " permissionSchemeName: " + permissionSchemeName + " moderatorEnabled: " + moderatorEnabled + " defaultModerator: " + defaultModerator +
					  " defaultDuration: " + defaultDuration + " defaultObjectives: " + defaultObjectives);
			ProjectData projectData = new ProjectData(project);
			Project prefetchProjectFromProjectData = projectManager.getProjectById(projectData.getId());

			prefetchProjectFromProjectData.setAllowReviewersToJoin(projectData.isAllowReviewersToJoin());
			prefetchProjectFromProjectData.setStoreRevisions(StringConverter.string2bool(storeRevisions));
			prefetchProjectFromProjectData.setDefaultReviewerGroups(projectData.getDefaultReviewerGroups());
			prefetchProjectFromProjectData.setProjKey(projectData.getKey());
			prefetchProjectFromProjectData.setName(projectData.getName());
			prefetchProjectFromProjectData.setPermissionScheme(this.permissionManager.findPermissionSchemeByName(permissionSchemeName));
			boolean moderatorEnabledBoolean = StringConverter.string2bool(moderatorEnabled);
			prefetchProjectFromProjectData.setModeratorDisabled(!moderatorEnabledBoolean);
			if (0 != defaultModerator.length() && true == moderatorEnabledBoolean)
			{
				log.info("Moderator is enabled and Default Moderator is set");
				User user = this.userManager.getUser(defaultModerator);
				prefetchProjectFromProjectData.setDefaultModerator(user);
			}
			prefetchProjectFromProjectData.setSuggestedReviewerConfig(projectData.getSuggestedReviewerConfig());
			prefetchProjectFromProjectData.setDefaultDuration(StringConverter.string2Integer(defaultDuration));
			prefetchProjectFromProjectData.setContentRoots(new HashSet(projectData.getContentRoots()));
			prefetchProjectFromProjectData.setDefaultObjectives(defaultObjectives);

			log.info("Getting repository handle");
			RepositoryHandle repositoryHandle = this.repositoryManager.getRepository(defaultRepositoryName);
			log.info("Removing repository mappings");
			this.projectManager.removeRepositoryMappings(repositoryHandle.getName());
			prefetchProjectFromProjectData.setDefaultRepositoryName(defaultRepositoryName);
			log.info("Adding repository mappings");
			this.projectManager.addPathToProject(prefetchProjectFromProjectData.getProjKey(), repositoryHandle.getName(), "/");
		}
		catch (Exception e)
		{
			log.info("Project modification failed: message: " + e.getMessage());
			return ResponseFactory.constructResponse("400", "project modification failed", e.getMessage());
		}
		return ResponseFactory.constructResponse("200", "operation succeeded", "");
	}
}
