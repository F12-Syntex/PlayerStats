package com.playerstats.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.Plugin;

import com.playerstats.main.PlayerStats;

public class EventHandler {

    public List<SubEvent> events = new ArrayList<SubEvent>();
	
    private Plugin plugin = PlayerStats.instance;
    
	public void setup() {
		this.events.add(new InputHandler());
		this.events.add(new PlayerTrack());
		this.events.add(new PlayerTalk());
		this.events.forEach(i -> plugin.getServer().getPluginManager().registerEvents(i, plugin));
	}
	
}
