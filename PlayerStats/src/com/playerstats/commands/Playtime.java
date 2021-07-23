
package com.playerstats.commands;

import org.bukkit.entity.Player;

import com.playerstats.main.PlayerStats;
import com.playerstats.placeholder.time.TimeFormater;
import com.playerstats.utils.MessageUtils;

public class Playtime extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
    	
    	CommandManager manager = PlayerStats.getInstance().CommandManager;
    	
    	if(args.length == 1) {
    		
    		MessageUtils.inform(player, " &c/" + manager.main + " " + this.name() + " alltime");
    		MessageUtils.inform(player, " &c/" + manager.main + " " + this.name() + " day");
    		MessageUtils.inform(player, " &c/" + manager.main + " " + this.name() + " week");
    		MessageUtils.inform(player, " &c/" + manager.main + " " + this.name() + " month");
    		
    		return;
    	}
    	
    	if(args[1].equalsIgnoreCase("alltime")) {
    		this.sendMessage(player, -1);	
    		return;
    	}
    	if(args[1].equalsIgnoreCase("day")) {
    		this.sendMessage(player, 1);	
    		return;
    	}
    	if(args[1].equalsIgnoreCase("week")) {
    		this.sendMessage(player, 7);	
    		return;
    	}
    	if(args[1].equalsIgnoreCase("month")) {
    		this.sendMessage(player, 30);	
    		return;
    	}

		MessageUtils.inform(player, " &c/" + manager.main + " " + this.name() + " alltime");
		MessageUtils.inform(player, " &c/" + manager.main + " " + this.name() + " day");
		MessageUtils.inform(player, " &c/" + manager.main + " " + this.name() + " week");
		MessageUtils.inform(player, " &c/" + manager.main + " " + this.name() + " month");
		

    }

    private void sendMessage(Player player, int days) {
    	TimeFormater formatter = new TimeFormater();
    	
    	com.playerstats.stats.Stats stats = PlayerStats.getInstance().configManager.stats.getStats(player);
    	
    	long time = 0;
    	
    	if(days < 0) {
    		time = stats.getPlayTime();
    	}else {
    		time = stats.getPlayTime(days);
    	}
    	
    	String formatted = formatter.parse(time);
    	
    	MessageUtils.sendMessage(player, PlayerStats.getInstance().configManager.messages.playtime.replace("%time%", formatted));
    }
    
    
    @Override

    public String name() {
        return "playtime";
    }

    @Override
    public String info() {
        return "returns how long you have been playing in this server.";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

	@Override
	public String permission() {
		return  PlayerStats.getInstance().configManager.permissions.basic;
	}
	

}