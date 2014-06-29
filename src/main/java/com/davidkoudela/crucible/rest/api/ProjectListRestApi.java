package com.davidkoudela.crucible.rest.api;

import com.atlassian.plugins.rest.common.interceptor.InterceptorChain;
import com.davidkoudela.crucible.model.ProjectAdminModel;
import com.davidkoudela.crucible.rest.intercept.ProjectAdminInterceptor;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
	private ProjectAdminModel projectAdminModel;

	/**
	 * Constructor for Spring class injection.
	 *
	 * @param projectAdminModel used for all Project admin tasks (Create / Modify / Delete / List)
	 */
	@org.springframework.beans.factory.annotation.Autowired
	public ProjectListRestApi(ProjectAdminModel projectAdminModel)
	{
		this.projectAdminModel = projectAdminModel;
	}

	/**
	 * Provides HTTP GET method for project List operation
	 *
	 * @return ws response containing result of the operation
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteProjectGet()
	{
		return listProjectFacade();
	}

	/**
	 * Provides HTTP POST method for project List operation
	 *
	 * @return ws response containing result of the operation
	 */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteProjectPost()
	{
		return listProjectFacade();
	}

	/**
	 * Facade for any error or exception handling coming from model returning just the Response at the end
	 *
	 * @return ws response containing result of the operation
	 */
	private Response listProjectFacade()
	{
		try
		{
			return Response.ok().entity(projectAdminModel.listProject()).build();
		}
		catch (Exception e)
		{
			System.out.println("Exception in the ProjectListRestApi: "+e.getMessage());
			return Response.serverError().build();
		}
	}
}
