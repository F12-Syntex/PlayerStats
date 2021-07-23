
package com.playerstats.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.playerstats.main.PlayerStats;
import com.playerstats.placeholder.time.TimeFormater;
import com.playerstats.stats.Stats;
import com.playerstats.utils.MessageUtils;
import com.playerstats.utils.Time;

public class Profile extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
    	
    	CommandManager manager = PlayerStats.getInstance().CommandManager;
    	
    	if(args.length == 1) {
    		MessageUtils.inform(player, " &c/" + manager.main + " " + this.name() + " <player>");
    		return;
    	}

    	String target = args[1];
    	
    	Player user = null;
    	
    	for(Player i : Bukkit.getOnlinePlayers()) {
    		if(i.getName().equalsIgnoreCase(target)) {
    			user = i;
    		}
    	}
		
    	
    	if(user == null) {
    		MessageUtils.inform(player, "&c " + target + " is not online!");
    		return;
    	}
    	
    	Stats stats = PlayerStats.getInstance().configManager.stats.getStats(user);
    	
    	TimeFormater formatter = new TimeFormater();
    	
    	for(String o : PlayerStats.getInstance().configManager.messages.profile) {
    		
    		MessageUtils.sendMessage(player, o
    				
    				.replace("%player%", user.getDisplayName())
    				.replace("%weekly%", formatter.parse(stats.getPlayTime(7)))
    				.replace("%monthly%", formatter.parse(stats.getPlayTime(30)))
    				.replace("%allround%", formatter.parse(stats.getPlayTime()))
    				.replace("%rank%", stats.getRank().getName())
    				.replace("%firstjoined%", Time.ConvertDateToString(stats.getFirstJoined())));
    		
    	}
    	

    }
    
    
    @Override

    public String name() {
        return "profile";
    }

    @Override
    public String info() {
        return "returns a users data.";
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