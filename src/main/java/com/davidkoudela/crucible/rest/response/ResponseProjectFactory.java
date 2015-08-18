package com.davidkoudela.crucible.rest.response;

import com.cenqua.crucible.model.Project;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: Factory method for Response Project construction containing result code, result message and result cause
 * Copyright (C) 2014 David Koudela
 *
 * @author dkoudela
 * @since 3.6.2014
 */
public class ResponseProjectFactory
{
	/**
	 * Constructs ResponseProjectOperation containing provided code, message and cause key-value pairs used in REST responses
	 *
	 * @param code contains result code a.k.a. http result code
	 * @param message contains info about failed operation
	 * @param cause contains root cause of the failure
	 * @return ResponseProjectOperation containing code, message and cause key-value pairs used in REST responses
	 */
	public static ResponseProjectOperation constructResponse(String code, String message, String cause)
	{
		Map<String, String> response = new LinkedHashMap<String, String>();
		response.put("code", code);
		response.put("message", message);
		response.put("cause", cause);
		ResponseProjectOperation responseProjectOperation = new ResponseProjectOperation(response, null);
		return responseProjectOperation;
	}

	/**
	 * Constructs ResponseProjectOperation containing provided code, message and cause key-value pairs used in REST responses
	 *
	 * @param code contains result code a.k.a. http result code
	 * @param message contains info about failed operation
	 * @param cause contains root cause of the failure
	 * @param relatedProject the related project containing all the already set data
	 * @return ResponseProjectOperation containing code, message and cause key-value pairs used in REST responses
	 */
	public static ResponseProjectOperation constructResponse(String code, String message, String cause, Project relatedProject)
	{
	    Map<String, String> response = new LinkedHashMap<String, String>();
	    response.put("code", code);
	    response.put("message", message);
	    response.put("cause", cause);
	    ResponseProjectOperation responseProjectOperation = new ResponseProjectOperation(response, relatedProject);
	    return responseProjectOperation;
	}

	/**
	 * Constructs ResponseProjectDataList containing provided code, message, cause and Project Data List used in REST responses
	 *
	 * @param code contains result code a.k.a. http result code
	 * @param message contains info about failed operation
	 * @param cause contains root cause of the failure
	 * @param list contains Project Data list provided in the REST response
	 * @return ResponseProjectDataList containing code, message and cause key-value pairs used in REST responses
	 */
	public static ResponseProjectDataList constructResponseWithList(String code, String message, String cause, List<ProjectProperties> list)
	{
		Map<String, String> response = new LinkedHashMap<String, String>();
		response.put("code", code);
		response.put("message", message);
		response.put("cause", cause);
		ResponseProjectDataList responseProjectDataList = new ResponseProjectDataList(response, list);
		return responseProjectDataList;
	}
}
