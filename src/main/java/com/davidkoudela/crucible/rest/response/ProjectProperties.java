package com.davidkoudela.crucible.rest.response;

import com.atlassian.crucible.spi.data.ReviewData;
import com.atlassian.fecru.user.FecruUser;
import com.cenqua.crucible.model.Project;
import com.davidkoudela.crucible.review.ReviewVisitorData;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Collection;

/**
 * Description: Project Properties holder used by Jackson JSON processor
 *              for generating Project part of the REST response in JSON.
 * Copyright (C) 2014 David Koudela
 *
 * @author dkoudela
 * @since 17.6.2014
 */
@JsonAutoDetect
public class ProjectProperties
{
	@JsonProperty
	String name;
	@JsonProperty
	String key;
	@JsonProperty
	String defaultRepositoryName;
	@JsonProperty
	Boolean storeRevisions;
	@JsonProperty
	Integer permissionSchemeId;
	@JsonProperty
	Boolean moderatorEnabled;
	@JsonProperty
	String defaultModerator;
	@JsonProperty
	Integer defaultDuration;
	@JsonProperty
	String defaultObjectives;
	@JsonProperty
	Integer numberOfReviews;

	/**
	 * Constructor for ProjectProperties based on provided Crucible project
	 *
	 * @param project to be used for the ProjectProperties construction
	 */
	public ProjectProperties(Project project, Integer reviewsInProject)
	{
		name = project.getName();
		key = project.getProjKey();
		defaultRepositoryName = project.getDefaultRepositoryName();
		storeRevisions = project.isStoreRevisions();
		permissionSchemeId = project.getPermissionScheme().getId();
		moderatorEnabled = project.isModeratorEnabled();
		defaultModerator = null;
		FecruUser defaultModeratorUser = project.getDefaultModerator();
		if (null != defaultModeratorUser)
			defaultModerator = defaultModeratorUser.getUsername();
		defaultDuration = project.getDefaultDuration();
		defaultObjectives = project.getDefaultObjectives();
		numberOfReviews = reviewsInProject;
	}

	/**
	 * Getter for Project Name used by Jackson JSON processor
	 *
	 * @return String containing the Project Name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Getter for Project Key used by Jackson JSON processor
	 *
	 * @return String containing the Project Key
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * Getter for Default Repository Name used by Jackson JSON processor
	 *
	 * @return String containing the Default Repository Name
	 */
	public String getDefaultRepositoryName()
	{
		return defaultRepositoryName;
	}

	/**
	 * Getter for Store Revisions used by Jackson JSON processor
	 *
	 * @return Boolean which is true if the Store Revisions are enabled, otherwise false
	 */
	public Boolean getStoreRevisions()
	{
		return storeRevisions;
	}

	/**
	 * Getter for Permission Scheme Id used by Jackson JSON processor
	 *
	 * @return Integer containing index number of the Permission Scheme
	 */
	public Integer getPermissionSchemeId()
	{
		return permissionSchemeId;
	}

	/**
	 * Getter for Moderator Enabled used by Jackson JSON processor
	 *
	 * @return Boolean which is true if the moderator of a Project's Review is enabled, otherwise false
	 */
	public Boolean getModeratorEnabled()
	{
		return moderatorEnabled;
	}

	/**
	 * Getter for Default Moderator used by Jackson JSON processor
	 *
	 * @return String containing the Default Moderator User Name of a Project's Review
	 */
	public String getDefaultModerator()
	{
		return defaultModerator;
	}

	/**
	 * Getter for Default Duration used by Jackson JSON processor
	 *
	 * @return Integer containing the Default Duration of a Project's Review in Work days
	 */
	public Integer getDefaultDuration()
	{
		return defaultDuration;
	}

	/**
	 * Getter for Default Objectives used by Jackson JSON processor
	 *
	 * @return String containing the Default Objectives
	 */
	public String getDefaultObjectives()
	{
		return defaultObjectives;
	}

	/**
	 * Setter for Project Name used by Jackson JSON processor
	 *
	 * @param name the Project Name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Setter for Project Key used by Jackson JSON processor
	 *
	 * @param key the Project Key
	 */
	public void setKey(String key)
	{
		this.key = key;
	}

	/**
	 * Setter for Default Repository Name used by Jackson JSON processor
	 *
	 * @param defaultRepositoryName the Default Repository Name
	 */
	public void setDefaultRepositoryName(String defaultRepositoryName)
	{
		this.defaultRepositoryName = defaultRepositoryName;
	}

	/**
	 * Setter for Store Revisions used by Jackson JSON processor
	 *
	 * @param storeRevisions true if the Store Revisions are enabled, otherwise false
	 */
	public void setStoreRevisions(Boolean storeRevisions)
	{
		this.storeRevisions = storeRevisions;
	}

	/**
	 * Setter for Permission Scheme Id used by Jackson JSON processor
	 *
	 * @param permissionSchemeId index number of the Permission Scheme
	 */
	public void setPermissionSchemeId(Integer permissionSchemeId)
	{
		this.permissionSchemeId = permissionSchemeId;
	}

	/**
	 * Setter for Moderator Enabled used by Jackson JSON processor
	 *
	 * @param moderatorEnabled true if the moderator of a Project's Review is enabled, otherwise false
	 */
	public void setModeratorEnabled(Boolean moderatorEnabled)
	{
		this.moderatorEnabled = moderatorEnabled;
	}

	/**
	 * Setter for Default Moderator used by Jackson JSON processor
	 *
	 * @param defaultModerator the Default Moderator User Name of a Project's Review
	 */
	public void setDefaultModerator(String defaultModerator)
	{
		this.defaultModerator = defaultModerator;
	}

	/**
	 * Setter for Default Duration used by Jackson JSON processor
	 *
	 * @param defaultDuration the Default Duration of a Project's Review in Work days
	 */
	public void setDefaultDuration(Integer defaultDuration)
	{
		this.defaultDuration = defaultDuration;
	}

	/**
	 * Setter for Default Objectives used by Jackson JSON processor
	 *
	 * @param defaultObjectives the Default Objectives
	 */
	public void setDefaultObjectives(String defaultObjectives)
	{
		this.defaultObjectives = defaultObjectives;
	}

	/**
	 * Provides a string representation of the Project Properties
	 *
	 * @return String containing the string representation of the Project Properties
	 */
	public String toString()
	{
		return "{\"name\":\"" + this.name + "\"," +
				"\"key\":\"" + this.key + "\"," +
				"\"defaultRepositoryName\":\"" + this.defaultRepositoryName + "\"," +
				"\"storeRevisions\":\"" + storeRevisions + "\"," +
				"\"permissionSchemeId\":\"" + permissionSchemeId + "\"," +
				"\"moderatorEnabled\":\"" + moderatorEnabled + "\"," +
				"\"defaultModerator\":\"" + defaultModerator + "\"," +
				"\"defaultDuration\":\"" + defaultDuration + "\"," +
				"\"defaultObjectives\":\"" + defaultObjectives + "\"," +
				"\"numberOfReviews\":" + numberOfReviews +
				"} "
				;
	}
}
