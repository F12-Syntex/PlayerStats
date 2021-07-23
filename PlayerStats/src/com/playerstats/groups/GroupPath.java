package com.playerstats.groups;

import com.playerstats.main.PlayerStats;

public class GroupPath {
	
	private int ID;
	
	public GroupPath(int ID) {
		this.ID = ID;
	}

	public int getKey() {
		return ID;
	}

	public void setKey(int ID) {
		this.ID = ID;
	}
	
	public Group getGroup() {
		return PlayerStats.getInstance().configManager.ranks.getGroup(getKey());
	}

}
