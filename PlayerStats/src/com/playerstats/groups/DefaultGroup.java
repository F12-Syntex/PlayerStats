package com.playerstats.groups;

import java.util.ArrayList;
import java.util.List;

public class DefaultGroup {

	private String name;
	private List<GroupPath> paths;
	private int ID;
	private String prefix;
	private List<String> permissions;
	
	public DefaultGroup(String name, List<Integer> path, int ID, String prefix, List<String> permissions) {
		this.name = name;
		
		List<GroupPath> paths = new ArrayList<GroupPath>();
		
		for(int i : path) {
			paths.add(new GroupPath(i));
		}
		
		this.paths = paths;
		this.prefix = prefix;
		this.setPermissions(permissions);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<GroupPath> getPaths() {
		return paths;
	}
	public void setPaths(List<GroupPath> paths) {
		this.paths = paths;
	}
	
	public Group get() {
		return new Group(getName(), paths, ID, this.prefix, this.permissions);
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
	
}
