
package com.playerstats.commands;

import org.bukkit.entity.Player;

import com.playerstats.main.PlayerStats;
import com.playerstats.utils.MessageUtils;

public class Reload extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
    	PlayerStats.getInstance().reload();
    	player.sendMessage(PlayerStats.getInstance().configManager.messages.prefix + " " + MessageUtils.translateAlternateColorCodes("&6reloaded!"));
    }

    @Override

    public String name() {
        return "reload";
    }

    @Override
    public String info() {
        return "reloads the plugin.";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

	@Override
	public String permission() {
		return  PlayerStats.getInstance().configManager.permissions.reload;	
	}
	

}