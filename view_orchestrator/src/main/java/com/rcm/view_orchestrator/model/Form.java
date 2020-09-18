package com.rcm.view_orchestrator.model;

import java.util.List;

public class Form {
	private String id;
	private String name;
	private String title;
	private List<Component> component;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Component> getComponent() {
		return component;
	}
	public void setComponent(List<Component> component) {
		this.component = component;
	}
	
	
}
