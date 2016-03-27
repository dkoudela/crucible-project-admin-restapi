package com.davidkoudela.crucible.rest.api;

import com.atlassian.annotations.security.XsrfProtectionExcluded;
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
 * Description: Plugin's REST API for FishEye/Crucible Repository Listing operations.
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 21.11.2015
 */
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/repository-list")
@Provider()
@InterceptorChain({ProjectAdminInterceptor.class})
public class RepositoryListRestApi {
	private RepositoryAdminModel repositoryAdminModel;

	@org.springframework.beans.factory.annotation.Autowired
	public RepositoryListRestApi(RepositoryAdminModel repositoryAdminModel){ this.repositoryAdminModel = repositoryAdminModel; }

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@XsrfProtectionExcluded()
	public Response listRepositoryPost(RepositoryRestData repositoryRestData)
	{
		try
		{
			if (null != repositoryRestData.name && false == repositoryRestData.name.isEmpty()) {
				return Response.ok().entity(repositoryAdminModel.listRepository(repositoryRestData.name)).build();
			} else {
				return Response.ok().entity(repositoryAdminModel.listRepository()).build();
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception in the RepositoryListRestApi: " + e.getMessage());
			return Response.serverError().build();
		}
	}
}
