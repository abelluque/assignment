package com.redhat.assignment.projects.model;

public enum ProjectStatus {

	open("open"), in_progress("in_progress"), completed("completed"), cancelled("cancelled");

	private String valueString;

	private ProjectStatus(String valueString) {
		this.valueString = valueString;
	}

	public String getValueString() {
		return valueString;
	}

	public void setValueString(String valueString) {
		this.valueString = valueString;
	}

}
