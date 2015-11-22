package com.davidkoudela.crucible.rest.response;

import com.atlassian.fisheye.spi.admin.data.RepositoryData;
import com.davidkoudela.crucible.model.RepositoryRestData;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description: Factory method for Response Repository construction containing result code, result message and result cause
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 13.8.2015
 */
public class ResponseRepositoryFactory {
	public static ResponseRepositoryOperation constructResponse(String code, String message, String cause)
	{
		Map<String, String> response = new LinkedHashMap<String, String>();
		response.put("code", code);
		response.put("message", message);
		response.put("cause", cause);
		ResponseRepositoryOperation responseRepositoryOperation = new ResponseRepositoryOperation(response);
		return responseRepositoryOperation;
	}

	public static ResponseRepositoryNameList constructResponseWithList(String code, String message, String cause, Set<String> names)
	{
		Map<String, String> response = new LinkedHashMap<String, String>();
		response.put("code", code);
		response.put("message", message);
		response.put("cause", cause);
		ResponseRepositoryNameList responseRepositoryNameList = new ResponseRepositoryNameList(response, names);
		return responseRepositoryNameList;
	}

	public static ResponseRepositoryData constructResponseWithRepositoryData(String code, String message, String cause, RepositoryRestData repositoryRestData)
	{
		Map<String, String> response = new LinkedHashMap<String, String>();
		response.put("code", code);
		response.put("message", message);
		response.put("cause", cause);
		ResponseRepositoryData responseRepositoryData = new ResponseRepositoryData(response, repositoryRestData);
		return responseRepositoryData;
	}
}
