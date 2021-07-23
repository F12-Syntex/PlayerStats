package com.playerstats.config;

import com.playerstats.main.PlayerStats;

public class Permissions extends Config{

	public String basic = "bukkit.command.help";
	public String rank = "bukkit.command.help";
	public String rankup = "bukkit.command.help";
	public String admin = "base.admin";
	public String bypass = "base.timer.bypass";
	public String reload = "base.admin.reload";
	public String configure = "base.admin.configure";
	public String playtime = "base.command.playtime";
	public String profile = "base.command.profile";
	
	public Permissions(String name, double version) {
		super(name, version);
		
		this.items.add(new ConfigItem("Permissions.everyone.basic", basic));
		this.items.add(new ConfigItem("Permissions.everyone.rank", rank));
		this.items.add(new ConfigItem("Permissions.everyone.rankup", rankup));
		this.items.add(new ConfigItem("Permissions.everyone.playtime", playtime));
		this.items.add(new ConfigItem("Permissions.administration.admin", admin));
		this.items.add(new ConfigItem("Permissions.administration.timer_bypass", bypass));
		this.items.add(new ConfigItem("Permissions.administration.reload", reload));
		this.items.add(new ConfigItem("Permissions.administration.configure", configure));
		this.items.add(new ConfigItem("Permissions.administration.profile", profile));
	}

	@Override
	public Configuration configuration() {
		// TODO Auto-generated method stub
		return Configuration.PERMISSIONS;
	}
	
	@Override
	public void initialize() {
		this.basic = this.getConfiguration().getString("Permissions.everyone.basic").replace("%prefix%", PlayerStats.getInstance().configManager.messages.prefix);
		this.rank = this.getConfiguration().getString("Permissions.everyone.rank").replace("%prefix%", PlayerStats.getInstance().configManager.messages.prefix);
		this.rankup = this.getConfiguration().getString("Permissions.everyone.rankup").replace("%prefix%", PlayerStats.getInstance().configManager.messages.prefix);
		this.admin = this.getConfiguration().getString("Permissions.administration.admin").replace("%prefix%", PlayerStats.getInstance().configManager.messages.prefix);
		this.bypass = this.getConfiguration().getString("Permissions.administration.timer_bypass").replace("%prefix%", PlayerStats.getInstance().configManager.messages.prefix);
		this.reload = this.getConfiguration().getString("Permissions.administration.reload").replace("%prefix%", PlayerStats.getInstance().configManager.messages.prefix);
		this.configure = this.getConfiguration().getString("Permissions.administration.configure").replace("%prefix%", PlayerStats.getInstance().configManager.messages.prefix);
		this.playtime = this.getConfiguration().getString("Permissions.everyone.playtime").replace("%prefix%", PlayerStats.getInstance().configManager.messages.prefix);
		this.playtime = this.getConfiguration().getString("Permissions.administration.profile").replace("%prefix%", PlayerStats.getInstance().configManager.messages.prefix);
	}


	
}
