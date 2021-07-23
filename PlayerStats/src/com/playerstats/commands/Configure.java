
package com.playerstats.commands;

import org.bukkit.entity.Player;

import com.playerstats.configs.gui.ConfigsView;
import com.playerstats.main.PlayerStats;

public class Configure extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
    	ConfigsView gui = new ConfigsView(player, null, null);
    	gui.open();
    }

    @Override

    public String name() {
        return "configure";
    }

    @Override
    public String info() {
        return "Modify configs in game!";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

	@Override
	public String permission() {
		return  PlayerStats.getInstance().configManager.permissions.configure;
	}
	

}