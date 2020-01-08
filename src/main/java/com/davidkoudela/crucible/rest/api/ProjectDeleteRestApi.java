package com.davidkoudela.crucible.rest.api;

import com.atlassian.annotations.security.XsrfProtectionExcluded;
import com.atlassian.plugins.rest.common.interceptor.InterceptorChain;
import com.davidkoudela.crucible.model.ProjectAdminModelImpl;
import com.davidkoudela.crucible.rest.intercept.ProjectAdminInterceptor;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Description: Plugin's REST API for Crucible Project's Delete operations.
 * Copyright (C) 2014 David Koudela
 *
 * @author dkoudela
 * @since 14.5.2014
 */

@Produces({MediaType.APPLICATION_JSON})
@Path("/project-delete")
@Provider()
@InterceptorChain({ProjectAdminInterceptor.class})
public class ProjectDeleteRestApi
{
	private ProjectAdminModelImpl projectAdminModelImpl;

	/**
	 * Constructor for Spring class injection.
	 *
	 * @param projectAdminModelImpl used for all Project admin tasks (Create / Modify / Delete / List)
	 */
	@org.springframework.beans.factory.annotation.Autowired
	public ProjectDeleteRestApi(ProjectAdminModelImpl projectAdminModelImpl)
	{
		this.projectAdminModelImpl = projectAdminModelImpl;
	}

	/**
	 * Provides HTTP GET method for project Delete operation
	 *
	 * @param key the project key used when giving reviews their unique code names
	 * @param deleteReviews if reviews of the project have to be deleted, otherwise only empty projects can be deleted
	 * @return ws response containing result of the operation
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteProjectGet(@QueryParam("key") String key,
	                                 @QueryParam("keys") String keys,
	                                 @QueryParam("deleteReviews") String deleteReviews
	)
	{
		return deleteProjectFacade(key, keys, deleteReviews);
	}

	/**
	 * Provides HTTP POST method for project Delete operation
	 *
	 * @param key the project key used when giving reviews their unique code names
	 * @param deleteReviews if reviews of the project have to be deleted, otherwise only empty projects can be deleted
	 * @return ws response containing result of the operation
	 */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@XsrfProtectionExcluded()
	public Response deleteProjectPost(@FormParam("key") String key,
	                                  @FormParam("keys") String keys,
	                                  @FormParam("deleteReviews") String deleteReviews
	)
	{
		return deleteProjectFacade(key, keys, deleteReviews);
	}

	/**
	 * Facade for any error or exception handling coming from model returning just the Response at the end
	 *
	 * @param key the project key used when giving reviews their unique code names
	 * @param keys the project keys used when giving reviews their unique code names
	 * @param deleteReviews if reviews of the project have to be deleted, otherwise only empty projects can be deleted
	 * @return ws response containing result of the operation
	 */
	private Response deleteProjectFacade(String key, String keys, String deleteReviews)
	{
		try
		{
			if (null != keys && !keys.isEmpty()) {
				return Response.ok().entity(projectAdminModelImpl.deleteProjects(keys, deleteReviews)).build();
			} else {
				return Response.ok().entity(projectAdminModelImpl.deleteProject(key, deleteReviews)).build();
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception in the ProjectDeleteRestApi: "+e.getMessage());
			return Response.serverError().build();
		}
	}
}
