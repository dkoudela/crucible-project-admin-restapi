package com.davidkoudela.crucible.rest.api;

import com.atlassian.plugins.rest.common.interceptor.InterceptorChain;
import com.davidkoudela.crucible.model.RepositoryAdminModel;
import com.davidkoudela.crucible.model.RepositoryRestData;
import com.davidkoudela.crucible.rest.intercept.ProjectAdminInterceptor;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Description: Plugin's REST API for FishEye/Crucible Repository Creation operations.
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 24.8.2015
 */
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/repository-update")
@Provider()
@InterceptorChain({ProjectAdminInterceptor.class})
public class RepositoryUpdateRestApi {
	private RepositoryAdminModel repositoryAdminModel;

	@org.springframework.beans.factory.annotation.Autowired
	public RepositoryUpdateRestApi(RepositoryAdminModel repositoryAdminModel){ this.repositoryAdminModel = repositoryAdminModel; }

	@POST
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateRepositoryPost(RepositoryRestData repositoryRestData)
	{
		try
		{
			return Response.ok().entity(repositoryAdminModel.updateRepository(repositoryRestData)).build();
		}
		catch (Exception e)
		{
			System.out.println("Exception in the RepositoryUpdateRestApi: " + e.getMessage());
			return Response.serverError().build();
		}
	}
}
