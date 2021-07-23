package com.playerstats.config;

import java.util.List;

import com.playerstats.utils.ComponentBuilder;
import com.playerstats.utils.MessageUtils;

public class Messages extends Config{

	public String prefix = "&7[&bStats&7]";
	public String error = "%prefix% sorry an error has accured!";;
	public String invalid_syntax = "%prefix% &cInvalid syntax";
	public String invalid_permission = "%prefix% &cYou cant do that!";
	public String invalid_entitiy = "%prefix% &cplayers only!";
	
	
	public String rank_display = "%prefix% &cyour rank is &b%rank%";
	public String rankup_money_error = "%prefix% &cyou need %required%&c to rankup!";
	public String rankup_time_error = "%prefix% &cyou have to play for %required%&c more to rankup!";
	public String rankup_sucess = "%prefix% &ayou have successfully ranked up to &a%rank%";
	public String rankup_invalid = "%prefix% &ayou can't rankup to that group.";
	public String playtime = "%prefix% &ayou have played for %time%&a.";
	public String firstJoin = "%prefix% &aYou joined on %joined%&a.";
	public List<String> profile = ComponentBuilder.createLore(

			"&bListing data for &7[&c%player%&7]",
			"&6=================================",
			"&cWeekly playtime: &7%weekly%",
			"&cMonthly playtime: &7%monthly%",
			"&cAllround Playtime: &7%allround%",
			"Rank: %rank%",
			"FirstJoined: %firstjoined%"
			
			);

	public Messages(String name, double version) {
		super(name, version);
		
		this.items.add(new ConfigItem("Messages.prefix", prefix));
		this.items.add(new ConfigItem("Messages.error", error));
		this.items.add(new ConfigItem("Messages.invalid_syntax", invalid_syntax));
		this.items.add(new ConfigItem("Messages.invalid_permission", invalid_permission));
		this.items.add(new ConfigItem("Messages.invalid_entitiy", invalid_entitiy));
		
		this.items.add(new ConfigItem("Messages.rank.display", rank_display));
		this.items.add(new ConfigItem("Messages.rankup.money_error", rankup_money_error));
		this.items.add(new ConfigItem("Messages.rankup.time_error", rankup_time_error));
		this.items.add(new ConfigItem("Messages.rankup.sucess", rankup_sucess));
		this.items.add(new ConfigItem("Messages.rankup.invalid", rankup_invalid));
		this.items.add(new ConfigItem("Messages.playtime.display", playtime));
		this.items.add(new ConfigItem("Messages.profile.display", profile));
		this.items.add(new ConfigItem("Messages.playtime.joined", firstJoin));
		
		
	}

	@Override
	public Configuration configuration() {
		// TODO Auto-generated method stub
		return Configuration.MESSAGES;
	}
	
	@Override
	public void initialize() {
		this.prefix = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.prefix"));
		this.error = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.error").replace("%prefix%", prefix));
		this.invalid_syntax = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.invalid_syntax").replace("%prefix%", prefix));
		this.invalid_permission = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.invalid_permission").replace("%prefix%", prefix));
		this.invalid_entitiy = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.invalid_entitiy").replace("%prefix%", prefix));
		
		this.rank_display = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.rank.display").replace("%prefix%", prefix));
		this.rankup_money_error = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.rankup.money_error").replace("%prefix%", prefix));
		this.rankup_time_error = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.rankup.time_error").replace("%prefix%", prefix));
		this.rankup_sucess = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.rankup.sucess").replace("%prefix%", prefix));
		this.rankup_invalid = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.rankup.invalid").replace("%prefix%", prefix));
		this.playtime = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.playtime.display").replace("%prefix%", prefix));		
		this.firstJoin = MessageUtils.translateAlternateColorCodes(this.getConfiguration().getString("Messages.playtime.joined").replace("%prefix%", prefix));	
		this.profile = ComponentBuilder.createLore(this.getConfiguration().getStringList("Messages.profile.display"));	
	}


	
}
