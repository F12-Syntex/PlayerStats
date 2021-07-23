package com.playerstats.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.playerstats.main.PlayerStats;

public class MessageUtils {

	public static void consolePrint(String s) {
		System.out.println(s);
	}
	public static String translateAlternateColorCodes(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
    
	public static void sendMessage(Player player, String s) {
		player.sendMessage(MessageUtils.translateAlternateColorCodes(s));
	}
	
	public static void inform(Player player, String s) {
		player.sendMessage(PlayerStats.getInstance().configManager.messages.prefix + MessageUtils.translateAlternateColorCodes(s));
	}

	public static void sendConsoleMessage(String msg){
		  Bukkit.getConsoleSender().sendMessage(MessageUtils.translateAlternateColorCodes(msg));
	}
	public static void sendConsoleMessage(String[] msg){
		for(int i = 0; i < msg.length; i++)
		System.out.println(MessageUtils.translateAlternateColorCodes(msg[i]));
	}
	public static void sendHelp(Player player) {
		 PlayerStats.getInstance().CommandManager.getCommands().forEach(i -> {
			 MessageUtils.sendMessage(player,PlayerStats.getInstance().configManager.messages.prefix + " " + "&6• &c" + i.name() + "&7 : &b" + i.info());
		 });
	}
}
