
package com.playerstats.commands;

import org.bukkit.entity.Player;

import com.playerstats.groups.Group;
import com.playerstats.groups.GroupPath;
import com.playerstats.main.PlayerStats;
import com.playerstats.placeholder.time.TimeFormater;
import com.playerstats.stats.Stats;
import com.playerstats.utils.MessageUtils;

import net.milkbowl.vault.economy.Economy;

public class Rankup extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {

    	CommandManager manager = PlayerStats.getInstance().CommandManager;
    	Stats stats = PlayerStats.getInstance().configManager.stats.getStats(player);
    	
    	if(args.length == 1) {
    		for(GroupPath i : stats.getRank().allowed) {
    			MessageUtils.sendMessage(player, "&c/" + manager.main + " " + this.name() + " " + i.getGroup().getName());
        	}
    		return;
    	}
    	
    	Group target = null;
    	
		for(GroupPath i : stats.getRank().allowed) {
			if(i.getGroup().getName().equalsIgnoreCase(args[1])) {
				target = i.getGroup();
			}
		}
		
		//PlayerStats.getInstance().configManager.messages.playtime.replace("%time%", formatted)
		
    	if(target == null) {
    		MessageUtils.sendMessage(player, PlayerStats.getInstance().configManager.messages.rankup_invalid);
    		return;
    	}
    	
    	Economy economy = PlayerStats.getInstance().economy;
    	
    	if(economy.getBalance(player) < target.getRankup()) {
    		
    		double required = target.getRankup() - economy.getBalance(player);
    		
    		MessageUtils.sendMessage(player, PlayerStats.getInstance().configManager.messages.rankup_money_error.replace("%required%", required+""));
    		return;
    	}
    	
    	if(stats.getPlayTime() < target.playertime) {
    	
    		TimeFormater matter = new TimeFormater();
    		String required = matter.parse(target.getPlayertime() - stats.getPlayTime());
    		
    		MessageUtils.sendMessage(player, PlayerStats.getInstance().configManager.messages.rankup_time_error.replace("%required%", required));
    		return;
    	}
    	
    	
    	economy.withdrawPlayer(player, target.getRankup());
    	stats.setRank(target);
    	
    	PlayerStats.getInstance().configManager.stats.refresh();
    	MessageUtils.sendMessage(player, PlayerStats.getInstance().configManager.messages.rankup_sucess.replace("%rank%", stats.getRank().getName()));
    	
    	
    }
    
    @Override

    public String name() {
        return "rankup";
    }

    @Override
    public String info() {
        return "attempt to rankup.";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

	@Override
	public String permission() {
		return  PlayerStats.getInstance().configManager.permissions.rankup;
	}
	

}