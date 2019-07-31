package com.redhat.assignment.projects.model;

import java.io.Serializable;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

@DataObject
public class Project implements Serializable {

	private static final long serialVersionUID = -6994655395272795259L;

	private Integer projectId;

	private String ownerFirstName;

	private String ownerLastName;

	private String ownerEmailAddress;

	private String projectTitle;

	private String projectDescription;

	private ProjectStatus projectStatus;

	public Project() {
		super();
	}

	public Project(JsonObject json) {
		
		this.projectId = json.getInteger("projectId");
		this.ownerFirstName = json.getString("ownerFirstName");
		this.ownerLastName = json.getString("ownerLastName");
		this.ownerEmailAddress = json.getString("ownerEmailAddres");
		this.projectTitle = json.getString("projectTitle");
		this.projectDescription = json.getString("projectDescription");
		
		//FIXME Cambiar por un valor real
		this.projectStatus = ProjectStatus.in_progress;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	public String getOwnerLastName() {
		return ownerLastName;
	}

	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}

	public String getOwnerEmailAddress() {
		return ownerEmailAddress;
	}

	public void setOwnerEmailAddress(String ownerEmailAddress) {
		this.ownerEmailAddress = ownerEmailAddress;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public ProjectStatus getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(ProjectStatus projectStatus) {
		this.projectStatus = projectStatus;
	}

	public JsonObject toJson() {

		final JsonObject json = new JsonObject();
		json.put("projectId", this.projectId);
		json.put("projectTitle", this.projectTitle);
		json.put("projectDescription", this.projectDescription);
		json.put("ownerLastName", this.ownerLastName);
		json.put("ownerFirstName", this.ownerFirstName);
		json.put("ownerEmailAddress", this.ownerEmailAddress);
		json.put("projectStatus", this.projectStatus.getValueString());
		return json;
	}

}
