package com.davidkoudela.crucible.review;

import com.atlassian.crucible.spi.data.ReviewData;
import com.cenqua.crucible.model.Project;

import java.util.Date;

/**
 * Description: A holder class for holding necessary information about Crucible Reviews.
 * Copyright (C) 2019 David Koudela
 *
 * @author dkoudela
 * @since 2019-03-05
 */
public class ReviewVisitorData {
	Integer id;
	String permaId;
	Project project;
	Date createDate;
	Date updateDate;
	ReviewData.State state;


	public ReviewVisitorData(Integer id, String permaId, Project project, Date createDate, Date updateDate, ReviewData.State state) {
		this.id = id;
		this.permaId = permaId;
		this.project = project;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.state = state;
	}

	public ReviewVisitorData() {
		id = 0;
		permaId = "";
		project = new Project();
		createDate = null;
		updateDate = null;
		state = null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPermaId() {
		return permaId;
	}

	public void setPermaId(String permaId) {
		this.permaId = permaId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public ReviewData.State getState() {
		return state;
	}

	public void setState(ReviewData.State state) {
		this.state = state;
	}
}
