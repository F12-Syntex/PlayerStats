package com.playerstats.config;

import org.bukkit.Material;

public class Configs extends Config{
	
	public String Sections = Material.PAPER.name();
	public String Text = Material.STRING.name();
	public String BooleanON = Material.REDSTONE.name();
	public String BooleanOFF = Material.SULPHUR.name();
	public String Numbers = Material.STICK.name();

	public Configs(String name, double version) {
		super(name, version);
		
		this.items.add(new ConfigItem("Configs.Sections.Material", Sections));
		this.items.add(new ConfigItem("Configs.Values.Text", Text));
		this.items.add(new ConfigItem("Configs.Values.Boolean.true", BooleanON));
		this.items.add(new ConfigItem("Configs.Values.Boolean.false", BooleanOFF));
		this.items.add(new ConfigItem("Configs.Values.Numbers", Numbers));
	
	}

	@Override
	public Configuration configuration() {
		// TODO Auto-generated method stub
		return Configuration.CONFIGS;
	}
	
	@Override
	public void initialize() {
		this.Sections = this.getConfiguration().getString("Configs.Sections.Material");
		this.Text = this.getConfiguration().getString("Configs.Values.Text");
		this.BooleanON = this.getConfiguration().getString("Configs.Values.Boolean.true");
		this.BooleanOFF = this.getConfiguration().getString("Configs.Values.Boolean.false");
		this.Numbers = this.getConfiguration().getString("Configs.Values.Numbers");
	}


	
}
