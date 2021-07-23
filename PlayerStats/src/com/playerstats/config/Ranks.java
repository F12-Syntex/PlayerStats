package com.playerstats.config;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;

import com.playerstats.groups.DefaultGroup;
import com.playerstats.groups.Group;
import com.playerstats.utils.ComponentBuilder;

public class Ranks extends Config{

	public List<Group> groups;
	public DefaultGroup defualtGroup;
	
	public Ranks(String name, double version) {
		super(name, version);
		
		this.items.add(new ConfigItem("Ranks.Default.Name", "default"));
		this.items.add(new ConfigItem("Ranks.Default.ID", 0));
		this.items.add(new ConfigItem("Ranks.Default.AllowedRankups", ComponentBuilder.createIntList(1)));
		this.items.add(new ConfigItem("Ranks.Default.Prefix", "&7[&6Member&7]"));
		this.items.add(new ConfigItem("Ranks.Default.Permissions", ComponentBuilder.createLore("bukkit.command.help", "bukkit.command.help2")));
		
		
		this.items.add(new ConfigItem("Ranks.Ranks.Trainer.RankupCost", 2000));
		this.items.add(new ConfigItem("Ranks.Ranks.Trainer.Playtime", 3600));
		this.items.add(new ConfigItem("Ranks.Ranks.Trainer.AllowedRankups", ComponentBuilder.createIntList(2, 3, 4)));
		this.items.add(new ConfigItem("Ranks.Ranks.Trainer.ID", 1));
		this.items.add(new ConfigItem("Ranks.Ranks.Trainer.Prefix", "&7[&6Trainer&7]"));
		this.items.add(new ConfigItem("Ranks.Ranks.Trainer.Permissions", ComponentBuilder.createLore("bukkit.command.heal", "bukkit.command.help2")));
		
		this.items.add(new ConfigItem("Ranks.Ranks.Bulbasaur.RankupCost", 35000));
		this.items.add(new ConfigItem("Ranks.Ranks.Bulbasaur.Playtime", 36000));
		this.items.add(new ConfigItem("Ranks.Ranks.Bulbasaur.AllowedRankups", ComponentBuilder.createIntList(5)));
		this.items.add(new ConfigItem("Ranks.Ranks.Bulbasaur.ID", 2));
		this.items.add(new ConfigItem("Ranks.Ranks.Bulbasaur.Prefix", "&7[&6Bulbasaur&7]"));
		this.items.add(new ConfigItem("Ranks.Ranks.Bulbasaur.Permissions", ComponentBuilder.createLore("bukkit.command.warp", "bukkit.command.help2")));
		
		this.items.add(new ConfigItem("Ranks.Ranks.Charmander.RankupCost", 35000));
		this.items.add(new ConfigItem("Ranks.Ranks.Charmander.Playtime", 36000));
		this.items.add(new ConfigItem("Ranks.Ranks.Charmander.AllowedRankups", ComponentBuilder.createIntList(5)));
		this.items.add(new ConfigItem("Ranks.Ranks.Charmander.ID", 3));
		this.items.add(new ConfigItem("Ranks.Ranks.Charmander.Prefix", "&7[&6Charmander&7]"));
		this.items.add(new ConfigItem("Ranks.Ranks.Charmander.Permissions", ComponentBuilder.createLore("bukkit.command.ec", "bukkit.command.help2")));
		
		this.items.add(new ConfigItem("Ranks.Ranks.Squirtle.RankupCost", 35000));
		this.items.add(new ConfigItem("Ranks.Ranks.Squirtle.Playtime", 36000));
		this.items.add(new ConfigItem("Ranks.Ranks.Squirtle.AllowedRankups", ComponentBuilder.createIntList(5)));
		this.items.add(new ConfigItem("Ranks.Ranks.Squirtle.ID", 4));
		this.items.add(new ConfigItem("Ranks.Ranks.Squirtle.Prefix", "&7[&6Squirtle&7]"));
		this.items.add(new ConfigItem("Ranks.Ranks.Squirtle.Permissions", ComponentBuilder.createLore("bukkit.command.eat", "bukkit.command.help2")));
		
		this.items.add(new ConfigItem("Ranks.Ranks.FireAnt.RankupCost", 35000));
		this.items.add(new ConfigItem("Ranks.Ranks.FireAnt.Playtime", 36000));
		this.items.add(new ConfigItem("Ranks.Ranks.FireAnt.AllowedRankups", ComponentBuilder.createIntList()));
		this.items.add(new ConfigItem("Ranks.Ranks.FireAnt.ID", 5));
		this.items.add(new ConfigItem("Ranks.Ranks.FireAnt.Prefix", "&7[&6FireAnt&7]"));
		this.items.add(new ConfigItem("Ranks.Ranks.FireAnt.Permissions", ComponentBuilder.createLore("bukkit.command.craft", "bukkit.command.help2")));
		
	}

	@Override
	public Configuration configuration() {
		// TODO Auto-generated method stub
		return Configuration.RANKS;
	}
	
	@Override
	public void initialize() {
		
		this.groups = new ArrayList<Group>();
		
		this.groups.clear();
		
		this.defualtGroup = new DefaultGroup(this.getConfiguration().getString("Ranks.Default.Name"), this.getConfiguration().getIntegerList("Ranks.Default.AllowedRankups"), this.getConfiguration().getInt("Ranks.Default.ID"), this.getConfiguration().getString("Ranks.Default.Prefix"), this.getConfiguration().getStringList("Ranks.Default.Permissions"));
		
		this.getConfiguration().getConfigurationSection("Ranks.Ranks").getKeys(false).forEach(i -> {
			ConfigurationSection ranks = this.getConfiguration().getConfigurationSection("Ranks.Ranks." + i);
			
			int rankup = ranks.getInt("RankupCost");
			int playtime = ranks.getInt("Playtime");
			int ID = ranks.getInt("ID");
			String prefix = ranks.getString("Prefix");
			List<String> permissions = ranks.getStringList("Permissions");

			List<Integer> paths = ranks.getIntegerList("AllowedRankups");
			
			Group group = new Group(i, rankup, playtime, paths, ID, prefix, permissions);
			groups.add(group);
			
		});
		
	}
	
	public Group getGroup(int ID) {
		
		if(ID == this.defualtGroup.get().ID) {
			return this.defualtGroup.get();
		}
		
		return this.groups.stream().filter(i -> i.getID() == ID).findFirst().get();
	}


	
}
