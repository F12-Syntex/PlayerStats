
package com.playerstats.commands;

import org.bukkit.entity.Player;

import com.playerstats.main.PlayerStats;
import com.playerstats.stats.Stats;
import com.playerstats.utils.MessageUtils;

public class Rank extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
    	
    	Stats stats = PlayerStats.getInstance().configManager.stats.getStats(player);
    	
    	MessageUtils.sendMessage(player, PlayerStats.getInstance().configManager.messages.rank_display.replace("%rank%", stats.getRank().getName()));
   
    }
    
    @Override

    public String name() {
        return "rank";
    }

    @Override
    public String info() {
        return "returns your current rank.";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

	@Override
	public String permission() {
		return  PlayerStats.getInstance().configManager.permissions.rank;
	}
	

}