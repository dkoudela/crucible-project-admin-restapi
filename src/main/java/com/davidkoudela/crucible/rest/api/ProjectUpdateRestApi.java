package com.davidkoudela.crucible.rest.api;

import com.atlassian.annotations.security.XsrfProtectionExcluded;
import com.atlassian.plugins.rest.common.interceptor.InterceptorChain;
import com.davidkoudela.crucible.model.ProjectAdminModelImpl;
import com.davidkoudela.crucible.rest.intercept.ProjectAdminInterceptor;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Description: Plugin's REST API for Crucible Project's Modify operations.
 * Copyright (C) 2014 David Koudela
 *
 * @author dkoudela
 * @since 14.5.2014
 */

@Produces({MediaType.APPLICATION_JSON})
@Path("/project-update")
@Provider()
@InterceptorChain({ProjectAdminInterceptor.class})
public class ProjectUpdateRestApi
{
	private ProjectAdminModelImpl projectAdminModelImpl;

	/**
	 * Constructor for Spring class injection.
	 *
	 * @param projectAdminModelImpl used for all Project admin tasks (Create / Modify / Delete / List)
	 */
	@org.springframework.beans.factory.annotation.Autowired
	public ProjectUpdateRestApi(ProjectAdminModelImpl projectAdminModelImpl)
	{
		this.projectAdminModelImpl = projectAdminModelImpl;
	}

	/**
	 * Provides HTTP GET method for project Modification operation
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
	 * @return ws response containing result of the operation
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateProjectGet(@QueryParam("name") String name,
								  @QueryParam("key") String key,
								  @QueryParam("defaultRepositoryName") String defaultRepositoryName,
								  @QueryParam("storeRevisions") String storeRevisions,
								  @QueryParam("permissionSchemeName") String permissionSchemeName,
								  @QueryParam("moderatorEnabled") String moderatorEnabled,
								  @QueryParam("defaultModerator") String defaultModerator,
								  @QueryParam("defaultDuration") String defaultDuration,
								  @QueryParam("defaultObjectives") String defaultObjectives
	)
	{
		return updateProjectFacade(name, key, defaultRepositoryName, storeRevisions, permissionSchemeName,
								   moderatorEnabled, defaultModerator, defaultDuration, defaultObjectives);
	}

	/**
	 * Provides HTTP POST method for project Modification operation
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
	 * @return ws response containing result of the operation
	 */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@XsrfProtectionExcluded()
	public Response updateProjectPost(@FormParam("name") String name,
									 @FormParam("key") String key,
									 @FormParam("defaultRepositoryName") String defaultRepositoryName,
									 @FormParam("storeRevisions") String storeRevisions,
									 @FormParam("permissionSchemeName") String permissionSchemeName,
									 @FormParam("moderatorEnabled") String moderatorEnabled,
									 @FormParam("defaultModerator") String defaultModerator,
									 @FormParam("defaultDuration") String defaultDuration,
									 @FormParam("defaultObjectives") String defaultObjectives
	)
	{
		return updateProjectFacade(name, key, defaultRepositoryName, storeRevisions, permissionSchemeName,
								   moderatorEnabled, defaultModerator, defaultDuration, defaultObjectives);
	}

	/**
	 * Facade for any error or exception handling coming from model returning just the Response at the end
	 *
	 * @param key the project key used when giving reviews their unique code names
	 * @return ws response containing result of the operation
	 */
	private Response updateProjectFacade(String name, String key, String defaultRepositoryName, String storeRevisions,
										 String permissionSchemeName, String moderatorEnabled, String defaultModerator,
										 String defaultDuration, String defaultObjectives)
	{
		try
		{
			return Response.ok().entity(projectAdminModelImpl.updateProject(name, key, defaultRepositoryName, storeRevisions, permissionSchemeName,
																		moderatorEnabled, defaultModerator, defaultDuration, defaultObjectives)).build();
		}
		catch (Exception e)
		{
			System.out.println("Exception in the ProjectUpdateRestApi: "+e.getMessage());
			return Response.serverError().build();
		}
	}
}
