package com.davidkoudela.crucible.rest.intercept;

import com.atlassian.fecru.user.EffectiveUserProvider;
import com.atlassian.plugins.rest.common.interceptor.MethodInvocation;
import com.atlassian.plugins.rest.common.interceptor.ResourceInterceptor;
import com.davidkoudela.crucible.rest.response.ResponseProjectFactory;

import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;

/**
 * Description: Servlet Interceptor allowing only Administrator access.
 * Copyright (C) 2014 David Koudela
 *
 * @author dkoudela
 * @since 23.6.2014
 */
public class ProjectAdminInterceptor implements ResourceInterceptor
{
	private final EffectiveUserProvider effectiveUserProvider;

	/**
	 * Constructor for Spring class injection.
	 *
	 * @param effectiveUserProvider used for getting current user priviledgies
	 */
	public ProjectAdminInterceptor(EffectiveUserProvider effectiveUserProvider)
	{
		this.effectiveUserProvider = effectiveUserProvider;
	}

	/**
	 * Provides interception of the incoming Servlet requests.
	 * It passes the request if the current user has the Administrator permissions,
	 * otherwise it is rejected with HTTP 401.
	 *
	 * @param invocation Http context to be intercepted
	 */
	public void intercept(MethodInvocation invocation) throws IllegalAccessException, InvocationTargetException
	{
		if (this.effectiveUserProvider.isAdmin())
			invocation.invoke();
		else
			invocation.getHttpContext().getResponse().setResponse(Response.status(401).entity(ResponseProjectFactory.constructResponse("401", "You must be an administrator to access this resource.", "")).build());
	}
}
