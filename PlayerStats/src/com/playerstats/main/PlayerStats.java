package com.playerstats.main;
import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.playerstats.config.ConfigManager;
import com.playerstats.cooldown.CooldownManager;
import com.playerstats.cooldown.CooldownTick;
import com.playerstats.events.EventHandler;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;


public class PlayerStats extends JavaPlugin implements Listener{


    public static PlayerStats instance;
    public com.playerstats.commands.CommandManager CommandManager;
    public ConfigManager configManager;
    public EventHandler eventHandler;
    public CooldownManager cooldownManager;
    public CooldownTick cooldownTick;
	public File ParentFolder;
	
    public Economy economy = null;
    
    public net.milkbowl.vault.permission.Permission perms;
    public Chat chat;
    
	@Override
	public void onEnable(){
		
		ParentFolder = getDataFolder();
	    instance = this;
		
	    if (!setupEconomy()) {
            this.getLogger().severe("Disabled due to no Vault dependency found!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
	    
	    if(!setupChat()) {
            this.getLogger().severe("Disabled due to no Vault (chat) dependency found!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
	    }
	    
	    if(!setupPermissions()) {
            this.getLogger().severe("Disabled due to no Vault (permissions) dependency found!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
	    }
	    
	    configManager = new ConfigManager();
	    configManager.setup(this);
	    
	    this.reload();
	    
	    eventHandler = new EventHandler();
	    eventHandler.setup();
	    
	    this.cooldownManager = new CooldownManager();

	    this.cooldownTick = new CooldownTick();
	    this.cooldownTick.schedule();
	    
	    this.CommandManager = new com.playerstats.commands.CommandManager();
	    this.CommandManager.setup(this);
	    
		Bukkit.getOnlinePlayers().forEach(i -> {
			configManager.stats.logJoin(i);
		});
		

	}
	

	  private boolean setupEconomy() {
	        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
	            return false;
	        }

	        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
	        if (rsp == null) {
	            return false;
	        }
	        economy = rsp.getProvider();
	        return economy != null;
	    }
	  
	    private boolean setupChat() {
	    	try {
		        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
		        chat = rsp.getProvider();
		        return chat != null;
	    	}catch (Exception e) {
	    		return false;
	    	}
	    }

	    private boolean setupPermissions() {
	    	try {
		    	RegisteredServiceProvider<net.milkbowl.vault.permission.Permission> rsp = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
		        perms = rsp.getProvider();
		        return perms != null;	
	    	}catch (Exception e) {
	    		return false;
	    	}
	    }

	
	@Override
	public void onDisable(){
		
		
		Bukkit.getOnlinePlayers().forEach(i -> {
			configManager.stats.logLeave(i);
		});
		
		this.eventHandler = null;
		HandlerList.getRegisteredListeners(instance);
	}
	
	public static void Log(String msg){
		  Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&c" + PlayerStats.getInstance().getName() + "&7] &c(&7LOG&c): " + msg));
	}
	

	public void reload() {
		this.configManager = new ConfigManager();
		this.configManager.setup(this);
	}
		

	public static PlayerStats getInstance() {
		return instance;
	}
		
	
}
