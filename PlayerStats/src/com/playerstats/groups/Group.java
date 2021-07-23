package com.playerstats.groups;

import java.util.ArrayList;
import java.util.List;

public class Group {
	
	public String name;
	public int rankup;
	public int playertime;
	public int ID;
	public List<GroupPath> allowed;
	public String prefix;
	public List<String> permissions;
	
	public Group(String name, int rankup, int playertime, List<Integer> alloweds, int ID, String prefix, List<String> permissions) {
		this.name = name;
		this.rankup = rankup;
		this.playertime = playertime;
		
		List<GroupPath> paths = new ArrayList<GroupPath>();
		
		for(int i : alloweds) {
			paths.add(new GroupPath(i));
		}
		
		this.allowed = paths;
		
		this.ID = ID;
		this.prefix = prefix;
		this.permissions = permissions;
		
	}
	
	public Group(String name, List<GroupPath> alloweds, int ID, String prefix, List<String> permissions) {
		this.name = name;
		this.rankup = 0;
		this.playertime = 0;
		this.allowed = alloweds;
		this.ID = ID;
		this.prefix = prefix;
		this.permissions = permissions;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRankup() {
		return rankup;
	}
	public void setRankup(int rankup) {
		this.rankup = rankup;
	}
	public int getPlayertime() {
		return playertime;
	}
	public void setPlayertime(int playertime) {
		this.playertime = playertime;
	}
	public List<GroupPath> getAllowed() {
		return allowed;
	}
	public void setAllowed(List<GroupPath> allowed) {
		this.allowed = allowed;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

}
