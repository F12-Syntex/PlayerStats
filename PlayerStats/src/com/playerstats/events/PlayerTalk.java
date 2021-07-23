package com.playerstats.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.playerstats.main.PlayerStats;
import com.playerstats.stats.Stats;
import com.playerstats.utils.MessageUtils;

public class PlayerTalk extends SubEvent{

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "Player message track";
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "track player messages";
	}
	
	
	@EventHandler
	public void onMessage(AsyncPlayerChatEvent e) {
	
		Stats data = PlayerStats.getInstance().configManager.stats.getStats(e.getPlayer());
		
		e.setFormat(MessageUtils.translateAlternateColorCodes(data.getRank().prefix + " " + e.getPlayer().getDisplayName() + " " + e.getMessage()));
		
		
		PlayerStats.getInstance().configManager.stats.logJoin(e.getPlayer());
		
		PlayerStats.getInstance().chat.setPlayerPrefix(e.getPlayer(), data.getRank().name);
		
		e.getPlayer().sendMessage("asdas: "+PlayerStats.getInstance().perms.playerInGroup(e.getPlayer(), data.getRank().name));
		e.getPlayer().sendMessage("asdasd: "+PlayerStats.getInstance().perms.has(e.getPlayer(), "essentials.heal"));
		
		
	}

}
