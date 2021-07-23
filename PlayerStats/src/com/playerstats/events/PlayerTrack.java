package com.playerstats.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.playerstats.main.PlayerStats;
import com.playerstats.stats.Stats;

public class PlayerTrack extends SubEvent{

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "Player track";
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "track player join/leave";
	}
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		PlayerStats.getInstance().configManager.stats.logJoin(e.getPlayer());
		
		Stats data = PlayerStats.getInstance().configManager.stats.getStats(e.getPlayer());
		PlayerStats.getInstance().chat.setPlayerPrefix(e.getPlayer(), data.getRank().name);
		
		if(!PlayerStats.getInstance().perms.playerInGroup(e.getPlayer(), data.getRank().name)) {	
			PlayerStats.getInstance().perms.playerAddGroup(e.getPlayer(), data.getRank().name);
		}
		
		
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		PlayerStats.getInstance().configManager.stats.logLeave(e.getPlayer());
	}

}
