package com.davidkoudela.crucible.rest.api;

import com.atlassian.annotations.security.XsrfProtectionExcluded;
import com.atlassian.plugins.rest.common.interceptor.InterceptorChain;
import com.davidkoudela.crucible.model.ProjectAdminModelImpl;
import com.davidkoudela.crucible.rest.intercept.ProjectAdminInterceptor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Description: Plugin's REST API for Crucible Project's Listing operations.
 * Copyright (C) 2014 David Koudela
 *
 * @author dkoudela
 * @since 14.5.2014
 */

@Produces({MediaType.APPLICATION_JSON})
@Path("/project-list")
@Provider()
@InterceptorChain({ProjectAdminInterceptor.class})
public class ProjectListRestApi
{
	private ProjectAdminModelImpl projectAdminModelImpl;

	/**
	 * Constructor for Spring class injection.
	 *
	 * @param projectAdminModelImpl used for all Project admin tasks (Create / Modify / Delete / List)
	 */
	@org.springframework.beans.factory.annotation.Autowired
	public ProjectListRestApi(ProjectAdminModelImpl projectAdminModelImpl)
	{
		this.projectAdminModelImpl = projectAdminModelImpl;
	}

	/**
	 * Provides HTTP GET method for project List operation
	 *
	 * @param key the project key used when giving reviews their unique code names
	 * @return ws response containing result of the operation
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response listProjectGet(@QueryParam("key") String key)
	{
		return listProjectFacade(key);
	}

	/**
	 * Provides HTTP POST method for project List operation
	 *
	 * @param key the project key used when giving reviews their unique code names
	 * @return ws response containing result of the operation
	 */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@XsrfProtectionExcluded()
	public Response listProjectPost(@FormParam("key") String key)
	{
		return listProjectFacade(key);
	}

	/**
	 * Facade for any error or exception handling coming from model returning just the Response at the end
	 *
	 * @param key the project key used when giving reviews their unique code names
	 * @return ws response containing result of the operation
	 */
	private Response listProjectFacade(String key)
	{
		try
		{
			if (null != key && false == key.isEmpty()) {
				return Response.ok().entity(projectAdminModelImpl.listProject(key)).build();
			} else {
				return Response.ok().entity(projectAdminModelImpl.listProject()).build();
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception in the ProjectListRestApi: "+e.getMessage());
			return Response.serverError().build();
		}
	}
}
