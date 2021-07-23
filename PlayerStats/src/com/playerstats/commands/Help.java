
package com.playerstats.commands;

import org.bukkit.entity.Player;

import com.playerstats.main.PlayerStats;
import com.playerstats.utils.MessageUtils;

public class Help extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
    	MessageUtils.sendHelp(player);
    }

    @Override

    public String name() {
        return "help";
    }

    @Override
    public String info() {
        return "displays the help command";
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