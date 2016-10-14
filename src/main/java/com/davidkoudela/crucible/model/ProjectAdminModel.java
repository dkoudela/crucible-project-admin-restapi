package com.davidkoudela.crucible.model;

import com.davidkoudela.crucible.rest.response.ResponseProjectDataList;
import com.davidkoudela.crucible.rest.response.ResponseProjectOperation;

/**
 * Description: Model class containing the logic functionality for Create / Modify / Delete Crucible Projects.
 * Copyright (C) 2016 David Koudela
 *
 * @author dkoudela
 * @since 14.10.2016
 */
public interface ProjectAdminModel {
	/**
	 * Creates a new Crucible project
	 *
	 * @param name the plain language name as displayed in the Crucible interface
	 * @param key the project key used when giving reviews their unique code names
	 * @param defaultRepositoryName default FishEye repository name
	 * @param storeRevisions retains a copy of all the source files under review even if the repository is disconnected
	 * @param permissionSchemeName permission scheme applied to this project
	 * @param moderatorEnabled clear to have reviews run by the author only
	 * @param defaultModerator the user who will be set as the moderator for all new reviews created in the project
	 * @param defaultDuration the default length of time (in week days) for reviews in this project
	 * @param defaultObjectives specify some text that will appear by default in the Review Objectives field of each new review
	 * @return ResponseProjectOperation containing code, message and cause key-value pairs used in REST responses
	 */
	public ResponseProjectOperation newProject(String name,
											   String key,
											   String defaultRepositoryName,
											   String storeRevisions,
											   String permissionSchemeName,
											   String moderatorEnabled,
											   String defaultModerator,
											   String defaultDuration,
											   String defaultObjectives);

	/**
	 * Modifies an existing Crucible project
	 *
	 * @param name the plain language name as displayed in the Crucible interface
	 * @param key the project key used when giving reviews their unique code names
	 * @param defaultRepositoryName default FishEye repository name
	 * @param storeRevisions retains a copy of all the source files under review even if the repository is disconnected
	 * @param permissionSchemeName permission scheme applied to this project
	 * @param moderatorEnabled clear to have reviews run by the author only
	 * @param defaultModerator the user who will be set as the moderator for all new reviews created in the project
	 * @param defaultDuration the default length of time (in week days) for reviews in this project
	 * @param defaultObjectives specify some text that will appear by default in the Review Objectives field of each new review
	 * @return ResponseProjectOperation containing code, message and cause key-value pairs used in REST responses
	 */
	public ResponseProjectOperation updateProject(String name,
												  String key,
												  String defaultRepositoryName,
												  String storeRevisions,
												  String permissionSchemeName,
												  String moderatorEnabled,
												  String defaultModerator,
												  String defaultDuration,
												  String defaultObjectives);

	/**
	 * Deletes an existing Crucible project
	 *
	 * @param key the project key used when giving reviews their unique code names
	 * @return ResponseProjectOperation containing code, message and cause key-value pairs used in REST responses
	 */
	public ResponseProjectOperation deleteProject(String key);

	/**
	 * Lists existing Crucible projects
	 *
	 * @return ResponseProjectDataList containing code, message, cause and project list key-value pairs used in REST responses
	 */
	public ResponseProjectDataList listProject();
}
