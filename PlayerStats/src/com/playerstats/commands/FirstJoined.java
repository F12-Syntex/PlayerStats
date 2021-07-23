
package com.playerstats.commands;

import org.bukkit.entity.Player;

import com.playerstats.main.PlayerStats;
import com.playerstats.stats.Stats;
import com.playerstats.utils.MessageUtils;
import com.playerstats.utils.Time;

public class FirstJoined extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
    	
    	Stats stats = PlayerStats.getInstance().configManager.stats.getStats(player);
    	
    	MessageUtils.sendMessage(player, PlayerStats.getInstance().configManager.messages.firstJoin.replace("%joined%", Time.ConvertDateToString(stats.getFirstJoined())));
   
    }
    
    @Override

    public String name() {
        return "joined";
    }

    @Override
    public String info() {
        return "returns when you joined the server.";
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