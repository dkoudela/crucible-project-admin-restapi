package com.davidkoudela.crucible.rest.api;

import com.atlassian.annotations.security.XsrfProtectionExcluded;
import com.atlassian.plugins.rest.common.interceptor.InterceptorChain;
import com.davidkoudela.crucible.model.RepositoryAdminModelImpl;
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
 * Description: Plugin's REST API for FishEye/Crucible Repository Modification operations.
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
	private RepositoryAdminModelImpl repositoryAdminModelImpl;

	@org.springframework.beans.factory.annotation.Autowired
	public RepositoryUpdateRestApi(RepositoryAdminModelImpl repositoryAdminModelImpl){ this.repositoryAdminModelImpl = repositoryAdminModelImpl; }

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@XsrfProtectionExcluded()
	public Response updateRepositoryPost(RepositoryRestData repositoryRestData)
	{
		try
		{
			return Response.ok().entity(repositoryAdminModelImpl.updateRepository(repositoryRestData)).build();
		}
		catch (Exception e)
		{
			System.out.println("Exception in the RepositoryUpdateRestApi: " + e.getMessage());
			return Response.serverError().build();
		}
	}
}
