package it.com.davidkoudela.crucible.framework;

import com.davidkoudela.crucible.model.RepositoryRestData;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: Repository Rest Data service managing JSON (de)serialization for Repository Rest Data
 * Copyright (C) 2016 David Koudela
 *
 * @author dkoudela
 * @since 18.10.2016
 */
public class RepositoryRestDataServiceImpl implements RepositoryRestDataService {
	public RepositoryRestData createRepositoryData(String json) {
		RepositoryRestData repositoryRestData = new Gson().fromJson(json, RepositoryRestData.class);
		return repositoryRestData;
	}

	public Map<String, String> compareRepositoryData(String json, RepositoryRestData repositoryRestData) {
		JsonParser parser = new JsonParser();
		JsonElement expectedJsonElement = parser.parse(json);
		JsonElement actualJsonElement = parser.parse(new Gson().toJson(repositoryRestData));
		String expectedJsonString = expectedJsonElement.toString();
		String actualJsonString = actualJsonElement.toString();
		Map<String, String> result = new HashMap<String, String>();
		result.put("expected", expectedJsonString);
		result.put("actual", actualJsonString);
		return result;
	}
}
